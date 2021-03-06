/*
 *
 * Copyright (C) 2007-2014 Licensed to the Comunes Association (CA) under
 * one or more contributor license agreements (see COPYRIGHT for details).
 * The CA licenses this file to you under the GNU Affero General Public
 * License version 3, (the "License"); you may not use this file except in
 * compliance with the License. This file is part of kune.
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
package cc.kune.core.client.state;

import cc.kune.core.shared.domain.utils.StateToken;
import cc.kune.core.shared.dto.StateAbstractDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContentCache.
 * 
 * @author danigb@gmail.com
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public interface ContentCache {

  /**
   * Cache.
   * 
   * @param token
   *          the token
   * @param content
   *          the content
   */
  void cache(StateToken token, StateAbstractDTO content);

  /**
   * Gets the content.
   * 
   * @param user
   *          the user
   * @param newState
   *          the new state
   * @param callback
   *          the callback
   * @return the content
   */
  void getContent(String user, StateToken newState, AsyncCallback<StateAbstractDTO> callback);

  /**
   * Removes the.
   * 
   * @param token
   *          the token
   */
  void remove(StateToken token);

  /**
   * Removes the cache of group.
   * 
   * @param group
   *          the group
   */
  void removeCacheOfGroup(String group);

}
