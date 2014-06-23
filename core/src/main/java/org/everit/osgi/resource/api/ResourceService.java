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
package org.everit.osgi.resource.api;

/**
 * Service interface for Resource related operations.
 */
public interface ResourceService {
    /**
     * Creates a new resource object.
     *
     * @return The id of the new resource.
     */
    long createResource();

    /**
     * Deletes a resource object from the database.
     *
     * @param resourceId
     *            The id of the resource object to be deleted.
     * @return true if a record was truly deleted, false if it did not even exist.
     */
    boolean deleteResource(long resourceId);

}
