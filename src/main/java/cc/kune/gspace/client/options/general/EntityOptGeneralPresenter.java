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
package cc.kune.gspace.client.options.general;

import cc.kune.core.client.state.Session;
import cc.kune.core.client.state.StateManager;
import cc.kune.core.shared.i18n.I18nTranslationService;
import cc.kune.gspace.client.options.EntityOptions;
import cc.kune.gspace.client.options.logo.CurrentEntityChangedEvent;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;

public abstract class EntityOptGeneralPresenter {

  protected final EntityOptions entityOptions;
  private final EventBus eventBus;
  protected final I18nTranslationService i18n;
  protected final Session session;
  protected final StateManager stateManager;
  protected EntityOptGeneralView view;

  public EntityOptGeneralPresenter(final Session session, final StateManager stateManager,
      final EventBus eventBus, final I18nTranslationService i18n, final EntityOptions entityOptions) {
    this.session = session;
    this.stateManager = stateManager;
    this.eventBus = eventBus;
    this.i18n = i18n;
    this.entityOptions = entityOptions;
  }

  protected abstract boolean applicable();

  public IsWidget getView() {
    return view;
  }

  public void init(final EntityOptGeneralView view) {
    this.view = view;
    setState();
    entityOptions.addTab(view, view.getTabTitle());
    view.setChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(final ChangeEvent event) {
        updateInServer();
      }
    });
  }

  protected void reset() {
    view.clear();
    entityOptions.hideMessages();
  }

  protected void sendChangeEntityEvent() {
    CurrentEntityChangedEvent.fire(eventBus);
  }

  protected abstract void setState();

  protected abstract void updateInServer();

}
