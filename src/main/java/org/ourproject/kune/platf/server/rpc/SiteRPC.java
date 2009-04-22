/*
 *
 * Copyright (C) 2007-2009 The kune development team (see CREDITS for details)
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
package org.ourproject.kune.platf.server.rpc;

import java.util.TimeZone;

import org.ourproject.kune.platf.client.dto.InitDataDTO;
import org.ourproject.kune.platf.client.errors.DefaultException;
import org.ourproject.kune.platf.client.rpc.SiteService;
import org.ourproject.kune.platf.server.InitData;
import org.ourproject.kune.platf.server.UserSession;
import org.ourproject.kune.platf.server.manager.I18nCountryManager;
import org.ourproject.kune.platf.server.manager.I18nLanguageManager;
import org.ourproject.kune.platf.server.manager.LicenseManager;
import org.ourproject.kune.platf.server.manager.UserManager;
import org.ourproject.kune.platf.server.mapper.Mapper;
import org.ourproject.kune.platf.server.properties.ChatProperties;
import org.ourproject.kune.platf.server.properties.KuneProperties;
import org.ourproject.kune.platf.server.tool.ServerToolRegistry;
import org.ourproject.kune.platf.server.users.UserInfoService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.TransactionType;
import com.wideplay.warp.persist.Transactional;

@Singleton
public class SiteRPC implements RPC, SiteService {
    private final Mapper mapper;
    private final Provider<UserSession> userSessionProvider;
    private final LicenseManager licenseManager;
    private final UserManager userManager;
    private final ChatProperties chatProperties;
    private final UserInfoService userInfoService;
    private final KuneProperties kuneProperties;
    private final I18nLanguageManager languageManager;
    private final I18nCountryManager countryManager;
    private final ServerToolRegistry serverToolRegistry;

    // TODO: refactor: too many parameters! refactor to Facade Pattern
    @Inject
    public SiteRPC(final Provider<UserSession> userSessionProvider, final UserManager userManager,
            final UserInfoService userInfoService, final LicenseManager licenseManager, final Mapper mapper,
            final KuneProperties kuneProperties, final ChatProperties chatProperties,
            final I18nLanguageManager languageManager, final I18nCountryManager countryManager,
            final ServerToolRegistry serverToolRegistry) {
        this.userSessionProvider = userSessionProvider;
        this.userManager = userManager;
        this.userInfoService = userInfoService;
        this.licenseManager = licenseManager;
        this.mapper = mapper;
        this.kuneProperties = kuneProperties;
        this.chatProperties = chatProperties;
        this.languageManager = languageManager;
        this.countryManager = countryManager;
        this.serverToolRegistry = serverToolRegistry;
    }

    @Transactional(type = TransactionType.READ_ONLY)
    public InitDataDTO getInitData(final String userHash) throws DefaultException {
        final InitData data = new InitData();
        final UserSession userSession = getUserSession();

        data.setSiteUrl(kuneProperties.get(KuneProperties.SITE_URL));
        data.setLicenses(licenseManager.getAll());
        data.setLanguages(languageManager.getAll());
        data.setCountries(countryManager.getAll());
        data.setTimezones(TimeZone.getAvailableIDs());
        data.setUserInfo(userInfoService.buildInfo(userManager.find(userSession.getUser().getId()),
                userSession.getHash()));
        data.setChatHttpBase(chatProperties.getHttpBase());
        data.setChatDomain(chatProperties.getDomain());
        data.setSiteDomain(kuneProperties.get(KuneProperties.SITE_DOMAIN));
        data.setChatRoomHost(chatProperties.getRoomHost());
        data.setWsThemes(this.kuneProperties.get(KuneProperties.WS_THEMES).split(","));
        data.setDefaultLicense(licenseManager.getDefLicense());
        data.setCurrentCCversion(this.kuneProperties.get(KuneProperties.CURRENT_CC_VERSION));
        data.setDefaultWsTheme(this.kuneProperties.get(KuneProperties.WS_THEMES_DEF));
        data.setSiteLogoUrl(kuneProperties.get(KuneProperties.SITE_LOGO_URL));
        data.setGalleryPermittedExtensions(kuneProperties.get(KuneProperties.UPLOAD_GALLERY_PERMITTED_EXTS));
        data.setMaxFileSizeInMb(kuneProperties.get(KuneProperties.UPLOAD_MAX_FILE_SIZE));
        data.setUserTools(serverToolRegistry.getToolsForUsers());
        data.setGroupTools(serverToolRegistry.getToolsForGroups());
        data.setImgResizewidth(new Integer(kuneProperties.get(KuneProperties.IMAGES_RESIZEWIDTH)));
        data.setImgThumbsize(new Integer(kuneProperties.get(KuneProperties.IMAGES_THUMBSIZE)));
        data.setImgCropsize(new Integer(kuneProperties.get(KuneProperties.IMAGES_CROPSIZE)));
        data.setImgIconsize(new Integer(kuneProperties.get(KuneProperties.IMAGES_ICONSIZE)));
        data.setFlvEmbedObject(kuneProperties.get(KuneProperties.FLV_EMBEDED_OBJECT));
        data.setMp3EmbedObject(kuneProperties.get(KuneProperties.MP3_EMBEDED_OBJECT));
        data.setOggEmbedObject(kuneProperties.get(KuneProperties.OGG_EMBEDED_OBJECT));
        data.setAviEmbedObject(kuneProperties.get(KuneProperties.AVI_EMBEDED_OBJECT));
        return mapper.map(data, InitDataDTO.class);
    }

    private UserSession getUserSession() {
        return userSessionProvider.get();
    }
}
