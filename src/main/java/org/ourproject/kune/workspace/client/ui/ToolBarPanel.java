package org.ourproject.kune.workspace.client.ui;

import org.ourproject.kune.platf.client.ui.CustomButton;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.gwtext.client.widgets.Button;

public class ToolBarPanel extends HorizontalPanel {
    private Button btnEnter;

    public ToolBarPanel() {
	Label expand = new Label("");
	this.add(expand);
	this.setWidth("100%");
	expand.setWidth("100%");
	this.setCellWidth(expand, "100%");
	this.addStyleName("kune-DocumentReaderPanel");
    }

    public void addButton(final String caption, final ClickListener listener) {
	btnEnter = new CustomButton(caption, listener).getButton();
	this.add(btnEnter);
	btnEnter.addStyleName("kune-Button-Large-lrSpace");
    }

    public void setButtonVisible(final boolean isEnabled) {
	btnEnter.setVisible(isEnabled);
    }

}