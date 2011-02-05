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
package org.ourproject.kune.platf.integration.content;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.ourproject.kune.platf.integration.IntegrationTestHelper;
import org.ourproject.kune.platf.server.TestDomainHelper;

import cc.kune.core.shared.dto.StateContentDTO;

public class ContentServiceSaveTest extends ContentServiceIntegrationTest {

    private StateContentDTO defaultContent;

    @Before
    public void init() throws Exception {
        new IntegrationTestHelper(this);
        defaultContent = getSiteDefaultContent();
        doLogin();
    }

    @Test
    public void testSaveAndRetrieve() throws Exception {
        final String text = "Lorem ipsum dolor sit amet";
        final int version = defaultContent.getVersion();
        contentService.save(getHash(), defaultContent.getStateToken(), text);
        final StateContentDTO again = (StateContentDTO) contentService.getContent(getHash(),
                defaultContent.getStateToken());
        assertEquals(text, again.getContent());
        assertEquals(version + 1, again.getVersion());
        assertEquals(0, again.getRateByUsers().intValue());
        assertEquals(new Double(0), again.getRate());
    }

    @Test
    public void testSaveAndRetrieveBig() throws Exception {
        final String text = TestDomainHelper.createBigText();
        final int version = defaultContent.getVersion();
        contentService.save(getHash(), defaultContent.getStateToken(), text);
        final StateContentDTO again = (StateContentDTO) contentService.getContent(getHash(),
                defaultContent.getStateToken());
        assertEquals(version + 1, again.getVersion());
        assertEquals(text, again.getContent());
    }

}
