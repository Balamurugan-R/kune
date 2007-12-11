/*
 *
 * Copyright (C) 2007 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * Kune is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kune is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ourproject.kune.platf.client.newgroup;

import org.ourproject.kune.platf.client.services.Kune;
import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.dto.GroupDTO;
import org.ourproject.kune.platf.client.dto.LicenseDTO;
import org.ourproject.kune.platf.client.dto.StateToken;
import org.ourproject.kune.platf.client.errors.GroupNameInUseException;
import org.ourproject.kune.platf.client.rpc.GroupService;
import org.ourproject.kune.platf.client.rpc.GroupServiceAsync;
import org.ourproject.kune.sitebar.client.Site;
import org.ourproject.kune.sitebar.client.msg.MessagePresenter;
import org.ourproject.kune.sitebar.client.msg.SiteMessage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class NewGroupPresenter implements NewGroup, MessagePresenter {
    private final NewGroupListener listener;
    private NewGroupView view;

    public NewGroupPresenter(final NewGroupListener listener) {
        this.listener = listener;
    }

    public void init(final NewGroupView view) {
        this.view = view;
    }

    public void onFinish() {
        GroupServiceAsync groupService = GroupService.App.getInstance();
        String shortName = view.getShortName();
        String longName = view.getLongName();
        String publicDesc = view.getPublicDesc();

        LicenseDTO license = view.getLicense();
        GroupDTO group = new GroupDTO(shortName, longName, publicDesc, getTypeOfGroup());
        group.setDefaultLicense(license);
        // FIXME: get User hash
        groupService.createNewGroup("FIXMEFIXME", group, new AsyncCallback() {
            public void onFailure(final Throwable caught) {
                Site.hideProgress();
                try {
                    throw caught;
                } catch (final GroupNameInUseException e) {
                    onBack();
                    setMessage(Kune.I18N.t("This name in already in use, try with a different name."),
                            SiteMessage.ERROR);
                } catch (final Throwable e) {
                    onBack(); // The messageP is in first page of wizard :-/
                    setMessage(Kune.I18N.t("Error creating group"), SiteMessage.ERROR);
                    GWT.log("Other kind of exception in group registration", null);
                    throw new RuntimeException();
                }
            }

            public void onSuccess(final Object token) {
                listener.onNewGroupCreated((StateToken) token);
                view.hide();
                reset();
            }
        });
    }

    public View getView() {
        return view;
    }

    private String getTypeOfGroup() {
        if (view.isProject()) {
            return GroupDTO.PROJECT;
        } else if (view.isOrganization()) {
            return GroupDTO.ORGANIZATION;
        } else {
            return GroupDTO.COMMUNITY;
        }
    }

    private void reset() {
        view.clearData();
    }

    public void onBack() {
        view.setEnabledBackButton(false);
        view.setEnabledFinishButton(false);
        view.setEnabledNextButton(true);
        view.showNewGroupInitialDataForm();
    }

    public void onCancel() {
        view.hide();
        reset();
        listener.onNewGroupCancel();
    }

    public void onClose() {
        reset();
        listener.onNewGroupClose();
    }

    public void onNext() {
        if (view.isFormValid()) {
            view.setEnabledBackButton(true);
            view.setEnabledFinishButton(true);
            view.setEnabledNextButton(false);
            view.showLicenseForm();
        }
    }

    public void onChange() {
        // This doesn't work perfect (don't use now):
        // if (view.isFormValid()) {
        // view.setEnabledNextButton(true);
        // } else {
        // view.setEnabledNextButton(false);
        // }
    }

    public void onMessageClose() {
        // From MessagePresenter: do nothing
    }

    public void resetMessage() {
        view.hideMessage();
    }

    public void setMessage(final String message, final int type) {
        view.setMessage(message, type);
    }
}