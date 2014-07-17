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
package org.everit.osgi.resource.ri;

/**
 * Constants of the resource component.
 */
public final class ResourceConstants {

    /**
     * The service factory PID of the resource component.
     */
    public static final String SERVICE_FACTORYPID_RESOURCE = "org.everit.osgi.resource.ri.Resource";

    /**
     * The property name of the OSGi filter expression defining which QuerydslSupport should be used by the resource
     * component.
     */
    public static final String PROP_QUERYDSL_SUPPORT_TARGET = "querydslSupport.target";

    private ResourceConstants() {
    }
}
