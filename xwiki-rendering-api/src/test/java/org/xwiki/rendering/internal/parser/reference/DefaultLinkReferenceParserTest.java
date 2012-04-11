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

import org.junit.Assert;
import org.junit.Test;
import org.xwiki.rendering.listener.reference.DocumentResourceReference;
import org.xwiki.rendering.listener.reference.InterWikiResourceReference;
import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.listener.reference.ResourceType;
import org.xwiki.rendering.parser.ResourceReferenceParser;
import org.xwiki.rendering.wiki.WikiModel;
import org.xwiki.test.AbstractComponentTestCase;

/**
 * Unit tests for {@link DefaultLinkReferenceParser}.
 * 
 * @version $Id$
 * @since 2.6M1
 */
public class DefaultLinkReferenceParserTest extends AbstractComponentTestCase
{
    private ResourceReferenceParser parser;

    @Override
    protected void registerComponents() throws Exception
    {
        // Create a Mock WikiModel implementation so that the link parser works in wiki mode
        registerMockComponent(WikiModel.class);

        this.parser = getComponentManager().getInstance(ResourceReferenceParser.class, "link");
    }

    @Test
    public void testParseLinksWhenInWikiModeCommon() throws Exception
    {
        ResourceReference reference = parser.parse("");
        Assert.assertEquals("", reference.getReference());
        Assert.assertFalse(reference.isTyped());
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertEquals("Typed = [false] Type = [doc] Reference = []", reference.toString());

        reference = parser.parse("Hello World");
        Assert.assertEquals("Hello World", reference.getReference());
        Assert.assertFalse(reference.isTyped());
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertEquals("Typed = [false] Type = [doc] Reference = [Hello World]", reference.toString());

        reference = parser.parse("http://xwiki.org");
        Assert.assertEquals("http://xwiki.org", reference.getReference());
        Assert.assertFalse(reference.isTyped());
        Assert.assertEquals(ResourceType.URL, reference.getType());
        Assert.assertEquals("Typed = [false] Type = [url] Reference = [http://xwiki.org]", reference.toString());

        // Verify mailto: URI is recognized
        reference = parser.parse("mailto:john@smith.com?subject=test");
        Assert.assertEquals("john@smith.com?subject=test", reference.getReference());
        Assert.assertTrue(reference.isTyped());
        Assert.assertEquals(ResourceType.MAILTO, reference.getType());
        Assert.assertEquals("Typed = [true] Type = [mailto] Reference = [john@smith.com?subject=test]",
            reference.toString());

        // Verify attach: URI is recognized
        reference = parser.parse("attach:some:content");
        Assert.assertEquals("some:content", reference.getReference());
        Assert.assertTrue(reference.isTyped());
        Assert.assertEquals(ResourceType.ATTACHMENT, reference.getType());
        Assert.assertEquals("Typed = [true] Type = [attach] Reference = [some:content]", reference.toString());

        // Verify that unknown URIs are ignored
        // Note: In this example we point to a document and we consider that myxwiki is the wiki name and
        // http://xwiki.org is the page name
        reference = parser.parse("mywiki:http://xwiki.org");
        Assert.assertEquals("mywiki:http://xwiki.org", reference.getReference());
        Assert.assertFalse(reference.isTyped());
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertEquals("Typed = [false] Type = [doc] Reference = [mywiki:http://xwiki.org]", reference.toString());
    }

    @Test
    public void testParseLinks() throws Exception
    {
        ResourceReference reference = parser.parse("doc:wiki:space.page");
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertEquals("wiki:space.page", reference.getReference());
        Assert.assertEquals("Typed = [true] Type = [doc] Reference = [wiki:space.page]", reference.toString());
        Assert.assertTrue(reference.isTyped());

        // Verify InterWiki links work
        reference = parser.parse("interwiki:alias:content");
        Assert.assertEquals(ResourceType.INTERWIKI, reference.getType());
        Assert.assertEquals("content", reference.getReference());
        Assert.assertTrue(reference.isTyped());
        Assert.assertEquals("alias", ((InterWikiResourceReference) reference).getInterWikiAlias());
        Assert.assertEquals("Typed = [true] Type = [interwiki] Reference = [content] "
            + "Parameters = [[interWikiAlias] = [alias]]", reference.toString());

        // Verify that an invalid InterWiki link is considered as Document link
        reference = parser.parse("interwiki:invalid_since_doesnt_have_colon");
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertEquals("interwiki:invalid_since_doesnt_have_colon", reference.getReference());
        Assert.assertFalse(reference.isTyped());
        Assert.assertEquals("Typed = [false] Type = [doc] Reference = [interwiki:invalid_since_doesnt_have_colon]",
            reference.toString());

        // Verify typed URLs
        reference = parser.parse("url:http://xwiki.org");
        Assert.assertEquals(ResourceType.URL, reference.getType());
        Assert.assertTrue(reference.isTyped());
        Assert.assertEquals("http://xwiki.org", reference.getReference());
        Assert.assertEquals("Typed = [true] Type = [url] Reference = [http://xwiki.org]", reference.toString());

        // Verify query string and anchors have no meaning in link reference to documents.
        reference = parser.parse("Hello World?no=queryString#notAnAnchor");
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertEquals("Hello World?no=queryString#notAnAnchor", reference.getReference());
        Assert.assertFalse(reference.isTyped());
        Assert.assertNull(((DocumentResourceReference) reference).getAnchor());
        Assert.assertNull(((DocumentResourceReference) reference).getQueryString());
        Assert.assertEquals("Typed = [false] Type = [doc] Reference = [Hello World?no=queryString#notAnAnchor]",
            reference.toString());

        // Verify that the interwiki separator from XWiki Syntax 2.0 has not meaning in link references to documents
        reference = parser.parse("page@alias");
        Assert.assertEquals(ResourceType.DOCUMENT, reference.getType());
        Assert.assertFalse(reference.isTyped());
        Assert.assertEquals("page@alias", reference.getReference());
        Assert.assertEquals("Typed = [false] Type = [doc] Reference = [page@alias]", reference.toString());

        // Verify path link types
        reference = parser.parse("path:/some/path");
        Assert.assertEquals(ResourceType.PATH, reference.getType());
        Assert.assertTrue(reference.isTyped());
        Assert.assertEquals("/some/path", reference.getReference());
        Assert.assertEquals("Typed = [true] Type = [path] Reference = [/some/path]", reference.toString());

        // Verify UNC link types
        reference = parser.parse("unc:\\\\myserver\\myshare\\mydoc.txt");
        Assert.assertEquals(ResourceType.UNC, reference.getType());
        Assert.assertTrue(reference.isTyped());
        Assert.assertEquals("\\\\myserver\\myshare\\mydoc.txt", reference.getReference());
        Assert.assertEquals("Typed = [true] Type = [unc] Reference = [\\\\myserver\\myshare\\mydoc.txt]",
            reference.toString());
    }

    @Test
    public void testParseLinksWithEscapes() throws Exception
    {
        // Veirfy that reference escapes are left as is by the link parser
        ResourceReference resourceReference = parser.parse("pa\\.ge");
        Assert.assertEquals(ResourceType.DOCUMENT, resourceReference.getType());
        Assert.assertEquals("pa\\.ge", resourceReference.getReference());
    }
}
