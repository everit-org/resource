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
package org.everit.osgi.resource.schema.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;




/**
 * QResource is a Querydsl query type for QResource
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QResource extends com.mysema.query.sql.RelationalPathBase<QResource> {

    private static final long serialVersionUID = -225061993;

    public static final QResource resource = new QResource("res_resource");

    public class PrimaryKeys {

        public final com.mysema.query.sql.PrimaryKey<QResource> resourcePk = createPrimaryKey(resourceId);

    }

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final PrimaryKeys pk = new PrimaryKeys();

    public QResource(String variable) {
        super(QResource.class, forVariable(variable), "org.everit.osgi.resource", "res_resource");
        addMetadata();
    }

    public QResource(String variable, String schema, String table) {
        super(QResource.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QResource(Path<? extends QResource> path) {
        super(path.getType(), path.getMetadata(), "org.everit.osgi.resource", "res_resource");
        addMetadata();
    }

    public QResource(PathMetadata<?> metadata) {
        super(QResource.class, metadata, "org.everit.osgi.resource", "res_resource");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(resourceId, ColumnMetadata.named("resource_id").ofType(-5).withSize(19).notNull());
    }

}

