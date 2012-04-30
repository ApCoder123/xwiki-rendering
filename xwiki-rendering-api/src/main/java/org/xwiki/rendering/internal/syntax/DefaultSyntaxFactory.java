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
package org.xwiki.rendering.internal.syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.parser.ParseException;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.syntax.SyntaxFactory;
import org.xwiki.rendering.syntax.SyntaxType;

/**
 * @version $Id$
 * @since 1.5M2
 */
@Component
@Singleton
public class DefaultSyntaxFactory implements SyntaxFactory
{
    /**
     * Used to cut the syntax identifier into syntax name and syntax version.
     */
    private static final Pattern SYNTAX_PATTERN = Pattern.compile("(.*)\\/(.*)");

    @Override
    public Syntax createSyntaxFromIdString(String syntaxIdAsString) throws ParseException
    {
        if (syntaxIdAsString == null) {
            throw new ParseException("The passed Syntax cannot be NULL");
        }

        Matcher matcher = SYNTAX_PATTERN.matcher(syntaxIdAsString);
        if (!matcher.matches()) {
            throw new ParseException("Invalid Syntax format [" + syntaxIdAsString + "]");
        }

        String syntaxId = matcher.group(1);
        String version = matcher.group(2);

        // For well-known syntaxes, get the Syntax Name from the registered SyntaxType, otherwise use the id as both
        // the human readable name and the technical id (since the syntax string doesn't contain any information about
        // the pretty name of a syntax type).
        SyntaxType syntaxType = SyntaxType.getSyntaxTypes().get(syntaxId);
        if (syntaxType == null) {
            syntaxType = new SyntaxType(syntaxId, syntaxId);
        }

        return new Syntax(syntaxType, version);
    }
}
