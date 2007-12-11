/*
 *
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

package org.ourproject.kune.sitebar.client.rpc;

import org.ourproject.kune.platf.client.dto.LicenseDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

    void login(String nickOrEmail, String passwd, AsyncCallback callback);

    void logout(AsyncCallback callback);

    void createUser(String shortName, String longName, String email, String paswd, LicenseDTO license, String language,
            String country, String timezone, AsyncCallback asyncCallback);

    void reloadUserInfo(String userHash, AsyncCallback asyncCallback);

}