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
package org.ourproject.kune.platf.server.manager;

import java.util.HashMap;
import java.util.List;

import org.ourproject.kune.platf.server.domain.I18nTranslation;
import org.ourproject.kune.platf.server.manager.impl.SearchResult;

import com.google.gwt.user.client.rpc.SerializableException;

public interface I18nTranslationManager extends Manager<I18nTranslation, Long> {

    HashMap<String, String> getLexicon(String language);

    List<I18nTranslation> getUntranslatedLexicon(String language);

    List<I18nTranslation> getTranslatedLexicon(String language);

    String getTranslation(String language, String text);

    String getTranslation(String language, String text, String arg);

    String getTranslation(String language, String text, Integer arg);

    void setTranslation(String language, String text, String translation);

    String setTranslation(String id, String translation) throws SerializableException;

    SearchResult<I18nTranslation> getUntranslatedLexicon(String language, Integer firstResult, Integer maxResults);

    SearchResult<I18nTranslation> getTranslatedLexicon(String language, Integer firstResult, Integer maxResults);

}
