/**
 * This file is part of Everit - Resource.
 *
 * Everit - Resource is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Resource is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Resource.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.resource.internal;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.querydsl.support.QuerydslCallable;
import org.everit.osgi.querydsl.support.QuerydslSupport;
import org.everit.osgi.resource.api.ResourceConstants;
import org.everit.osgi.resource.api.ResourceService;
import org.everit.osgi.resource.schema.qdsl.QResource;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;

/**
 * Implementation for {@link ResourceService}.
 */
@Component(name = ResourceConstants.SERVICE_FACTORYPID_RESOURCE, metatype = true, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE)
@Properties({ @Property(name = ResourceConstants.PROP_QUERYDSL_SUPPORT_TARGET) })
@Service
public class ResourceComponent implements ResourceService {

    @Reference(bind = "bindQuerydslSupport")
    private QuerydslSupport querydslSupport;

    public void bindQuerydslSupport(QuerydslSupport querydslSupport) {
        this.querydslSupport = querydslSupport;
    }

    @Override
    public long createResource() {
        return querydslSupport.execute(new QuerydslCallable<Long>() {

            @Override
            public Long call(Connection connection, Configuration configuration) throws SQLException {
                QResource qResource = new QResource("qResource");
                SQLInsertClause insertClause = new SQLInsertClause(connection, configuration, qResource);
                return insertClause.executeWithKey(qResource.resourceId);
            }
        });
    }

    @Override
    public boolean deleteResource(final long resourceId) {
        return querydslSupport.execute(new QuerydslCallable<Boolean>() {

            @Override
            public Boolean call(Connection connection, Configuration configuration) throws SQLException {
                QResource qResource = new QResource("qResource");
                SQLDeleteClause deleteClause = new SQLDeleteClause(connection, configuration, qResource);
                long deletedRecordNum = deleteClause.where(qResource.resourceId.eq(resourceId)).execute();
                return deletedRecordNum > 0;
            }
        });
    }
}
