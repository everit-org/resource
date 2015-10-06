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
package org.everit.resource.ri;

import java.util.Objects;

import org.everit.persistence.querydsl.support.QuerydslSupport;
import org.everit.resource.ResourceService;
import org.everit.resource.ri.schema.qdsl.QResource;

import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;

/**
 * Implementation of {@link ResourceService}.
 */
public class ResourceServiceImpl implements ResourceService {

  private final QuerydslSupport querydslSupport;

  /**
   * Constructor.
   *
   * @param querydslSupport
   *          the {@link QuerydslSupport} instance. Cannot be <code>null</code>.
   *
   * @throws NullPointerException
   *           if querydslSupport parameter is <code>null</code>.
   */
  public ResourceServiceImpl(final QuerydslSupport querydslSupport) {
    this.querydslSupport =
        Objects.requireNonNull(querydslSupport, "QuerydslSupport cannot be null.");
  }

  @Override
  public long createResource() {
    return querydslSupport.execute((connection, configuration) -> {
      QResource qResource = QResource.resource;
      new SQLInsertClause(connection, configuration, qResource);
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

}
