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

    public static final QResource resResource = new QResource("res_resource");

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final com.mysema.query.sql.PrimaryKey<QResource> resResourcePk = createPrimaryKey(resourceId);

    public QResource(String variable) {
        super(QResource.class, forVariable(variable), null, "res_resource");
        addMetadata();
    }

    public QResource(String variable, String schema, String table) {
        super(QResource.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QResource(Path<? extends QResource> path) {
        super(path.getType(), path.getMetadata(), null, "res_resource");
        addMetadata();
    }

    public QResource(PathMetadata<?> metadata) {
        super(QResource.class, metadata, null, "res_resource");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(resourceId, ColumnMetadata.named("resource_id").ofType(-5).withSize(19).notNull());
    }

}

