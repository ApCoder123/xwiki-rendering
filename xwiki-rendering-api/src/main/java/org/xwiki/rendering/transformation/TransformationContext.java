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
package org.xwiki.rendering.transformation;

import org.xwiki.rendering.block.XDOM;
import org.xwiki.rendering.syntax.Syntax;

/**
 * The context of the transformation process. Contains information such as the current XWiki DOM for the parsed content.
 *
 * @version $Id$
 * @since 2.4M1
 */
public class TransformationContext implements Cloneable
{
    /**
     * The complete {@link XDOM} of the content currently being transformed.
     */
    private XDOM xdom;

    /**
     * The current syntax.
     */
    private Syntax syntax;

    /**
     * @see #getId(). Note that the id is optional.
     */
    private String id;

    /**
     * In restricted mode, only transformations that are deemed safe for execution by untrusted users will be performed.
     */
    private boolean restricted;

    /**
     * @see #getTargetSyntax()
     */
    private Syntax targetSyntax;

    /**
     * Default constructor that doesn't set the XDOM or the Syntax. This is because setting the XDOM and the Syntax is
     * optional and only required by some Macros to behave as expected.
     */
    public TransformationContext()
    {
        this(null, null);
    }

    /**
     * Some macros require the XDOM and the Syntax to be set.
     *
     * @param xdom see {@link #setXDOM(org.xwiki.rendering.block.XDOM)}
     * @param syntax see {@link #setSyntax(org.xwiki.rendering.syntax.Syntax)}
     */
    public TransformationContext(XDOM xdom, Syntax syntax)
    {
        this(xdom, syntax, false);
    }

    /**
     * Some macros require the XDOM and the Syntax to be set.
     *
     * @param xdom see {@link #setXDOM(org.xwiki.rendering.block.XDOM)}
     * @param syntax see {@link #setSyntax(org.xwiki.rendering.syntax.Syntax)}
     * @param restricted disables potentially harmful transformations.
     */
    public TransformationContext(XDOM xdom, Syntax syntax, boolean restricted)
    {
        setXDOM(xdom);
        setSyntax(syntax);
        setRestricted(restricted);
    }

    /**
     * @return an id representing the transformation being evaluated. It's a free form name that Transformations can
     *         use, for example if they need to perform some caching based on a key. For example the Velocity Macro is
     *         using this id to pass it to the underlying Velocity Engine so that it caches macros using this key.
     * @since 2.4M2
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * @param id the id
     * @since 2.4M2
     * @see #getId()
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @param xdom the complete {@link XDOM} of the content currently being transformed.
     */
    public void setXDOM(XDOM xdom)
    {
        this.xdom = xdom;
    }

    /**
     * @return the complete {@link XDOM} of the content currently being transformed.
     */
    public XDOM getXDOM()
    {
        return this.xdom;
    }

    /**
     * @param syntax the current syntax.
     */
    public void setSyntax(Syntax syntax)
    {
        this.syntax = syntax;
    }

    /**
     * @return the current syntax.
     */
    public Syntax getSyntax()
    {
        return this.syntax;
    }

    /**
     * @return indicator of whether the transformation context is restricted or not.
     */
    public boolean isRestricted()
    {
        return this.restricted;
    }

    /**
     * @param restricted set indicator of whether the transformation context is restricted or not.
     */
    public void setRestricted(boolean restricted)
    {
        this.restricted = restricted;
    }

    /**
     * @return the syntax of the renderer
     */
    public Syntax getTargetSyntax()
    {
        return this.targetSyntax;
    }

    /**
     * @param targetSyntax the syntax of the renderer
     */
    public void setTargetSyntax(Syntax targetSyntax)
    {
        this.targetSyntax = targetSyntax;
    }

    @Override
    public TransformationContext clone()
    {
        TransformationContext newContext;
        try {
            newContext = (TransformationContext) super.clone();
        } catch (CloneNotSupportedException e) {
            // Should never happen
            throw new RuntimeException("Failed to clone object", e);
        }

        return newContext;
    }
}
