/*
 *
 * Copyright (C) 2007-2008 The kune development team (see CREDITS for details)
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
package org.ourproject.kune.platf.client.state;

import java.util.List;

import org.ourproject.kune.platf.client.dto.I18nCountryDTO;
import org.ourproject.kune.platf.client.dto.I18nLanguageDTO;
import org.ourproject.kune.platf.client.dto.I18nLanguageSimpleDTO;
import org.ourproject.kune.platf.client.dto.LicenseDTO;
import org.ourproject.kune.platf.client.dto.StateDTO;

public interface Session {

    /**
     * Duration remembering login: 2 weeks
     */
    public static int SESSION_DURATION = 1000 * 60 * 60 * 24 * 14;

    public List<LicenseDTO> getLicenses();

    public void setLicenses(final List<LicenseDTO> licenses);

    public void setCurrent(final StateDTO currentState);

    public StateDTO getCurrentState();

    public void setCurrentState(final StateDTO currentState);

    public void setDefaultWsTheme(final String defaultWsTheme);

    public void setWsThemes(final String[] wsThemes);

    public String[] getWsThemes();

    public String getDefaultWsTheme();

    public boolean isLogged();

    public List<I18nLanguageSimpleDTO> getLanguages();

    public void setLanguages(final List<I18nLanguageSimpleDTO> languages);

    public List<I18nCountryDTO> getCountries();

    public void setCountries(final List<I18nCountryDTO> countries);

    public Object[][] getLanguagesArray();

    public Object[][] getCountriesArray();

    public void setTimezones(final String[] timezones);

    public Object[][] getTimezones();

    public void setCurrentLanguage(final I18nLanguageDTO currentLanguage);

    public I18nLanguageDTO getCurrentLanguage();

    String getUserHash();

    void setUserHash(String userHash);

}