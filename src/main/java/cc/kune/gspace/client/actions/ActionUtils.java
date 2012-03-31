/*
 *
 * Copyright (C) 2007-2012 The kune development team (see CREDITS for details)
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

package cc.kune.gspace.client.actions;

import cc.kune.common.client.actions.ActionEvent;
import cc.kune.core.client.state.Session;
import cc.kune.core.shared.domain.utils.StateToken;
import cc.kune.core.shared.dto.AbstractContentSimpleDTO;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ActionUtils {
  @Inject
  private static Session session;

  public static StateToken getActionToken(final ActionEvent event) {
    final boolean inContent = session.getCurrentStateToken().hasAll();
    final StateToken token = inContent ? session.getCurrentStateToken()
        : ((AbstractContentSimpleDTO) event.getTarget()).getStateToken();
    return token;
  }
}