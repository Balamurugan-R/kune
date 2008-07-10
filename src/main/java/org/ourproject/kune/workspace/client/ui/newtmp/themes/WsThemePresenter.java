package org.ourproject.kune.workspace.client.ui.newtmp.themes;

import org.ourproject.kune.platf.client.dto.InitDataDTO;
import org.ourproject.kune.platf.client.state.Session;

import com.calclab.suco.client.signal.Signal2;
import com.calclab.suco.client.signal.Slot;
import com.calclab.suco.client.signal.Slot2;

public class WsThemePresenter {

    private WsThemeView view;
    private WsTheme previousTheme;
    private final Signal2<WsTheme, WsTheme> onThemeChanged;

    public WsThemePresenter(final Session session) {
	this.onThemeChanged = new Signal2<WsTheme, WsTheme>("onThemeChanged");
	session.onInitDataReceived(new Slot<InitDataDTO>() {
	    public void onEvent(final InitDataDTO initData) {
		view.setThemes(initData.getWsThemes());
	    }
	});
    }

    public void init(final WsThemeView view) {
	this.view = view;
    }

    public void onThemeChanged(final Slot2<WsTheme, WsTheme> slot) {
	onThemeChanged.add(slot);
    }

    public void setTheme(final WsTheme newTheme) {
	if (previousTheme == null || !previousTheme.equals(newTheme)) {
	    onThemeChanged.fire(previousTheme, newTheme);
	}
	previousTheme = newTheme;
    }

    public void setVisible(final boolean visible) {
	view.setVisible(visible);
    }

}
