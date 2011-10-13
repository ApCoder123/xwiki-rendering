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
package org.xwiki.rendering.internal.transformation.icon;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.configuration.ConfigurationSource;
import org.xwiki.rendering.transformation.icon.IconTransformationConfiguration;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * All configuration options for the Icon transformation.
 *
 * @version $Id$
 * @since 2.6RC1
 */
@Component
@Singleton
public class DefaultIconTransformationConfiguration implements IconTransformationConfiguration, Initializable
{
    /**
     * Prefix for configuration keys for the Icon transformation module.
     */
    private static final String PREFIX = "rendering.transformation.icon.";

    /**
     * Used to dynamically look up a Configuration Source in order to handle the use case when there's no
     * implementation of it available in the current classloader.
     */
    @Inject
    private ComponentManager componentManager;

    /**
     * Default Tools.
     */
    private Properties defaultMappings = new Properties();

    @Override
    public void initialize() throws InitializationException
    {
        // Default Mappings
        this.defaultMappings.setProperty(":)", "emoticon_smile");
        this.defaultMappings.setProperty(":(", "emoticon_unhappy");
        this.defaultMappings.setProperty(":P", "emoticon_tongue");
        this.defaultMappings.setProperty(":D", "emoticon_grin");
        this.defaultMappings.setProperty(";)", "emoticon_wink");
        this.defaultMappings.setProperty("(y)", "thumb_up");
        this.defaultMappings.setProperty("(n)", "thumb_down");
        this.defaultMappings.setProperty("(i)", "information");
        this.defaultMappings.setProperty("(/)", "accept");
        this.defaultMappings.setProperty("(x)", "cancel");
        this.defaultMappings.setProperty("(!)", "error");
        this.defaultMappings.setProperty("(+)", "add");
        this.defaultMappings.setProperty("(-)", "delete");
        this.defaultMappings.setProperty("(?)", "help");
        this.defaultMappings.setProperty("(on)", "lightbulb");
        this.defaultMappings.setProperty("(off)", "lightbulb_off");
        this.defaultMappings.setProperty("(*)", "star");
    }

    @Override
    public Properties getMappings()
    {
        // Merge default properties and properties defined in the configuration
        Properties props = new Properties();
        props.putAll(this.defaultMappings);

        try {
            ConfigurationSource configuration = this.componentManager.lookup(ConfigurationSource.class);
            props.putAll(configuration.getProperty(PREFIX + "mappings", Properties.class));
        } catch (ComponentLookupException e) {
            // No Configuration Source implementation found, don't add any new mapping
        }

        return props;
    }
}
