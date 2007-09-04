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

package org.ourproject.kune.platf.server;

import org.ourproject.kune.platf.client.rpc.ContentService;
import org.ourproject.kune.platf.client.rpc.KuneService;
import org.ourproject.kune.platf.server.access.AccessService;
import org.ourproject.kune.platf.server.access.AccessServiceDefault;
import org.ourproject.kune.platf.server.access.FinderService;
import org.ourproject.kune.platf.server.access.FinderServiceDefault;
import org.ourproject.kune.platf.server.content.ContainerManager;
import org.ourproject.kune.platf.server.content.ContainerManagerDefault;
import org.ourproject.kune.platf.server.content.ContentManager;
import org.ourproject.kune.platf.server.content.ContentManagerDefault;
import org.ourproject.kune.platf.server.content.CreationService;
import org.ourproject.kune.platf.server.content.CreationServiceDefault;
import org.ourproject.kune.platf.server.manager.GroupManager;
import org.ourproject.kune.platf.server.manager.LicenseManager;
import org.ourproject.kune.platf.server.manager.SocialNetworkManager;
import org.ourproject.kune.platf.server.manager.ToolConfigurationManager;
import org.ourproject.kune.platf.server.manager.impl.GroupManagerDefault;
import org.ourproject.kune.platf.server.manager.impl.LicenseManagerDefault;
import org.ourproject.kune.platf.server.manager.impl.SocialNetworkManagerDefault;
import org.ourproject.kune.platf.server.manager.impl.ToolConfigurationManagerDefault;
import org.ourproject.kune.platf.server.mapper.DozerMapper;
import org.ourproject.kune.platf.server.mapper.Mapper;
import org.ourproject.kune.platf.server.properties.KuneProperties;
import org.ourproject.kune.platf.server.properties.KunePropertiesDefault;
import org.ourproject.kune.platf.server.rpc.ContentRPC;
import org.ourproject.kune.platf.server.rpc.KuneRPC;
import org.ourproject.kune.platf.server.rpc.SiteBarRPC;
import org.ourproject.kune.platf.server.state.StateService;
import org.ourproject.kune.platf.server.state.StateServiceDefault;
import org.ourproject.kune.platf.server.tool.ToolRegistry;
import org.ourproject.kune.platf.server.users.UserInfoService;
import org.ourproject.kune.platf.server.users.UserInfoServiceDefault;
import org.ourproject.kune.platf.server.users.UserManager;
import org.ourproject.kune.platf.server.users.UserManagerDefault;
import org.ourproject.kune.sitebar.client.rpc.SiteBarService;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

public class PlatformServerModule extends AbstractModule {
    @Override
    protected void configure() {
	install(PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION).buildModule());
	bind(KunePersistenceService.class);

	bindManagers();
	bindRPC();
	bindServices();
	bind(KuneProperties.class).to(KunePropertiesDefault.class);
	bind(Mapper.class).to(DozerMapper.class);
	bind(ToolRegistry.class);
    }

    private void bindServices() {
	bind(UserInfoService.class).to(UserInfoServiceDefault.class);
	bind(CreationService.class).to(CreationServiceDefault.class);
	bind(AccessService.class).to(AccessServiceDefault.class);
	bind(FinderService.class).to(FinderServiceDefault.class);
	bind(StateService.class).to(StateServiceDefault.class);
    }

    private void bindRPC() {
	bind(KuneService.class).to(KuneRPC.class);
	bind(ContentService.class).to(ContentRPC.class);
	bind(SiteBarService.class).to(SiteBarRPC.class);
    }

    private void bindManagers() {
	bind(UserManager.class).to(UserManagerDefault.class);
	bind(GroupManager.class).to(GroupManagerDefault.class);
	bind(ContentManager.class).to(ContentManagerDefault.class);
	bind(ToolConfigurationManager.class).to(ToolConfigurationManagerDefault.class);
	bind(ContainerManager.class).to(ContainerManagerDefault.class);
	bind(LicenseManager.class).to(LicenseManagerDefault.class);
	bind(SocialNetworkManager.class).to(SocialNetworkManagerDefault.class);
    }
}
