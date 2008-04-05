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
package org.ourproject.kune.chat.client;

import java.util.Date;

import com.allen_sauer.gwt.log.client.Log;
import com.calclab.gwtjsjac.client.Debugger;
import com.calclab.gwtjsjac.client.PresenceShow;
import com.calclab.gwtjsjac.client.XmppConnection;
import com.calclab.gwtjsjac.client.XmppFactory;
import com.calclab.gwtjsjac.client.XmppUserSettings;
import com.calclab.gwtjsjac.client.impl.JsJacFactory;
import com.calclab.gwtjsjac.client.log.GWTLoggerOutput;
import com.calclab.gwtjsjac.client.mandioca.XmppSession;
import com.calclab.gwtjsjac.client.mandioca.rooms.XmppRoom;

class ChatEngineXmpp implements ChatEngine {
    private XmppSession session;
    private final ChatState state;
    private final XmppConnection connection;

    public ChatEngineXmpp(final ChatState state) {
        this.state = state;
        XmppFactory factory = JsJacFactory.getInstance();
        connection = factory.createBindingConnection(state.httpBase, 2000, GWTLoggerOutput.instance);
        Debugger.debug(connection, new LoggerOutputImpl());
    }

    public ChatState getState() {
        return state;
    }

    public void login(final String chatName, final String chatPassword) {
        Log.debug("LOGIN CHAT: " + chatName + "[" + chatPassword + "]");
        state.user = new XmppUserSettings(state.domain, chatName, chatPassword, XmppUserSettings.NON_SASL);
        state.user.resource = "kuneClient" + new Date().getTime();
        session = new XmppSession(connection, true);
        session.login(state.user);
        // FIXME: hardcoded
        session.getUser().sendPresence(PresenceShow.CHAT, ":: ready ::");
    }

    public void logout() {
        // FIXME: bug
        // this$static has no properties
        // [Break on this error] if (this$static.session !== null) {
        if (session != null) {
            session.logout();
        }
    }

    public XmppRoom joinRoom(final String roomName, final String userAlias) {
        return session.joinRoom(state.roomHost, roomName, userAlias);
    }

}
