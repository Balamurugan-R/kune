/*
 *
 * Copyright (C) 2007-2008 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ourproject.kune.platf.server.tool;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Singleton;

@Singleton
public class ToolRegistry {
    static Log log = LogFactory.getLog(ToolRegistry.class);

    // TODO: ConcurrentHashMap, sure?
    private final ConcurrentHashMap<String, ServerTool> tools;

    public ToolRegistry() {
	this.tools = new ConcurrentHashMap<String, ServerTool>();
    }

    public void register(final ServerTool tool) {
	tools.put(tool.getName(), tool);
    }

    public Collection<ServerTool> all() {
	return tools.values();
    }

    public ServerTool get(final String toolName) {
	return tools.get(toolName);

    }

}
