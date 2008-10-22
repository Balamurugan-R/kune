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

package org.ourproject.kune.platf.server.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.Length;
import org.hibernate.validator.Pattern;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

/**
 * GlobalizeLanguages generated by hbm2java from original rails globalize schema
 */
@Entity
@Indexed
@Table(name = "globalize_languages")
public class I18nLanguage implements HasId {

    @Id
    // Is not GeneratedValue
    @DocumentId
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "iso_639_1", length = 2)
    private String iso6391;

    @Column(name = "iso_639_2", length = 3, unique = true)
    private String iso6392;

    @Column(name = "iso_639_3", length = 3, unique = true)
    private String iso6393;

    @Column(name = "rfc_3066", unique = true)
    private String rfc3066;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "english_name")
    private String englishName;

    @Column(name = "english_name_locale")
    private String englishNameLocale;

    @Column(name = "english_name_modifier")
    private String englishNameModifier;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "native_name")
    private String nativeName;

    @Column(name = "native_name_locale")
    private String nativeNameLocale;

    @Column(name = "native_name_modifier")
    private String nativeNameModifier;

    @Column(name = "macro_language")
    private Boolean macroLanguage;

    @Column(name = "direction")
    private String direction;

    @Pattern(regex = "^[c=\\d?:%!<>&|() ]+$")
    @Column(name = "pluralization")
    @Length(max = 200)
    private String pluralization;

    @Column(name = "scope", length = 1)
    private String scope;

    public I18nLanguage() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    // Only for tests
    public I18nLanguage(final Long id, final String englishName, final String nativeName, final String code) {
        this.id = id;
        this.englishName = englishName;
        this.nativeName = nativeName;
        this.code = code;
    }

    // code is iso6391 || iso6392 || rfc3306 (see DatabaInicializer)
    public I18nLanguage(final Long id, final String code, final String direction, final String englishName,
            final String englishNameLocale, final String englishNameModifier, final String iso6391,
            final String iso6392, final String iso6393, final Boolean macroLanguage, final String nativeName,
            final String nativeNameLocale, final String nativeNameModifier, final String pluralization,
            final String rfc3066, final String scope) {
        this.id = id;
        this.iso6391 = iso6391;
        this.iso6392 = iso6392;
        this.iso6393 = iso6393;
        this.rfc3066 = rfc3066;
        this.englishName = englishName;
        this.englishNameLocale = englishNameLocale;
        this.englishNameModifier = englishNameModifier;
        this.nativeName = nativeName;
        this.nativeNameLocale = nativeNameLocale;
        this.nativeNameModifier = nativeNameModifier;
        this.macroLanguage = macroLanguage;
        this.direction = direction;
        this.pluralization = pluralization;
        this.scope = scope;
        this.code = code;
    }

    @Finder(query = "FROM I18nLanguage l WHERE code = :language")
    public I18nLanguage findByCode(@Named("language") final String language) {
        return null;
    }

    @Finder(query = "FROM I18nLanguage")
    public List<I18nLanguage> getAll() {
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public String getEnglishNameLocale() {
        return this.englishNameLocale;
    }

    public String getEnglishNameModifier() {
        return this.englishNameModifier;
    }

    public Long getId() {
        return this.id;
    }

    public String getIso6391() {
        return this.iso6391;
    }

    public String getIso6392() {
        return this.iso6392;
    }

    public String getIso6393() {
        return this.iso6393;
    }

    public Boolean getMacroLanguage() {
        return this.macroLanguage;
    }

    public String getNativeName() {
        return this.nativeName;
    }

    public String getNativeNameLocale() {
        return this.nativeNameLocale;
    }

    public String getNativeNameModifier() {
        return this.nativeNameModifier;
    }

    public String getPluralization() {
        return this.pluralization;
    }

    public String getRfc3066() {
        return this.rfc3066;
    }

    public String getScope() {
        return this.scope;
    }

    @Column(unique = true)
    public void setCode(final String code) {
        this.code = code;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }

    public void setEnglishName(final String englishName) {
        this.englishName = englishName;
    }

    public void setEnglishNameLocale(final String englishNameLocale) {
        this.englishNameLocale = englishNameLocale;
    }

    public void setEnglishNameModifier(final String englishNameModifier) {
        this.englishNameModifier = englishNameModifier;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIso6391(final String iso6391) {
        this.iso6391 = iso6391;
    }

    public void setIso6392(final String iso6392) {
        this.iso6392 = iso6392;
    }

    public void setIso6393(final String iso6393) {
        this.iso6393 = iso6393;
    }

    public void setMacroLanguage(final Boolean macroLanguage) {
        this.macroLanguage = macroLanguage;
    }

    public void setNativeName(final String nativeName) {
        this.nativeName = nativeName;
    }

    public void setNativeNameLocale(final String nativeNameLocale) {
        this.nativeNameLocale = nativeNameLocale;
    }

    public void setNativeNameModifier(final String nativeNameModifier) {
        this.nativeNameModifier = nativeNameModifier;
    }

    public void setPluralization(final String pluralization) {
        this.pluralization = pluralization;
    }

    public void setRfc3066(final String rfc3066) {
        this.rfc3066 = rfc3066;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "I18nLanguage[" + englishName + "]";
    }

}
