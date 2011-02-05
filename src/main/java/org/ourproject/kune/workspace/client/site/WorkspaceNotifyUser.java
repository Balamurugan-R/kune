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
package org.ourproject.kune.workspace.client.site;

import org.ourproject.kune.platf.client.ui.noti.ConfirmationAsk;
import org.ourproject.kune.platf.client.ui.noti.NotifyUser;
import org.ourproject.kune.platf.client.ui.noti.NotifyUser.Level;
import org.ourproject.kune.workspace.client.site.msg.ToastMessage;
import org.ourproject.kune.workspace.client.sitebar.siteprogress.SiteProgress;
import org.ourproject.kune.workspace.client.skel.WorkspaceSkeleton;

import cc.kune.core.shared.i18n.I18nTranslationService;

import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener0;
import com.calclab.suco.client.events.Listener2;
import com.calclab.suco.client.ioc.Provider;

public class WorkspaceNotifyUser {

    public WorkspaceNotifyUser(NotifyUser notifyUser, final I18nTranslationService i18n,
            final SiteProgress siteProgress, final Provider<ToastMessage> toaster,
            final Provider<WorkspaceSkeleton> ws) {
        notifyUser.addProgressNotifier(new Listener<String>() {
            public void onEvent(String message) {
                siteProgress.showProgress(message);
            }
        });
        notifyUser.addHideProgressNotifier(new Listener0() {
            public void onEvent() {
                siteProgress.hideProgress();
            }
        });
        notifyUser.addNotifier(new Listener2<Level, String>() {
            public void onEvent(Level level, String msg) {
                String title = "";
                switch (level) {
                case error:
                    title = i18n.t("Error");
                    break;
                case important:
                    title = i18n.t("Important");
                    break;
                case veryImportant:
                    title = i18n.t("Alert");
                    break;
                case info:
                    title = i18n.t("Info");
                    break;
                }
                toaster.get().showMessage(title, msg, level);
            }
        });
        notifyUser.addAlerter(new Listener2<String, String>() {
            public void onEvent(String title, String msg) {
                ws.get().showAlertMessage(title, msg);
            }
        });
        notifyUser.addConfirmationAsker(new Listener<ConfirmationAsk>() {
            public void onEvent(ConfirmationAsk ask) {
                ws.get().askConfirmation(ask.getTitle(), ask.getMessage(), ask.getOnConfirmed(), ask.getOnCancel());
            }
        });
    }

}
