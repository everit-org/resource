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

import com.mysema.query.sql.SQLTemplates;

/**
 * ExpandedSQLTemplates is set the quote and printing schema name.
 */
public class ExpandedSQLTemplates extends SQLTemplates {

    /**
     * Create a new SQLTemplates where the quote string is "; and using the specified escape and quote parameters.
     * 
     * @return the builder which building the correct {@link SQLTemplates}.
     */
    public static Builder builder() {
        return new Builder() {
            @Override
            public SQLTemplates build() {
                return new ExpandedSQLTemplates();
            }

            @Override
            protected SQLTemplates build(final char escape, final boolean quote) {
                return new ExpandedSQLTemplates(escape, quote);
            }
        };
    }

    /**
     * Default constructor, where the quote string is "; and using the schema name for the query.
     */
    public ExpandedSQLTemplates() {
        this("\"", '\\', true);
        setPrintSchema(true);
    }

    /**
     * Simple constructor where the default quote string is "; and using the schema name for the query.
     * 
     * @param escape
     *            the escape char.
     * @param useQuotes
     *            using quote or not.
     */
    public ExpandedSQLTemplates(final char escape, final boolean useQuotes) {
        this("\"", escape, useQuotes);
        setPrintSchema(true);
    }

    /**
     * Simple constructor.
     * 
     * @param quoteStr
     *            the quote string.
     * @param escape
     *            the escape char.
     * @param useQuotes
     *            using quote or not.
     */
    protected ExpandedSQLTemplates(final String quoteStr, final char escape, final boolean useQuotes) {
        super(quoteStr, escape, useQuotes);
    }

}
