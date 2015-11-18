/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.resource.ri.schema.qdsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QResource is a Querydsl query type for QResource
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QResource extends com.querydsl.sql.RelationalPathBase<QResource> {

    private static final long serialVersionUID = -2073814280;

    public static final QResource resource = new QResource("res_resource");

    public class PrimaryKeys {

        public final com.querydsl.sql.PrimaryKey<QResource> resourcePk = createPrimaryKey(resourceId);

    }

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final PrimaryKeys pk = new PrimaryKeys();

    public QResource(String variable) {
        super(QResource.class, forVariable(variable), "org.everit.resource.ri", "res_resource");
        addMetadata();
    }

    public QResource(String variable, String schema, String table) {
        super(QResource.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QResource(Path<? extends QResource> path) {
        super(path.getType(), path.getMetadata(), "org.everit.resource.ri", "res_resource");
        addMetadata();
    }

    public QResource(PathMetadata metadata) {
        super(QResource.class, metadata, "org.everit.resource.ri", "res_resource");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(resourceId, ColumnMetadata.named("resource_id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

