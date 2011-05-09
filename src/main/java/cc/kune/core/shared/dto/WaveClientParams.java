/*
 *
 * Copyright (C) 2007-2011 The kune development team (see CREDITS for details)
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
package cc.kune.core.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class WaveClientParams implements IsSerializable {
    private String clientFlags;
    private String sessionJSON;
    private boolean useSocketIO;

    public WaveClientParams() {
    }

    public WaveClientParams(final String sessionJSON, final String clientFlags, final boolean useSocketIO) {
        this.sessionJSON = sessionJSON;
        this.clientFlags = clientFlags;
        this.useSocketIO = useSocketIO;
    }

    public String getClientFlags() {
        return clientFlags;
    }

    public String getSessionJSON() {
        return sessionJSON;
    }

    public boolean useSocketIO() {
        return useSocketIO;
    }

    public void setClientFlags(final String clientFlags) {
        this.clientFlags = clientFlags;
    }

    public void setSessionJSON(final String sessionJSON) {
        this.sessionJSON = sessionJSON;
    }

    public void setUseSocketIO(final boolean useSocketIO) {
        this.useSocketIO = useSocketIO;
    }

}