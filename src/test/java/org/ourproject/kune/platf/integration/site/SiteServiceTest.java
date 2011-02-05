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
package org.ourproject.kune.platf.integration.site;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.ourproject.kune.platf.integration.IntegrationTest;
import org.ourproject.kune.platf.integration.IntegrationTestHelper;
import org.ourproject.kune.platf.server.UserSession;
import org.ourproject.kune.platf.server.manager.I18nLanguageManager;

import cc.kune.core.client.rpcservices.SiteService;
import cc.kune.core.shared.dto.InitDataDTO;
import cc.kune.core.shared.dto.LicenseDTO;
import cc.kune.domain.Group;

import com.google.inject.Inject;

public class SiteServiceTest extends IntegrationTest {

    @Inject
    SiteService service;
    @Inject
    UserSession session;
    @Inject
    Group groupFinder;
    @Inject
    I18nLanguageManager i18nLangManager;

    @Before
    public void init() {
        new IntegrationTestHelper(this);
    }

    @Test
    public void testGetInitData() throws Exception {
        final InitDataDTO initData = service.getInitData(null);
        assertNotNull(initData);
        assertValidLicenseDTOList(initData.getLicenses());
        assertTrue(initData.getLanguages().size() > 0);
        assertTrue(initData.getCountries().size() > 0);
        assertNotNull(initData.getLanguages().get(0).getCode());
        assertNotNull(initData.getCountries().get(0).getCode());
        assertNotNull(initData.getGroupTools());
        assertNotNull(initData.getUserTools());
        assertTrue(initData.getGroupTools().size() > 0);
        assertTrue(initData.getUserTools().size() > 0);
    }

    private void assertValidLicenseDTOList(final ArrayList<LicenseDTO> licenseList) {
        assertTrue(licenseList.size() > 0);
        for (final Object o : licenseList) {
            assertNotNull(o);
            assertEquals(LicenseDTO.class, o.getClass());
        }
    }

}
