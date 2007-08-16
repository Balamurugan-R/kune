package org.ourproject.kune.workspace.client.dto;

import org.ourproject.kune.platf.client.dto.AccessListsDTO;
import org.ourproject.kune.platf.client.dto.AccessRightsDTO;
import org.ourproject.kune.platf.client.dto.FolderDTO;
import org.ourproject.kune.platf.client.dto.GroupDTO;
import org.ourproject.kune.platf.client.dto.StateToken;

import com.google.gwt.user.client.rpc.IsSerializable;

public class StateDTO implements IsSerializable {
    private String documentId;
    private int version;
    private String content;
    private String title;
    private String toolName;
    private GroupDTO group;
    private FolderDTO folder;
    private AccessListsDTO accessLists;
    private AccessRightsDTO contentRights;
    private AccessRightsDTO folderRights;
    private Double rate;
    private Integer rateByUsers;

    public StateDTO() {
	this(null, null, null);
    }

    public StateDTO(final String docRef, final String title, final String content) {
	this.documentId = docRef;
	this.title = title;
	this.content = content;
    }

    public int getVersion() {
	return version;
    }

    public void setVersion(final int version) {
	this.version = version;
    }

    public void setDocumentId(final String docRef) {
	this.documentId = docRef;
    }

    public void setContent(final String content) {
	this.content = content;
    }

    public String getDocumentId() {
	return documentId;
    }

    public String getContent() {
	return content;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(final String title) {
	this.title = title;
    }

    public String getToolName() {
	return toolName;
    }

    public void setToolName(final String toolName) {
	this.toolName = toolName;
    }

    public GroupDTO getGroup() {
	return this.group;
    }

    public void setGroup(final GroupDTO group) {
	this.group = group;
    }

    public AccessRightsDTO getContentRights() {
	return this.contentRights;
    }

    public void setContentRights(final AccessRightsDTO accessRights) {
	this.contentRights = accessRights;
    }

    public FolderDTO getFolder() {
	return folder;
    }

    public void setFolder(final FolderDTO folder) {
	this.folder = folder;
    }

    public StateToken getState() {
	return new StateToken(group.getShortName(), toolName, folder.getId().toString(), getDocumentId());
    }

    // FIXME: probablemente, un tag en el content indicando el tipo!!, darle una
    // pensada a esto
    public boolean hasDocument() {
	return documentId != null;
    }

    public AccessListsDTO getAccessLists() {
	return accessLists;
    }

    public void setAccessLists(final AccessListsDTO accessLists) {
	this.accessLists = accessLists;
    }

    public Double getRate() {
	return rate;
    }

    public void setRate(final Double rate) {
	this.rate = rate;
    }

    public Integer getRateByUsers() {
	return rateByUsers;
    }

    public void setRateByUsers(final Integer rateByUsers) {
	this.rateByUsers = rateByUsers;
    }

    public AccessRightsDTO getFolderRights() {
	return folderRights;
    }

    public void setFolderRights(final AccessRightsDTO folderRights) {
	this.folderRights = folderRights;
    }

}