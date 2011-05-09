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

import cc.kune.common.client.actions.ui.ActionSimplePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class FolderItemWidget extends Composite implements HasText {

  interface FolderItemWidgetUiBinder extends UiBinder<Widget, FolderItemWidget> {
  }

  private static FolderItemWidgetUiBinder uiBinder = GWT.create(FolderItemWidgetUiBinder.class);

  @UiField
  FlowPanel flow;
  @UiField
  FocusPanel focusPanel;
  @UiField
  Image icon;
  @UiField
  SimplePanel menu;
  @UiField
  InlineLabel modified;
  @UiField
  InlineLabel title;

  public FolderItemWidget(final ImageResource iconResource, final String title) {
    initWidget(uiBinder.createAndBindUi(this));
    this.title.setText(title);
    icon.setResource(iconResource);
  }

  private void clearFocusStyles() {
    focusPanel.removeStyleDependentName("nofocused");
    focusPanel.removeStyleDependentName("focused");
  }

  HasClickHandlers getRowClick() {
    return focusPanel;
  }

  HasDoubleClickHandlers getRowDoubleClick() {
    return focusPanel;
  }

  HasAllFocusHandlers getRowFocus() {
    return focusPanel;
  }

  HasAllMouseHandlers getRowMouse() {
    return focusPanel;
  }

  @Override
  public String getText() {
    return title.getText();
  }

  @UiHandler("focusPanel")
  public void onBlur(final BlurEvent event) {
    clearFocusStyles();
    focusPanel.addStyleDependentName("nofocused");
  }

  @UiHandler("focusPanel")
  public void onFocus(final FocusEvent event) {
    clearFocusStyles();
    focusPanel.addStyleDependentName("focused");
  }

  @UiHandler("focusPanel")
  public void onOut(final MouseOutEvent event) {
    clearFocusStyles();
    focusPanel.addStyleDependentName("nofocused");
  }

  @UiHandler("focusPanel")
  public void onOver(final MouseOverEvent event) {
    clearFocusStyles();
    focusPanel.addStyleDependentName("focused");
  }

  public void setMenu(final ActionSimplePanel toolbar) {
    menu.add(toolbar);
  }

  public void setMenuVisible(final boolean visible) {
    menu.getElement().getStyle().setOpacity(visible ? 1d : 0.2d);
  }

  public void setModifiedText(final String text) {
    modified.setText(text);
  }

  public void setSelect(final boolean selected) {
    clearFocusStyles();
    focusPanel.removeStyleDependentName(selected ? "noselected" : "selected");
    focusPanel.addStyleDependentName(selected ? "selected" : "noselected");
  }

  @Override
  public void setText(final String text) {
    title.setText(text);
  }

}
