/*
 * Copyright (C) 2007 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * Kune is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kune is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ourproject.kune.platf.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SocialNetworkServiceAsync {

    void requestJoinGroup(String hash, String groupShortName, AsyncCallback callback);

    void AcceptJoinGroup(String hash, String groupToAcceptShortName, String groupShortName, AsyncCallback callback);

    void deleteMember(String hash, String groupToDeleteShortName, String groupShortName, AsyncCallback callback);

    void denyJoinGroup(String hash, String groupToDenyShortName, String groupShortName, AsyncCallback callback);

    void unJoinGroup(String hash, String groupToUnJoinShortName, String groupShortName, AsyncCallback callback);

    void setCollabAsAdmin(String hash, String groupToSetAdminShortName, String groupShortName, AsyncCallback callback);

    void setAdminAsCollab(String hash, String groupToSetCollabShortName, String groupShortName, AsyncCallback callback);

    void addAdminMember(String hash, String groupToAddShortName, String groupShortName, AsyncCallback callback);

    void addCollabMember(String hash, String groupToAddShortName, String groupShortName, AsyncCallback callback);

    void addViewerMember(String hash, String groupToAddShortName, String groupShortName, AsyncCallback callback);

    void getGroupMembers(String hash, String groupShortName, AsyncCallback callback);

    void getParticipation(String hash, String groupShortName, AsyncCallback callback);
}