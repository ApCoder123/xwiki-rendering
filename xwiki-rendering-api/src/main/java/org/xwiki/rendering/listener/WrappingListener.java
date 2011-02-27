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
package org.xwiki.rendering.listener;

import java.util.Map;

import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.syntax.Syntax;

/**
 * A Listener wrapping another Listener.
 * 
 * @version $Id$
 */
public class WrappingListener implements Listener
{
    /**
     * The Listener to wrap.
     */
    private Listener listener;

    /**
     * @param listener the Listener to wrap
     */
    public void setWrappedListener(Listener listener)
    {
        this.listener = listener;
    }

    /**
     * @return the Listener to wrap
     */
    public Listener getWrappedListener()
    {
        return this.listener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#beginDocument(MetaData)
     * @since 3.0M2
     */
    public void beginDocument(MetaData metaData)
    {
        this.listener.beginDocument(metaData);
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#endDocument(MetaData)
     * @since 3.0M2
     */
    public void endDocument(MetaData metaData)
    {
        this.listener.endDocument(metaData);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginGroup(Map)
     */
    public void beginGroup(Map<String, String> parameters)
    {
        this.listener.beginGroup(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endGroup(Map)
     */
    public void endGroup(Map<String, String> parameters)
    {
        this.listener.endGroup(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginFormat(Format, Map)
     */
    public void beginFormat(Format format, Map<String, String> parameters)
    {
        this.listener.beginFormat(format, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginList(org.xwiki.rendering.listener.ListType, java.util.Map)
     */
    public void beginList(ListType listType, Map<String, String> parameters)
    {
        this.listener.beginList(listType, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginListItem()
     */
    public void beginListItem()
    {
        this.listener.beginListItem();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginMacroMarker(java.lang.String, java.util.Map, java.lang.String,
     *      boolean)
     */
    public void beginMacroMarker(String name, Map<String, String> parameters, String content, boolean isInline)
    {
        this.listener.beginMacroMarker(name, parameters, content, isInline);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginParagraph(java.util.Map)
     */
    public void beginParagraph(Map<String, String> parameters)
    {
        this.listener.beginParagraph(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginSection(java.util.Map)
     */
    public void beginSection(Map<String, String> parameters)
    {
        this.listener.beginSection(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginHeader(org.xwiki.rendering.listener.HeaderLevel,
     *      java.lang.String, java.util.Map)
     */
    public void beginHeader(HeaderLevel level, String id, Map<String, String> parameters)
    {
        this.listener.beginHeader(level, id, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endFormat(Format, Map)
     */
    public void endFormat(Format format, Map<String, String> parameters)
    {
        this.listener.endFormat(format, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endList(org.xwiki.rendering.listener.ListType, java.util.Map)
     */
    public void endList(ListType listType, Map<String, String> parameters)
    {
        this.listener.endList(listType, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endListItem()
     */
    public void endListItem()
    {
        this.listener.endListItem();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endMacroMarker(java.lang.String, java.util.Map, java.lang.String,
     *      boolean)
     */
    public void endMacroMarker(String name, Map<String, String> parameters, String content, boolean isInline)
    {
        this.listener.endMacroMarker(name, parameters, content, isInline);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endParagraph(java.util.Map)
     */
    public void endParagraph(Map<String, String> parameters)
    {
        this.listener.endParagraph(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endSection(java.util.Map)
     */
    public void endSection(Map<String, String> parameters)
    {
        this.listener.endSection(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endHeader(org.xwiki.rendering.listener.HeaderLevel, java.lang.String,
     *      java.util.Map)
     */
    public void endHeader(HeaderLevel level, String id, Map<String, String> parameters)
    {
        this.listener.endHeader(level, id, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.LinkListener#beginLink(ResourceReference , boolean,
     *      java.util.Map)
     * @since 2.5RC1
     */
    public void beginLink(ResourceReference reference, boolean isFreeStandingURI, Map<String, String> parameters)
    {
        this.listener.beginLink(reference, isFreeStandingURI, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.LinkListener#endLink(ResourceReference , boolean, java.util.Map)
     * @since 2.5RC1
     */
    public void endLink(ResourceReference reference, boolean isFreeStandingURI, Map<String, String> parameters)
    {
        this.listener.endLink(reference, isFreeStandingURI, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onMacro(java.lang.String, java.util.Map, java.lang.String, boolean)
     */
    public void onMacro(String id, Map<String, String> parameters, String content, boolean isInline)
    {
        this.listener.onMacro(id, parameters, content, isInline);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onNewLine()
     */
    public void onNewLine()
    {
        this.listener.onNewLine();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onSpace()
     */
    public void onSpace()
    {
        this.listener.onSpace();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onSpecialSymbol(char)
     */
    public void onSpecialSymbol(char symbol)
    {
        this.listener.onSpecialSymbol(symbol);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onWord(java.lang.String)
     */
    public void onWord(String word)
    {
        this.listener.onWord(word);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onId(java.lang.String)
     */
    public void onId(String name)
    {
        this.listener.onId(name);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onHorizontalLine(Map)
     */
    public void onHorizontalLine(Map<String, String> parameters)
    {
        this.listener.onHorizontalLine(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onEmptyLines(int)
     */
    public void onEmptyLines(int count)
    {
        this.listener.onEmptyLines(count);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onVerbatim(String, boolean, Map)
     */
    public void onVerbatim(String protectedString, boolean isInline, Map<String, String> parameters)
    {
        this.listener.onVerbatim(protectedString, isInline, parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onRawText(String, Syntax)
     */
    public void onRawText(String text, Syntax syntax)
    {
        this.listener.onRawText(text, syntax);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginDefinitionList(java.util.Map)
     * @since 2.0RC1
     */
    public void beginDefinitionList(Map<String, String> parameters)
    {
        this.listener.beginDefinitionList(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endDefinitionList(java.util.Map)
     * @since 2.0RC1
     */
    public void endDefinitionList(Map<String, String> parameters)
    {
        this.listener.endDefinitionList(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#beginDefinitionTerm()
     * @since 1.6M2
     */
    public void beginDefinitionTerm()
    {
        this.listener.beginDefinitionTerm();
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#beginDefinitionDescription()
     * @since 1.6M2
     */
    public void beginDefinitionDescription()
    {
        this.listener.beginDefinitionDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#endDefinitionTerm()
     * @since 1.6M2
     */
    public void endDefinitionTerm()
    {
        this.listener.endDefinitionTerm();
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#endDefinitionDescription()
     * @since 1.6M2
     */
    public void endDefinitionDescription()
    {
        this.listener.endDefinitionDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#beginQuotation(java.util.Map)
     * @since 1.6M2
     */
    public void beginQuotation(Map<String, String> parameters)
    {
        this.listener.beginQuotation(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#endQuotation(java.util.Map)
     * @since 1.6M2
     */
    public void endQuotation(Map<String, String> parameters)
    {
        this.listener.endQuotation(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginQuotationLine()
     * @since 1.6M2
     */
    public void beginQuotationLine()
    {
        this.listener.beginQuotationLine();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endQuotationLine()
     * @since 1.6M2
     */
    public void endQuotationLine()
    {
        this.listener.endQuotationLine();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTable(java.util.Map)
     */
    public void beginTable(Map<String, String> parameters)
    {
        this.listener.beginTable(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTableCell(java.util.Map)
     */
    public void beginTableCell(Map<String, String> parameters)
    {
        this.listener.beginTableCell(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTableHeadCell(java.util.Map)
     */
    public void beginTableHeadCell(Map<String, String> parameters)
    {
        this.listener.beginTableHeadCell(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTableRow(java.util.Map)
     */
    public void beginTableRow(Map<String, String> parameters)
    {
        this.listener.beginTableRow(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTable(java.util.Map)
     */
    public void endTable(Map<String, String> parameters)
    {
        this.listener.endTable(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTableCell(java.util.Map)
     */
    public void endTableCell(Map<String, String> parameters)
    {
        this.listener.endTableCell(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTableHeadCell(java.util.Map)
     */
    public void endTableHeadCell(Map<String, String> parameters)
    {
        this.listener.endTableHeadCell(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTableRow(java.util.Map)
     */
    public void endTableRow(Map<String, String> parameters)
    {
        this.listener.endTableRow(parameters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onImage(ResourceReference, boolean, java.util.Map)
     * @since 2.5RC1
     */
    public void onImage(ResourceReference reference, boolean isFreeStandingURI, Map<String, String> parameters)
    {
        this.listener.onImage(reference, isFreeStandingURI, parameters);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.rendering.listener.Listener#beginMetaData(MetaData)
     * @since 3.0M2
     */
    public void beginMetaData(MetaData metadata)
    {
        this.listener.beginMetaData(metadata);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.rendering.listener.Listener#endMetaData(MetaData)
     * @since 3.0M2
     */
    public void endMetaData(MetaData metadata)
    {
        this.listener.endMetaData(metadata);
    }
}
