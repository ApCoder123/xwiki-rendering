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
package org.xwiki.rendering.listener.reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents a reference to a Resource (document, image, attachment, mail, etc).
 * Note that this representation is independent of any wiki syntax.
 *
 * @version $Id$
 * @since 2.5RC1
 */
public class ResourceReference implements Cloneable
{
    /**
     * Bracket start char.
     */
    private static final char BRACKET_START = '[';

    /**
     * Bracket dtop char.
     */
    private static final char BRACKET_STOP = ']';

    /**
     * Comma followed by space.
     */
    private static final String COMMA_SPACE = ", ";

    /**
     * @see #isTyped()
     */
    private boolean isTyped = true;

    /**
     * @see #getReference()
     *
     * Note that the reason we store the reference as a String and not as an Entity Reference is because we want
     * the Rendering module independent of the XWiki Model so that it can be used independently of XWiki.
     */
    private String reference;

    /**
     * @see #getBaseReferences()
     *
     * Note that the reason we store the base reference as a String and not as an Entity Reference is because we want
     * the Rendering module independent of the XWiki Model so that it can be used independently of XWiki.
     */
    private List<String> baseReferences;

    /**
     * @see #getType()
     */
    private ResourceType type;

    /**
     * @see #getParameter(String)
     */
    private Map<String, String> parameters = new HashMap<String, String>();
    
    /**
     * @param reference see {@link #getReference()}
     * @param type see {@link #getType()}
     */
    public ResourceReference(String reference, ResourceType type)
    {
        setReference(reference);
        setType(type);
    }

    /**
     * @param isTyped see {@link #isTyped()}
     */
    public void setTyped(boolean isTyped)
    {
        this.isTyped = isTyped;
    }

    /**
     * @return true if the resource type has been explicitly provided (eg in XWiki Syntax 2.1 if the reference is
     *         prefixed with the resource type followed by ":" and then the rest of the reference)
     */
    public boolean isTyped()
    {
        return this.isTyped;
    }

    /**
     * @param reference see {@link #getReference()}
     */
    public void setReference(String reference)
    {
        this.reference = reference;
    }

    /**
     * @return the reference pointed to by this resource. For example a reference can be a document's name (which
     *         depends on the wiki, for example for XWiki the format is "wiki:space.page"), a URI
     *         (for example: mailto:john@doe.com), a URL, an
     *         <a href="http://en.wikipedia.org/wiki/InterWiki">Inter Wiki</a> reference, etc
     * @see #getType()
     */
    public String getReference()
    {
        return this.reference;
    }

    /**
     * @param baseReference see {@link #getBaseReferences()}
     */
    public void addBaseReference(String baseReference)
    {
        if (this.baseReferences == null) {
            this.baseReferences = new ArrayList<String>();
        }
        this.baseReferences.add(baseReference);
    }

    /**
     * @param baseReferences see {@link #getBaseReferences()}
     */
    public void addBaseReferences(List<String> baseReferences)
    {
        for (String baseReference : baseReferences) {
            addBaseReference(baseReference);
        }
    }

    /**
     * @return the base references to use when we need to compute an absolute reference and {@link #getReference()}
     *         returns a non absolute reference, can be {@code null}. When resolving references the list should be
     *         evaluated from first to last (the last entries qualifying the entries earlier in the list)
     */
    public List<String> getBaseReferences()
    {
        List<String> result;
        if (this.baseReferences == null) {
            result = Collections.emptyList();
        } else {
            result = Collections.unmodifiableList(this.baseReferences);
        }
        return result;
    }

    /**
     * @return the type of the resource
     * @see ResourceType
     */
    public ResourceType getType()
    {
        return this.type;
    }

    /**
     * @param type the type of the resource
     * @see ResourceType
     */
    public void setType(ResourceType type)
    {
        this.type = type;
    }

    /**
     * @param name see {@link #getParameter(String)}
     * @param value see {@link #getParameter(String)}
     */
    public void setParameter(String name, String value)
    {
        this.parameters.put(name, value);
    }

    /**
     * @param parameters see {@link #getParameters()}
     */
    public void setParameters(Map<String, String> parameters)
    {
        this.parameters.putAll(parameters);
    }

    /**
     * @param name see {@link #getParameter(String)}
     */
    public void removeParameter(String name)
    {
        this.parameters.remove(name);
    }

    /**
     * In order for Resource references to be extensible we allow for extra parameters in addition to the Resource
     * reference. For example this is used in Document Resource References for storing the query string and anchor
     * information, and in InterWiki Resource References to store the InterWiki Alias. Note that supported parameters
     * depend on the Renderer that will be used (i.e. it depends on the target Syntax). For example the XWiki Syntax
     * 2.1 only supports "queryString" and "anchor".
     * 
     * @param name the name of the parameter to get
     * @return the parameter value or null if no such parameter exist
     */
    public String getParameter(String name)
    {
        return this.parameters.get(name);
    }

    /**
     * @return the collections of parameters, see {@link #getParameter(String)}
     */
    public Map<String, String> getParameters()
    {
        return Collections.unmodifiableMap(this.parameters);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The output is syntax independent since this class is used for all syntaxes. Specific syntaxes should extend this
     * class and override this method to perform syntax-dependent formatting.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Typed = [").append(isTyped()).append("]");
        sb.append(" ");
        sb.append("Type = [").append(getType().getScheme()).append("]");
        if (getReference() != null) {
            sb.append(" ");
            sb.append("Reference = [").append(getReference()).append("]");
        }
        if (!getBaseReferences().isEmpty()) {
            sb.append(" ");
            sb.append("Base References = [");
            Iterator<String> it = getBaseReferences().listIterator();
            while (it.hasNext()) {
                String baseReference = it.next();
                sb.append(BRACKET_START).append(baseReference).append(BRACKET_STOP);
                if (it.hasNext()) {
                    sb.append(COMMA_SPACE);
                }
            }
            sb.append(BRACKET_STOP);
        }
        Map<String, String> params = getParameters();
        if (!params.isEmpty()) {
            sb.append(" ");
            sb.append("Parameters = [");
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                sb.append(BRACKET_START).append(entry.getKey()).append("] = [").append(entry.getValue());
                sb.append(BRACKET_STOP);
                if (it.hasNext()) {
                    sb.append(COMMA_SPACE);
                }
            }
            sb.append(BRACKET_STOP);
        }

        return sb.toString();
    }

    @Override
    public ResourceReference clone()
    {
        ResourceReference clone;
        try {
            clone = (ResourceReference) super.clone();
        } catch (CloneNotSupportedException e) {
            // Should never happen
            throw new RuntimeException("Failed to clone object", e);
        }
        return clone;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(1, 9)
            .append(getType())
            .append(isTyped())
            .append(getReference())
            .append(getBaseReferences())
            .append(getParameters())
            .toHashCode();
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object.getClass() != getClass()) {
            return false;
        }
        ResourceReference rhs = (ResourceReference) object;
        return new EqualsBuilder()
            .append(getType(), rhs.getType())
            .append(isTyped(), rhs.isTyped())
            .append(getReference(), rhs.getReference())
            .append(getBaseReferences(), rhs.getBaseReferences())
            .append(getParameters(), rhs.getParameters())
            .isEquals();
    }
}
