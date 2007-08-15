package org.ourproject.kune.docs.client.actions;

import org.ourproject.kune.platf.client.dto.FolderDTO;
import org.ourproject.kune.platf.client.dto.GroupDTO;
import org.ourproject.kune.platf.client.rpc.ContentService;
import org.ourproject.kune.platf.client.rpc.ContentServiceAsync;
import org.ourproject.kune.sitebar.client.Site;
import org.ourproject.kune.workspace.client.dto.ContentDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddFolder extends AbstractAddAction {
    public static final String KEY = "docs.addFolder";
    GroupDTO group;

    public void execute(final Object value, final Object extra) {
	group = (GroupDTO) extra;
	// i18n
	showNewDocDialog((FolderDTO) value, "create new folder");
    }

    protected void add() {
	// i18n
	Site.showProgress("adding document");
	ContentServiceAsync server = ContentService.App.getInstance();
	String name = form.getName();
	server.addFolder(user, group.getShortName(), folderDTO.getId(), name, new AsyncCallback() {
	    public void onFailure(final Throwable caught) {
	    }

	    public void onSuccess(final Object result) {
		ContentDTO content = (ContentDTO) result;
		stateManager.setState(content);
	    }
	});
    }
}