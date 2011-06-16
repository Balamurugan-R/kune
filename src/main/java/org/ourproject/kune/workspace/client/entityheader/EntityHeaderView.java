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
 \*/
package org.ourproject.kune.workspace.client.entityheader;

import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.actions.ui.OldGuiActionDescrip;

import cc.kune.core.shared.domain.utils.StateToken;
import cc.kune.core.shared.dto.GSpaceTheme;
import cc.kune.core.shared.dto.GroupDTO;

public interface EntityHeaderView {

    void addAction(OldGuiActionDescrip descriptor);

    void addWidget(View view);

    void reloadImage(GroupDTO group);

    void setLargeFont();

    void setLogoImage(StateToken stateToken);

    void setLogoImageVisible(boolean visible);

    void setLogoText(final String groupName);

    void setMediumFont();

    void setSmallFont();

    void setTheme(final GSpaceTheme oldTheme, GSpaceTheme newTheme);

    void showDefUserLogo();
}
