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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.syntax.Syntax;

/**
 * Send events to a list of {@link Listener}s.
 * 
 * @version $Id$
 * @since 2.1M1
 */
public class CompositeListener implements Listener
{
    /**
     * The listeners.
     */
    private List<Listener> listeners = new ArrayList<Listener>();

    /**
     * Add a nex listener to the list.
     * 
     * @param listener a listener
     */
    public void addListener(Listener listener)
    {
        this.listeners.add(listener);
    }

    /**
     * Get listener at the provided position in the list.
     * 
     * @param i the index of the listener in the list
     * @return the listener
     */
    public Listener getListener(int i)
    {
        return this.listeners.get(i);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginDefinitionDescription()
     */
    public void beginDefinitionDescription()
    {
        for (Listener listener : listeners) {
            listener.beginDefinitionDescription();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginDefinitionList(java.util.Map)
     */
    public void beginDefinitionList(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginDefinitionList(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginDefinitionTerm()
     */
    public void beginDefinitionTerm()
    {
        for (Listener listener : listeners) {
            listener.beginDefinitionTerm();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#beginDocument(MetaData)
     * @since 3.0M2
     */
    public void beginDocument(MetaData metaData)
    {
        for (Listener listener : listeners) {
            listener.beginDocument(metaData);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginFormat(org.xwiki.rendering.listener.Format, java.util.Map)
     */
    public void beginFormat(Format format, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginFormat(format, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginGroup(java.util.Map)
     */
    public void beginGroup(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginGroup(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginHeader(org.xwiki.rendering.listener.HeaderLevel,
     *      java.lang.String, java.util.Map)
     */
    public void beginHeader(HeaderLevel level, String id, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginHeader(level, id, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginList(org.xwiki.rendering.listener.ListType, java.util.Map)
     */
    public void beginList(ListType listType, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginList(listType, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginListItem()
     */
    public void beginListItem()
    {
        for (Listener listener : listeners) {
            listener.beginListItem();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginMacroMarker(java.lang.String, java.util.Map, java.lang.String,
     *      boolean)
     */
    public void beginMacroMarker(String name, Map<String, String> macroParameters, String content, boolean isInline)
    {
        for (Listener listener : listeners) {
            listener.beginMacroMarker(name, macroParameters, content, isInline);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginParagraph(java.util.Map)
     */
    public void beginParagraph(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginParagraph(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginQuotation(java.util.Map)
     */
    public void beginQuotation(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginQuotation(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginQuotationLine()
     */
    public void beginQuotationLine()
    {
        for (Listener listener : listeners) {
            listener.beginQuotationLine();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginSection(java.util.Map)
     */
    public void beginSection(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginSection(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTable(java.util.Map)
     */
    public void beginTable(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginTable(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTableCell(java.util.Map)
     */
    public void beginTableCell(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginTableCell(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTableHeadCell(java.util.Map)
     */
    public void beginTableHeadCell(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginTableHeadCell(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#beginTableRow(java.util.Map)
     */
    public void beginTableRow(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginTableRow(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endDefinitionDescription()
     */
    public void endDefinitionDescription()
    {
        for (Listener listener : listeners) {
            listener.endDefinitionDescription();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endDefinitionList(java.util.Map)
     */
    public void endDefinitionList(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endDefinitionList(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endDefinitionTerm()
     */
    public void endDefinitionTerm()
    {
        for (Listener listener : listeners) {
            listener.endDefinitionTerm();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Listener#endDocument(MetaData)
     * @since 3.0M2
     */
    public void endDocument(MetaData metaData)
    {
        for (Listener listener : listeners) {
            listener.endDocument(metaData);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endFormat(org.xwiki.rendering.listener.Format, java.util.Map)
     */
    public void endFormat(Format format, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endFormat(format, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endGroup(java.util.Map)
     */
    public void endGroup(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endGroup(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endHeader(org.xwiki.rendering.listener.HeaderLevel, java.lang.String,
     *      java.util.Map)
     */
    public void endHeader(HeaderLevel level, String id, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endHeader(level, id, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endList(org.xwiki.rendering.listener.ListType, java.util.Map)
     */
    public void endList(ListType listType, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endList(listType, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endListItem()
     */
    public void endListItem()
    {
        for (Listener listener : listeners) {
            listener.endListItem();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endMacroMarker(java.lang.String, java.util.Map, java.lang.String,
     *      boolean)
     */
    public void endMacroMarker(String name, Map<String, String> macroParameters, String content, boolean isInline)
    {
        for (Listener listener : listeners) {
            listener.endMacroMarker(name, macroParameters, content, isInline);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endParagraph(java.util.Map)
     */
    public void endParagraph(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endParagraph(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endQuotation(java.util.Map)
     */
    public void endQuotation(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endQuotation(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endQuotationLine()
     */
    public void endQuotationLine()
    {
        for (Listener listener : listeners) {
            listener.endQuotationLine();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endSection(java.util.Map)
     */
    public void endSection(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endSection(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTable(java.util.Map)
     */
    public void endTable(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endTable(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTableCell(java.util.Map)
     */
    public void endTableCell(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endTableCell(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTableHeadCell(java.util.Map)
     */
    public void endTableHeadCell(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endTableHeadCell(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#endTableRow(java.util.Map)
     */
    public void endTableRow(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endTableRow(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onEmptyLines(int)
     */
    public void onEmptyLines(int count)
    {
        for (Listener listener : listeners) {
            listener.onEmptyLines(count);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onHorizontalLine(java.util.Map)
     */
    public void onHorizontalLine(Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.onHorizontalLine(parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onId(java.lang.String)
     */
    public void onId(String name)
    {
        for (Listener listener : listeners) {
            listener.onId(name);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onMacro(java.lang.String, java.util.Map, java.lang.String, boolean)
     */
    public void onMacro(String id, Map<String, String> macroParameters, String content, boolean isInline)
    {
        for (Listener listener : listeners) {
            listener.onMacro(id, macroParameters, content, isInline);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onNewLine()
     */
    public void onNewLine()
    {
        for (Listener listener : listeners) {
            listener.onNewLine();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onRawText(java.lang.String, org.xwiki.rendering.syntax.Syntax)
     */
    public void onRawText(String text, Syntax syntax)
    {
        for (Listener listener : listeners) {
            listener.onRawText(text, syntax);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onSpace()
     */
    public void onSpace()
    {
        for (Listener listener : listeners) {
            listener.onSpace();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onSpecialSymbol(char)
     */
    public void onSpecialSymbol(char symbol)
    {
        for (Listener listener : listeners) {
            listener.onSpecialSymbol(symbol);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onVerbatim(java.lang.String, boolean, java.util.Map)
     */
    public void onVerbatim(String protectedString, boolean isInline, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.onVerbatim(protectedString, isInline, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.Listener#onWord(java.lang.String)
     */
    public void onWord(String word)
    {
        for (Listener listener : listeners) {
            listener.onWord(word);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.LinkListener#beginLink(org.xwiki.rendering.listener.reference.ResourceReference , boolean,
     *      java.util.Map)
     * @since 2.5RC1
     */
    public void beginLink(ResourceReference reference, boolean isFreeStandingURI, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.beginLink(reference, isFreeStandingURI, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.LinkListener#endLink(ResourceReference , boolean, java.util.Map)
     * @since 2.5RC1
     */
    public void endLink(ResourceReference reference, boolean isFreeStandingURI, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.endLink(reference, isFreeStandingURI, parameters);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.listener.ImageListener#onImage(ResourceReference, boolean, java.util.Map)
     * @since 2.5RC1 
     */
    public void onImage(ResourceReference reference, boolean isFreeStandingURI, Map<String, String> parameters)
    {
        for (Listener listener : listeners) {
            listener.onImage(reference, isFreeStandingURI, parameters);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.rendering.listener.Listener#beginMetaData(MetaData)
     * @since 3.0M2
     */
    public void beginMetaData(MetaData metadata)
    {
        for (Listener listener : listeners) {
            listener.beginMetaData(metadata);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.rendering.listener.Listener#endMetaData(MetaData)
     * @since 3.0M2
     */
    public void endMetaData(MetaData metadata)
    {
        for (Listener listener : listeners) {
            listener.endMetaData(metadata);
        }
    }
}
