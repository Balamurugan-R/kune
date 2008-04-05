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

package org.ourproject.kune.platf.server.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.ourproject.kune.platf.client.errors.I18nNotFoundException;
import org.ourproject.kune.platf.client.errors.UserNotFoundException;
import org.ourproject.kune.platf.client.ui.KuneStringUtils;
import org.ourproject.kune.platf.server.access.FinderService;
import org.ourproject.kune.platf.server.domain.Container;
import org.ourproject.kune.platf.server.domain.Content;
import org.ourproject.kune.platf.server.domain.I18nLanguage;
import org.ourproject.kune.platf.server.domain.Rate;
import org.ourproject.kune.platf.server.domain.Revision;
import org.ourproject.kune.platf.server.domain.Tag;
import org.ourproject.kune.platf.server.domain.User;
import org.ourproject.kune.platf.server.manager.TagManager;
import org.ourproject.kune.platf.server.manager.impl.DefaultManager;
import org.ourproject.kune.platf.server.manager.impl.SearchResult;

import com.google.gwt.user.client.rpc.SerializableException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentManagerDefault extends DefaultManager<Content, Long> implements ContentManager {

    private final FinderService finder;
    private final User userFinder;
    private final I18nLanguage languageFinder;
    private final TagManager tagManager;

    @Inject
    public ContentManagerDefault(final Provider<EntityManager> provider, final FinderService finder,
            final User userFinder, final I18nLanguage languageFinder, final TagManager tagManager) {
        super(provider, Content.class);
        this.finder = finder;
        this.userFinder = userFinder;
        this.languageFinder = languageFinder;
        this.tagManager = tagManager;
    }

    public Content createContent(final String title, final String body, final User user, final Container container) {
        Content descriptor = new Content();
        descriptor.addAuthor(user);
        descriptor.setLanguage(user.getLanguage());
        // FIXME: remove this when UI take publishing into account
        descriptor.setPublishedOn(new Date());
        descriptor.setContainer(container);
        container.addContent(descriptor);
        Revision revision = new Revision(descriptor);
        revision.setTitle(title);
        revision.setBody(body);
        descriptor.addRevision(revision);
        return persist(descriptor);
    }

    public Content save(final User editor, final Content descriptor, final String content) {
        Revision revision = new Revision(descriptor);
        revision.setEditor(editor);
        revision.setTitle(descriptor.getTitle());
        revision.setBody(content);
        descriptor.addRevision(revision);
        return persist(descriptor);
    }

    public Double getRateAvg(final Content content) {
        return finder.getRateAvg(content);
    }

    public Long getRateByUsers(final Content content) {
        return finder.getRateByUsers(content);
    }

    public void rateContent(final User rater, final Long contentId, final Double value) throws SerializableException {
        Content content = finder.getContent(contentId);
        Rate oldRate = finder.getRate(rater, content);
        if (oldRate == null) {
            Rate rate = new Rate(rater, content, value);
            super.persist(rate, Rate.class);
        } else {
            oldRate.setValue(value);
            super.persist(oldRate, Rate.class);
        }

    }

    public Double getRateContent(final User rater, final Content content) {
        Rate rate = finder.getRate(rater, content);
        if (rate != null) {
            return rate.getValue();
        } else {
            return null;
        }
    }

    public void addAuthor(final User user, final Long contentId, final String authorShortName)
            throws SerializableException {
        Content content = finder.getContent(contentId);
        User author = userFinder.getByShortName(authorShortName);
        if (author == null) {
            throw new UserNotFoundException();
        }
        content.addAuthor(author);
    }

    public void removeAuthor(final User user, final Long contentId, final String authorShortName)
            throws SerializableException {
        Content content = finder.getContent(contentId);
        User author = userFinder.getByShortName(authorShortName);
        if (author == null) {
            throw new UserNotFoundException();
        }
        content.removeAuthor(author);
    }

    public I18nLanguage setLanguage(final User user, final Long contentId, final String languageCode)
            throws SerializableException {
        Content content = finder.getContent(contentId);
        I18nLanguage language = languageFinder.findByCode(languageCode);
        if (language == null) {
            throw new I18nNotFoundException();
        }
        content.setLanguage(language);
        return language;
    }

    public void setPublishedOn(final User user, final Long contentId, final Date publishedOn)
            throws SerializableException {
        Content content = finder.getContent(contentId);
        content.setPublishedOn(publishedOn);
    }

    public void setTags(final User user, final Long contentId, final String tags) throws SerializableException {
        Content content = finder.getContent(contentId);
        ArrayList<String> tagsStripped = KuneStringUtils.splitTags(tags);
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        for (Iterator<String> i = tagsStripped.iterator(); i.hasNext();) {
            String tagString = i.next();
            Tag tag;
            try {
                tag = tagManager.findByTagName(tagString);
            } catch (NoResultException e) {
                tag = new Tag(tagString);
                tagManager.persist(tag);
            }
            if (!tagList.contains(tag)) {
                tagList.add(tag);
            }
        }
        content.setTags(tagList);
    }

    public String renameContent(final User user, final Long contentId, final String newTitle)
            throws SerializableException {
        Content content = finder.getContent(contentId);
        content.getLastRevision().setTitle(newTitle);
        return newTitle;
    }

    public void delContent(final User user, final Long contentId) throws SerializableException {
        Content content = finder.getContent(contentId);
        content.setMarkForDeletion(true);
        content.setDeletedOn(new Date());
        // FIXME: Maybe set only visible for admins
    }

    public SearchResult<Content> search(final String search) {
        return this.search(search, null, null);
    }

    public SearchResult<Content> search(final String search, final Integer firstResult, final Integer maxResults) {
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[] { "authors.name", "authors.shortName",
                "container.name", "language.code", "language.englishName", "language.nativeName", "lastRevision.body",
                "lastRevision.title", "tags.name" }, new StandardAnalyzer());
        Query query;
        try {
            query = parser.parse(search);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing search");
        }
        return super.search(query, firstResult, maxResults);
    }

}
