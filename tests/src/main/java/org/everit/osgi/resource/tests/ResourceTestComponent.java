/**
 * This file is part of Everit - Resource Tests.
 *
 * Everit - Resource Tests is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Resource Tests is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Resource Tests.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.resource.tests;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.dev.testrunner.TestDuringDevelopment;
import org.everit.osgi.resource.api.ResourceService;
import org.everit.osgi.resource.schema.qdsl.QResource;
import org.junit.Assert;
import org.junit.Test;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;

/**
 * Test component for testing {@link ResourceService} methods.
 */
@Component(name = "ResourceTest", immediate = true, policy = ConfigurationPolicy.OPTIONAL, configurationFactory = false)
@Service(value = ResourceTestComponent.class)
@Properties({ @Property(name = "eosgi.testEngine", value = "junit4"),
        @Property(name = "eosgi.testId", value = "resourceTest"),
        @Property(name = "dataSource.target") })
@TestDuringDevelopment
public class ResourceTestComponent {
    /**
     * Fake, non existing resourceId to be able to test deleteResource with wrong parameter.
     */
    private static final Long FAKE_ID = 2000L;
    /**
     * Zero number.
     */
    private static final long ZERO = 0;
    /**
     * {@link DataSource}.
     */
    @Reference
    private DataSource dataSource;
    /**
     * {@link ResourceService}.
     */
    @Reference(policy = ReferencePolicy.STATIC)
    private ResourceService resourceService;
    /**
     * {@link ExpandedSQLTemplates}.
     */
    @Reference
    private SQLTemplates sqlTemplates;

    public void bindDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void bindResourceService(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Counts the resource records in the database.
     * 
     * @return Number of the records.
     */
    private Long countResources() {
        Long count = 0L;
        try (Connection connection = dataSource.getConnection()) {
            QResource qResource = new QResource("qResource");
            SQLQuery countClause = new SQLQuery(connection, sqlTemplates);
            count = countClause.from(qResource).count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Test method for {@link ResourceService} createResource and deleteResource methods.
     */
    @Test
    @TestDuringDevelopment
    public void testResourceService() {
        Long firstCount = countResources();
        long resourceId = resourceService.createResource();
        Long count = countResources();
        Long expectedCount = firstCount + 1;
        Assert.assertEquals(expectedCount, count);
        long numOfDeleted = resourceService.deleteResource(FAKE_ID);
        count = countResources();
        Assert.assertEquals(ZERO, numOfDeleted);
        resourceService.deleteResource(resourceId);
        count = countResources();
        Assert.assertEquals(firstCount, count);
    }
}
