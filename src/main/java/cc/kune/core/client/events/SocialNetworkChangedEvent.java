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
package cc.kune.core.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;

// TODO: Auto-generated Javadoc
/**
 * The Class SocialNetworkChangedEvent.
 *
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class SocialNetworkChangedEvent extends
    GwtEvent<SocialNetworkChangedEvent.SocialNetworkChangedHandler> {

  /**
   * The Interface HasSocialNetworkChangedHandlers.
   *
   * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
   */
  public interface HasSocialNetworkChangedHandlers extends HasHandlers {

    /**
     * Adds the social network changed handler.
     *
     * @param handler
     *          the handler
     * @return the handler registration
     */
    HandlerRegistration addSocialNetworkChangedHandler(SocialNetworkChangedHandler handler);
  }

  /**
   * The Interface SocialNetworkChangedHandler.
   *
   * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
   */
  public interface SocialNetworkChangedHandler extends EventHandler {

    /**
     * On social network changed.
     *
     * @param event
     *          the event
     */
    public void onSocialNetworkChanged(SocialNetworkChangedEvent event);
  }

  /** The Constant TYPE. */
  private static final Type<SocialNetworkChangedHandler> TYPE = new Type<SocialNetworkChangedHandler>();

  /**
   * Fire.
   *
   * @param eventBus
   *          the source
   * @param state
   *          the state
   */
  public static void fire(final EventBus eventBus, final cc.kune.core.shared.dto.StateAbstractDTO state) {
    eventBus.fireEvent(new SocialNetworkChangedEvent(state));
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public static Type<SocialNetworkChangedHandler> getType() {
    return TYPE;
  }

  /** The state. */
  cc.kune.core.shared.dto.StateAbstractDTO state;

  /**
   * Instantiates a new social network changed event.
   */
  protected SocialNetworkChangedEvent() {
    // Possibly for serialization.
  }

  /**
   * Instantiates a new social network changed event.
   *
   * @param state
   *          the state
   */
  public SocialNetworkChangedEvent(final cc.kune.core.shared.dto.StateAbstractDTO state) {
    this.state = state;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
   * .EventHandler)
   */
  @Override
  protected void dispatch(final SocialNetworkChangedHandler handler) {
    handler.onSocialNetworkChanged(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final SocialNetworkChangedEvent other = (SocialNetworkChangedEvent) obj;
    if (state == null) {
      if (other.state != null) {
        return false;
      }
    } else if (!state.equals(other.state)) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
   */
  @Override
  public Type<SocialNetworkChangedHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * Gets the state.
   *
   * @return the state
   */
  public cc.kune.core.shared.dto.StateAbstractDTO getState() {
    return state;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (state == null ? 1 : state.hashCode());
    return hashCode;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.google.web.bindery.event.shared.Event#toString()
   */
  @Override
  public String toString() {
    return "SocialNetworkChangedEvent[" + state + "]";
  }
}
