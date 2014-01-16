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

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.listener.reference.ResourceType;
import org.xwiki.rendering.parser.ResourceReferenceTypeParser;

/**
 * Considers all passed link references to be untyped and tries to guess the type by first looking for a URL
 * and if a URL is not found then considering it's a reference to a document.
 *
 * @version $Id$
 * @since 2.6M1
 */
@Component
@Named("link/untyped")
@Singleton
public class DefaultUntypedLinkReferenceParser extends AbstractResourceReferenceParser
{
    /**
     * Parser to parse link references pointing to URLs.
     */
    @Inject
    @Named("url")
    private ResourceReferenceTypeParser urlResourceReferenceTypeParser;

    /**
     * Parser to parse link references pointing to documents.
     */
    @Inject
    @Named("doc")
    private ResourceReferenceTypeParser documentResourceReferenceTypeParser;

    @Override
    public ResourceReference parse(String rawReference)
    {
        ResourceReference reference;

        // If we're not in wiki mode then references are considered URLs.
        if (!isInWikiMode()) {
            reference = new ResourceReference(rawReference, ResourceType.URL);
        } else {
            // Try to guess the link type. It can be either:
            // - a URL (specified without the "url" type)
            // - a reference to a document (specified without the "doc" type)
            reference = this.urlResourceReferenceTypeParser.parse(rawReference);
            if (reference == null) {
                // What remains is considered to be a link to a document, use the document link type parser to parse it.
                reference = this.documentResourceReferenceTypeParser.parse(rawReference);
            }
        }
        reference.setTyped(false);

        return reference;
    }
}
