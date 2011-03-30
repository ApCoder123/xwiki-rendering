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
import org.xwiki.rendering.parser.ResourceReferenceParser;
import org.xwiki.rendering.parser.ResourceReferenceTypeParser;

/**
 * Considers all passed images references to be untyped and tries to guess the type by first looking for a URL
 * and then considering it's a reference to an attachment.
 *
 * @version $Id$
 * @since 2.6M1
 */
@Component("image/untyped")
@Singleton
public class DefaultUntypedImageReferenceParser implements ResourceReferenceParser
{
    /**
     * Parser to parse link references pointing to URLs.
     */
    @Inject
    @Named("url")
    private ResourceReferenceTypeParser urlResourceReferenceTypeParser;

    /**
     * Parser to parse link references pointing to attachments.
     */
    @Inject
    @Named("attach")
    private ResourceReferenceTypeParser attachmentResourceReferenceTypeParser;

    /**
     * {@inheritDoc}
     * @see ResourceReferenceParser#parse(String)
     */
    public ResourceReference parse(String rawReference)
    {
        // Try to guess the link type. It can be either:
        // - a URL (specified without the "url" type)
        // - a reference to an attachment (specified without the "attach" type)
        ResourceReference reference = this.urlResourceReferenceTypeParser.parse(rawReference);
        if (reference == null) {
            // What remains is considered to be a link to an attachment, use the attachment link type parser to
            // parse.
            reference = this.attachmentResourceReferenceTypeParser.parse(rawReference);
        }
        reference.setTyped(false);
        return reference;
    }
}
