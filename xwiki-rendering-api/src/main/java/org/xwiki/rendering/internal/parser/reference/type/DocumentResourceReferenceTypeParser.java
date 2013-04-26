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
package org.xwiki.rendering.internal.parser.reference.type;

import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.listener.reference.DocumentResourceReference;
import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.listener.reference.ResourceType;

/**
 * Parses a resource reference to a document.
 *
 * @version $Id$
 * @since 2.5RC1
 */
@Component
@Named("doc")
@Singleton
public class DocumentResourceReferenceTypeParser extends AbstractURIResourceReferenceTypeParser
{
    @Override
    public ResourceType getType()
    {
        return ResourceType.DOCUMENT;
    }

    @Override
    public ResourceReference parse(String reference)
    {
        // Note that we construct a DocumentResourceReference object so that the user who calls
        // {@link ResourceReferenceParser#parse} can cast it to a DocumentResourceReference object if the type is of
        // type Document.
        return new DocumentResourceReference(reference);
    }
}
