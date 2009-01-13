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
 */package org.ourproject.kune.workspace.client.sitebar.sitesign;

import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.dto.UserInfoDTO;
import org.ourproject.kune.platf.client.state.Session;

import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener0;

public class SiteSignInLinkPresenter implements SiteSignInLink {

    private SiteSignInLinkView view;

    public SiteSignInLinkPresenter(final Session session) {
        session.onUserSignIn(new Listener<UserInfoDTO>() {
            public void onEvent(final UserInfoDTO user) {
                view.setVisible(false);
            }
        });
        session.onUserSignOut(new Listener0() {
            public void onEvent() {
                view.setVisible(true);
            }
        });
    }

    public View getView() {
        return view;
    }

    public void init(final SiteSignInLinkView view) {
        this.view = view;
        view.setVisible(false);
    }

}
