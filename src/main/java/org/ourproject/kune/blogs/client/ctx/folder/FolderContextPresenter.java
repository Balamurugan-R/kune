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
package org.ourproject.kune.blogs.client.ctx.folder;

import org.ourproject.kune.blogs.client.BlogClientTool;
import org.ourproject.kune.docs.client.actions.DocsEvents;
import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.dto.AccessRightsDTO;
import org.ourproject.kune.platf.client.dto.ContainerDTO;
import org.ourproject.kune.platf.client.dto.StateToken;
import org.ourproject.kune.platf.client.services.Kune;
import org.ourproject.kune.workspace.client.ui.ctx.items.ContextItems;
import org.ourproject.kune.workspace.client.ui.ctx.items.ContextItemsImages;

public class FolderContextPresenter implements FolderContext {
    private final ContextItems contextItems;

    public FolderContextPresenter(final ContextItems contextItems) {
        this.contextItems = contextItems;
        ContextItemsImages contextImages = ContextItemsImages.App.getInstance();
        contextItems.registerType(BlogClientTool.TYPE_DOCUMENT, contextImages.pageWhite());
        contextItems.registerType(BlogClientTool.TYPE_FOLDER, contextImages.folder());
        // FIXME: blogs
        contextItems.canCreate(BlogClientTool.TYPE_DOCUMENT, Kune.I18N.t("New post"), DocsEvents.ADD_DOCUMENT);
        contextItems.canCreate(BlogClientTool.TYPE_FOLDER, Kune.I18N.t("New blog"), DocsEvents.ADD_FOLDER);
        contextItems.setParentTreeVisible(true);
    }

    public View getView() {
        return contextItems.getView();
    }

    public void setContainer(final StateToken state, final ContainerDTO container, final AccessRightsDTO rights) {
        contextItems.showContainer(state, container, rights);
    }

}
