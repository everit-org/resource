/**
 * This file is part of Everit - Resource RI.
 *
 * Everit - Resource RI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Resource RI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Resource RI.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.resource.ri.internal;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.querydsl.support.QuerydslSupport;
import org.everit.osgi.resource.ResourceService;
import org.everit.osgi.resource.ri.ResourceConstants;
import org.everit.osgi.resource.ri.schema.qdsl.QResource;

import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;

@Component(name = ResourceConstants.SERVICE_FACTORYPID_RESOURCE, metatype = true, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE)
@Properties({
        @Property(name = ResourceConstants.PROP_QUERYDSL_SUPPORT_TARGET)
})
@Service
public class ResourceComponent implements ResourceService {

    @Reference(bind = "setQuerydslSupport")
    private QuerydslSupport querydslSupport;

    @Override
    public long createResource() {
        return querydslSupport.execute((connection, configuration) -> {
            QResource qResource = QResource.resource;
            return new SQLInsertClause(connection, configuration, qResource)
                    .executeWithKey(qResource.resourceId);
        });
    }

    @Override
    public boolean deleteResource(final long resourceId) {
        return querydslSupport.execute((connection, configuration) -> {
            QResource qResource = QResource.resource;
            long deletedRecordNum = new SQLDeleteClause(connection, configuration, qResource)
                    .where(qResource.resourceId.eq(resourceId))
                    .execute();
            return deletedRecordNum > 0;
        });
    }

    public void setQuerydslSupport(final QuerydslSupport querydslSupport) {
        this.querydslSupport = querydslSupport;
    }

}
