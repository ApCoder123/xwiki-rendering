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
package org.xwiki.rendering.xml.internal;

import java.util.regex.Pattern;

/**
 * Allow to customize the syntax.
 * 
 * @version $Id$
 * @since 5.0M1
 */
public class XMLConfiguration
{
    /**
     * The default name of block element.
     */
    public static final String DEFAULT_ELEM_BLOCK = "block";

    /**
     * The default name of the attribute containing the name of the block.
     */
    public static final String DEFAULT_ATT_BLOCK_NAME = "name";

    /**
     * The default name of the parameter element.
     */
    public static final String DEFAULT_ELEM_PARAMETER = "parameter";

    /**
     * @see #getElementBlock()
     */
    private String elementBlock;

    /**
     * @see #getElementParameter()
     */
    private String elementParameter;

    /**
     * @see #getAttributeBlockName()
     */
    private String attributeBlockName;

    /**
     * @see #getElementParameterPattern()
     */
    private Pattern elementParameterPattern;

    /**
     * Default constructor.
     */
    public XMLConfiguration()
    {
        setElementBlock(DEFAULT_ELEM_BLOCK);
        setElementParameter(DEFAULT_ELEM_PARAMETER);
        setAttributeBlockName(DEFAULT_ATT_BLOCK_NAME);
    }

    /**
     * @return the name of the block element
     */
    public String getElementBlock()
    {
        return this.elementBlock;
    }

    /**
     * @param elementBlock the name of the block element
     */
    public void setElementBlock(String elementBlock)
    {
        this.elementBlock = elementBlock;
    }

    /**
     * @return the name of the parameter element
     */
    public String getElementParameter()
    {
        return this.elementParameter;
    }

    /**
     * @param elementParameter the name of the parameter element
     */
    public void setElementParameter(String elementParameter)
    {
        this.elementParameter = elementParameter;
        this.elementParameterPattern = Pattern.compile(Pattern.quote(elementParameter) + "(\\d*)");
    }

    /**
     * @return the pattern to use to parse the element parameter
     */
    public Pattern getElementParameterPattern()
    {
        return this.elementParameterPattern;
    }

    /**
     * @return the name of the attribute containing the name of the block
     */
    public String getAttributeBlockName()
    {
        return this.attributeBlockName;
    }

    /**
     * @param attributeBlockName the name of the attribute containing the name of the block
     */
    public void setAttributeBlockName(String attributeBlockName)
    {
        this.attributeBlockName = attributeBlockName;
    }
}
