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
package cc.kune.gspace.client.viewers;

import org.waveprotocol.wave.client.common.util.DateUtils;

import cc.kune.common.client.actions.ui.ActionSimplePanel;
import cc.kune.common.client.actions.ui.bind.GuiProvider;
import cc.kune.common.client.actions.ui.descrip.GuiActionDescrip;
import cc.kune.common.client.actions.ui.descrip.MenuDescriptor;
import cc.kune.core.client.registry.ContentCapabilitiesRegistry;
import cc.kune.core.client.resources.CoreResources;
import cc.kune.core.shared.dto.StateContainerDTO;
import cc.kune.core.shared.i18n.I18nTranslationService;
import cc.kune.gspace.client.GSpaceArmor;
import cc.kune.gspace.client.viewers.FolderViewerPresenter.FolderViewerView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class FolderViewerAsTablePanel extends AbstractFolderViewerPanel {

  interface FolderViewerAsTablePanelUiBinder extends UiBinder<Widget, FolderViewerAsTablePanel> {
  }

  private static FolderViewerAsTablePanelUiBinder uiBinder = GWT.create(FolderViewerAsTablePanelUiBinder.class);

  @UiField
  FlexTable flex;

  private final GuiProvider guiProvider;
  private final CoreResources res;
  protected FolderItemWidget selected;

  @Inject
  public FolderViewerAsTablePanel(final GSpaceArmor gsArmor, final I18nTranslationService i18n,
      final GuiProvider guiProvider, final CoreResources res,
      final ContentCapabilitiesRegistry capabilitiesRegistry) {
    super(gsArmor, i18n, capabilitiesRegistry);
    this.guiProvider = guiProvider;
    this.res = res;
    widget = uiBinder.createAndBindUi(this);
    widget.addStyleName("k-folder-viewer");
  }

  @Override
  public void addItem(final FolderItemDescriptor item, final ClickHandler clickHandler,
      final DoubleClickHandler doubleClickHandler) {
    final int rowCount = flex.getRowCount();
    final FolderItemWidget itemWidget = new FolderItemWidget((ImageResource) item.getIcon(),
        item.getText());
    final ActionSimplePanel toolbar = new ActionSimplePanel(guiProvider);
    final long modifiedOn = item.getModififiedOn();
    if (modifiedOn != FolderViewerView.NO_DATE) {
      // String dateFormat = session.getCurrentLanguage().getDateFormatShort();
      itemWidget.setModifiedText(DateUtils.getInstance().formatPastDate(modifiedOn));
    }
    itemWidget.setMenu(toolbar);
    // Tooltip.to(itemWidget, item.getTooltip());
    final MenuDescriptor menu = new MenuDescriptor(i18n.t("Actions"));
    menu.withIcon(res.arrowdown()).withStyles("k-def-docbtn, k-btn, k-button");
    menu.setStandalone(false);
    itemWidget.setMenuVisible(false);
    toolbar.add(menu);
    for (final GuiActionDescrip menuItem : item.getActionCollection()) {
      menuItem.setParent(menu);
      toolbar.add(menuItem);
    }
    itemWidget.getRowClick().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        if (selected != null) {
          selected.setSelect(false);
        }
        itemWidget.setSelect(true);
        selected = itemWidget;
      }
    });
    itemWidget.getRowDoubleClick().addDoubleClickHandler(doubleClickHandler);
    itemWidget.getRowMouse().addMouseOutHandler(new MouseOutHandler() {
      @Override
      public void onMouseOut(final MouseOutEvent event) {
        itemWidget.setMenuVisible(false);
        // menu.setVisible(false);
        // itemWidget.setSelect(false);
        // menu.hide();
      }
    });
    itemWidget.getRowMouse().addMouseOverHandler(new MouseOverHandler() {
      @Override
      public void onMouseOver(final MouseOverEvent event) {
        // menu.setVisible(true);
        itemWidget.setMenuVisible(true);
      }
    });
    flex.setWidget(rowCount + 1, 0, itemWidget);
  }

  @Override
  public void clear() {
    flex.removeAllRows();
    super.clear();
  }

  @Override
  public void setContainer(final StateContainerDTO state) {
    super.setContainer(state);
  }

}