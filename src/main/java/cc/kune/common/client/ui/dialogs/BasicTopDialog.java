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
package cc.kune.common.client.ui.dialogs;

import cc.kune.common.client.ui.PopupTopPanel;
import cc.kune.common.client.utils.TextUtils;

import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;

public class BasicTopDialog extends BasicDialog {

  public static class Builder {
    private final boolean autohide;
    private boolean autoscroll = false;
    private final String dialogId;
    private String firstButtonId;
    private String firstButtonTitle;
    private String height = "70%";
    private String icon;
    private final boolean modal;
    private String sndButtonId;
    private String sndButtonTitle;
    private int tabIndexStart = 0;
    private String title;
    private String width;

    public Builder(final String dialogId, final boolean autohide, final boolean modal) {
      // Required params
      this.autohide = autohide;
      this.modal = modal;
      this.dialogId = dialogId;
    }

    public Builder autoscroll(final boolean autoscroll) {
      // Not used for now
      this.autoscroll = autoscroll;
      return this;
    }

    public BasicTopDialog build() {
      return new BasicTopDialog(this);
    }

    public Builder firstButtonId(final String firstButtonId) {
      this.firstButtonId = firstButtonId;
      return this;
    }

    public Builder firstButtonTitle(final String firstButtonTitle) {
      this.firstButtonTitle = firstButtonTitle;
      return this;
    }

    public Builder height(final int height) {
      // FIXME
      this.height = String.valueOf(height); // + "px";
      return this;
    }

    public Builder height(final String height) {
      this.height = height;
      return this;
    }

    public Builder icon(final String icon) {
      this.icon = icon;
      return this;
    }

    public Builder sndButtonId(final String sndButtonId) {
      this.sndButtonId = sndButtonId;
      return this;
    }

    public Builder sndButtonTitle(final String sndButtonTitle) {
      this.sndButtonTitle = sndButtonTitle;
      return this;
    }

    public Builder tabIndexStart(final int tabIndexStart) {
      this.tabIndexStart = tabIndexStart;
      return this;
    }

    public Builder title(final String title) {
      this.title = title;
      return this;
    }

    public Builder width(final int width) {
      // FIXME
      this.width = String.valueOf(width); // + "px";
      return this;
    }

    public Builder width(final String width) {
      this.width = width;
      return this;
    }

  }

  private String height;
  private final PopupTopPanel popup;
  private String width;

  private BasicTopDialog(final Builder builder) {
    popup = new PopupTopPanel(builder.autohide, builder.modal);
    popup.add(this);
    popup.ensureDebugId(builder.dialogId);
    super.getTitleText().setText(builder.title);
    if (TextUtils.notEmpty(builder.icon)) {
      super.setTitleIcon(builder.icon);
    }
    super.setFirstBtnText(builder.firstButtonTitle);
    super.setFirstBtnId(builder.firstButtonId);
    super.setFirstBtnTabIndex(builder.tabIndexStart);
    super.setSecondBtnText(builder.sndButtonTitle);
    super.setSecondBtnId(builder.sndButtonId);
    super.setSecondBtnTabIndex(builder.tabIndexStart + 1);
    super.getTitleText().setText(builder.title);
    this.setWidth(builder.width);
    this.setHeight(builder.height);
    setSizes();
    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(final ResizeEvent event) {
        setSizes(event.getWidth(), event.getHeight());
      }
    });
  }

  private String calculatePercent(final int currentSize, final String percent) {
    return String.valueOf(currentSize * Integer.valueOf(percent.replace("%", "")) / 100) + "px";
  }

  public HasCloseHandlers<PopupPanel> getClose() {
    return popup;
  }

  public void hide() {
    popup.hide();
  }

  @Override
  public void setFirstBtnTitle(final String title) {
    super.setFirstBtnTitle(title);
  }

  @Override
  public void setHeight(final String height) {
    this.height = height;
    setSizes();
  }

  @Override
  public void setSize(final String width, final String height) {
    this.width = width;
    this.height = height;
    setSizes();
  }

  private void setSizes() {
    setSizes(Window.getClientWidth(), Window.getClientHeight());
  }

  private void setSizes(final int windowWidth, final int windowHeight) {
    String newWidth = null;
    String newHeight = null;
    if (width != null) {
      if (width.contains("%")) {
        newWidth = calculatePercent(windowWidth, width);
      } else {
        newWidth = width;
      }
    }
    if (height != null) {
      if (height.contains("%")) {
        newHeight = calculatePercent(windowHeight, height);
      } else {
        newHeight = height;
      }
    }
    if (newWidth != null) {
      super.setInnerWidth(newWidth);
    }
    if (newHeight != null) {
      super.setInnerHeight(newHeight);
    }
  }

  @Override
  public void setWidth(final String width) {
    this.width = width;
    setSizes();
  }

  public void showCentered() {
    popup.showCentered();
  }

  public void showRelativeTo(final UIObject object) {
    popup.showRelativeTo(object);
  }
}