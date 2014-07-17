/**
 * This file is part of Everit - Resource RI Tests.
 *
 * Everit - Resource RI Tests is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Resource RI Tests is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Resource RI Tests.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.resource.ri.tests;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.querydsl.support.QuerydslSupport;
import org.everit.osgi.resource.ResourceService;
import org.everit.osgi.resource.ri.schema.qdsl.QResource;
import org.junit.Assert;
import org.junit.Test;

import com.mysema.query.sql.SQLQuery;

@Component(name = "ResourceComponentTest", immediate = true, metatype = true, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE)
@Properties({
        @Property(name = "eosgi.testEngine", value = "junit4"),
        @Property(name = "eosgi.testId", value = "ResourceComponentTest"),
        @Property(name = "querydslSupport.target")
})
@Service(value = ResourceComponentTest.class)
public class ResourceComponentTest {

    /**
     * Fake, non existing resourceId to be able to test deleteResource with wrong parameter.
     */
    private static final Long FAKE_ID = 2000L;

    /**
     * {@link ResourceService}.
     */
    @Reference(bind = "setResourceService")
    private ResourceService resourceService;

    @Reference(bind = "setQuerydslSupport")
    private QuerydslSupport querydslSupport;

    /**
     * Counts the resource records in the database.
     *
     * @return Number of the records.
     */
    private Long countResources() {
        return querydslSupport.execute((connection, configuration) -> {
            QResource resource = QResource.resource;
            return new SQLQuery(connection, configuration).from(resource).count();
        });
    }

    public void setQuerydslSupport(final QuerydslSupport querydslSupport) {
        this.querydslSupport = querydslSupport;
    }

    public void setResourceService(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Test method for {@link ResourceService} createResource and deleteResource methods.
     */
    @Test
    public void testResourceService() {
        long firstCount = countResources();
        long resourceId = resourceService.createResource();
        long count = countResources();
        long expectedCount = firstCount + 1;
        Assert.assertEquals(expectedCount, count);
        Assert.assertFalse(resourceService.deleteResource(FAKE_ID));
        count = countResources();
        Assert.assertTrue(resourceService.deleteResource(resourceId));
        count = countResources();
        Assert.assertEquals(firstCount, count);
    }

}
