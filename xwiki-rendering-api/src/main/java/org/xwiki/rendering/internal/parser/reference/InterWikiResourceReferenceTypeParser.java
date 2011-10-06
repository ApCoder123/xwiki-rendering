/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.internal.parser.reference;

import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.listener.reference.InterWikiResourceReference;
import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.listener.reference.ResourceType;

/**
 * Parses a resource reference to a document in another wiki (interwiki).
 *
 * @version $Id$
 * @since 2.5RC1
 */
@Component
@Named("interwiki")
@Singleton
public class InterWikiResourceReferenceTypeParser extends AbstractURIResourceReferenceTypeParser
{
    /**
     * Separator between the interwiki alias and the interwiki path (eg "alias" in interwiki:alias:path).  
     */
    public static final String INTERWIKI_ALIAS_SEPARATOR = ":";

    @Override
    public ResourceType getType()
    {
        return ResourceType.INTERWIKI;
    }

    @Override
    public ResourceReference parse(String reference)
    {
        ResourceReference resultReference = null;
        // Try to find an interwiki separator to extract the interwiki alias from the interwiki suffix.
        // If no separator is found it means the interwiki link syntax is invalid. In this case consider that the
        // reference is not an interwiki link.
        int pos = reference.indexOf(INTERWIKI_ALIAS_SEPARATOR);
        if (pos > -1) {
            InterWikiResourceReference interWikiReference =
                new InterWikiResourceReference(reference.substring(pos + INTERWIKI_ALIAS_SEPARATOR.length()));
            interWikiReference.setInterWikiAlias(reference.substring(0, pos + INTERWIKI_ALIAS_SEPARATOR.length() - 1));
            resultReference = interWikiReference;
        }
        return resultReference;
    }
}
