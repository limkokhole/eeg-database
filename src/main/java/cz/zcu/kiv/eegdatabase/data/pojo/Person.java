/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   Person.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.converters.IAutoCompletable;
import cz.zcu.kiv.formgen.annotation.Form;
import cz.zcu.kiv.formgen.annotation.FormItem;
import cz.zcu.kiv.formgen.annotation.FormItemRestriction;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Person generated by hbm2java
 */
@Entity
@Form
@Table(name = "PERSON")
public class Person implements Serializable, Comparable<Person>, IAutoCompletable {

	private int personId;
	private String facebookId;
	private String email;
	@FormItem(required = true)
	private String givenname;
	@FormItem(required = true)
	private String surname;
	@FormItem
	private Timestamp dateOfBirth;
	@FormItem
	@FormItemRestriction(values = {"M", "F"})
	private char gender;
	private char laterality;
	private String phoneNumber;
	private String note;
	private String username;
	private String password;
	private String authority;
	private Timestamp registrationDate;
	private boolean confirmed;
	private String authenticationHash;
	private ResearchGroup defaultGroup;
	private EducationLevel educationLevel;
	private Set<Experiment> experiments = new HashSet<Experiment>(0);
	private Set<ResearchGroupMembership> researchGroupMemberships = new HashSet<ResearchGroupMembership>(
					0);
	private Set<Scenario> scenarios = new HashSet<Scenario>(0);
	private Set<Experiment> experimentsForOwnerId = new HashSet<Experiment>(0);
	private Set<Experiment> experimentsForSubjectPersonId = new HashSet<Experiment>(
					0);
	private Set<History> histories = new HashSet<History>(0);
	private Set<PersonOptParamVal> personOptParamVals = new HashSet<PersonOptParamVal>(
					0);
	private Set<ResearchGroup> researchGroups = new HashSet<ResearchGroup>(0);
	private Set<GroupPermissionRequest> requests = new HashSet<GroupPermissionRequest>(
					0);
	private Set<Article> articlesSubscribtions = new HashSet<Article>(0);
	private Set<ResearchGroup> articlesGroupSubscribtions = new HashSet<ResearchGroup>(
					0);
	private Set<ServiceResult> results = new HashSet<ServiceResult>(0);
	private Set<PersonalLicense> personalLicenses = new HashSet<PersonalLicense>(0);

	public Person() {
	}

	public Person(String surname, char gender, char laterality) {
		this.surname = surname;
		this.gender = gender;
		this.laterality = laterality;
	}

	public Person(String facebookId, String email, String givenname,
					String surname, Timestamp dateOfBirth, char gender,
					char laterality, String phoneNumber, String note, String username,
					String password, String authority, Timestamp registrationDate,
					boolean confirmed, String authenticationHash,
					ResearchGroup defaultGroup, EducationLevel educationLevel,
					Set<Experiment> experiments,
					Set<ResearchGroupMembership> researchGroupMemberships,
					Set<Scenario> scenarios, Set<Experiment> experimentsForOwnerId,
					Set<Experiment> experimentsForSubjectPersonId,
					Set<History> histories, Set<PersonOptParamVal> personOptParamVals,
					Set<ResearchGroup> researchGroups,
					Set<GroupPermissionRequest> requests,
					Set<Article> articlesSubscribtions,
					Set<ResearchGroup> articlesGroupSubscribtions,
					Set<ServiceResult> results) {
		this.facebookId = facebookId;
		this.email = email;
		this.givenname = givenname;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.laterality = laterality;
		this.phoneNumber = phoneNumber;
		this.note = note;
		this.username = username;
		this.password = password;
		this.authority = authority;
		this.registrationDate = registrationDate;
		this.confirmed = confirmed;
		this.authenticationHash = authenticationHash;
		this.defaultGroup = defaultGroup;
		this.educationLevel = educationLevel;
		this.experiments = experiments;
		this.researchGroupMemberships = researchGroupMemberships;
		this.scenarios = scenarios;
		this.experimentsForOwnerId = experimentsForOwnerId;
		this.experimentsForSubjectPersonId = experimentsForSubjectPersonId;
		this.histories = histories;
		this.personOptParamVals = personOptParamVals;
		this.researchGroups = researchGroups;
		this.requests = requests;
		this.articlesSubscribtions = articlesSubscribtions;
		this.articlesGroupSubscribtions = articlesGroupSubscribtions;
		this.results = results;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PERSON_ID", nullable = false, precision = 22, scale = 0)
	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	@Column(name = "FB_UID", unique = true, length = 30)
	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	@Column(name = "EMAIL", unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "GIVENNAME", length = 50)
	public String getGivenname() {
		return this.givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	@Column(name = "SURNAME", nullable = false, length = 50)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "DATE_OF_BIRTH", length = 7)
	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "GENDER", nullable = false, length = 1)
	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Column(name = "LATERALITY", nullable = false, length = 1)
	public char getLaterality() {
		return this.laterality;
	}

	public void setLaterality(char laterality) {
		this.laterality = laterality;
	}

	@Column(name = "PHONE_NUMBER", length = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "NOTE")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "USERNAME", unique = true, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 80)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "AUTHORITY", length = 50)
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Column(name = "REGISTRATION_DATE", length = 7)
	public Timestamp getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(name = "CONFIRMED")
	public boolean isConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Column(name = "AUTHENTICATION", length = 50)
	public String getAuthenticationHash() {
		return this.authenticationHash;
	}

	public void setAuthenticationHash(String authenticationHash) {
		this.authenticationHash = authenticationHash;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEFAULT_GROUP_ID")
	public ResearchGroup getDefaultGroup() {
		return this.defaultGroup;
	}

	public void setDefaultGroup(ResearchGroup defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EDUCATION_LEVEL_ID")
	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "COEXPERIMENTER_REL", joinColumns = {
		@JoinColumn(name = "PERSON_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
		@JoinColumn(name = "EXPERIMENT_ID", nullable = false, updatable = false)})
	public Set<Experiment> getExperiments() {
		return this.experiments;
	}

	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<ResearchGroupMembership> getResearchGroupMemberships() {
		return this.researchGroupMemberships;
	}

	public void setResearchGroupMemberships(
					Set<ResearchGroupMembership> researchGroupMemberships) {
		this.researchGroupMemberships = researchGroupMemberships;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Scenario> getScenarios() {
		return this.scenarios;
	}

	public void setScenarios(Set<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personByOwnerId")
	public Set<Experiment> getExperimentsForOwnerId() {
		return this.experimentsForOwnerId;
	}

	public void setExperimentsForOwnerId(Set<Experiment> experimentsForOwnerId) {
		this.experimentsForOwnerId = experimentsForOwnerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personBySubjectPersonId")
	public Set<Experiment> getExperimentsForSubjectPersonId() {
		return this.experimentsForSubjectPersonId;
	}

	public void setExperimentsForSubjectPersonId(
					Set<Experiment> experimentsForSubjectPersonId) {
		this.experimentsForSubjectPersonId = experimentsForSubjectPersonId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<History> getHistories() {
		return this.histories;
	}

	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<PersonOptParamVal> getPersonOptParamVals() {
		return this.personOptParamVals;
	}

	public void setPersonOptParamVals(Set<PersonOptParamVal> personOptParamVals) {
		this.personOptParamVals = personOptParamVals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<ResearchGroup> getResearchGroups() {
		return this.researchGroups;
	}

	public void setResearchGroups(Set<ResearchGroup> researchGroups) {
		this.researchGroups = researchGroups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<GroupPermissionRequest> getRequests() {
		return this.requests;
	}

	public void setRequests(Set<GroupPermissionRequest> requests) {
		this.requests = requests;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ARTICLES_SUBSCRIBTIONS", joinColumns = {
		@JoinColumn(name = "PERSON_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
		@JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false)})
	public Set<Article> getArticlesSubscribtions() {
		return this.articlesSubscribtions;
	}

	public void setArticlesSubscribtions(Set<Article> articlesSubscribtions) {
		this.articlesSubscribtions = articlesSubscribtions;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ARTICLES_GROUP_SUBSCRIBTIONS", joinColumns = {
		@JoinColumn(name = "PERSON_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
		@JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, updatable = false)})
	public Set<ResearchGroup> getArticlesGroupSubscribtions() {
		return this.articlesGroupSubscribtions;
	}

	public void setArticlesGroupSubscribtions(
					Set<ResearchGroup> articlesGroupSubscribtions) {
		this.articlesGroupSubscribtions = articlesGroupSubscribtions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	public Set<ServiceResult> getResults() {
		return this.results;
	}

	public void setResults(Set<ServiceResult> results) {
		this.results = results;
	}

	@OneToMany(mappedBy = "person")
	public Set<PersonalLicense> getPersonalLicenses() {
		return personalLicenses;
	}

	public void setPersonalLicenses(Set<PersonalLicense> personalLicenses) {
		this.personalLicenses = personalLicenses;
	}
	
	@Transient
	public String getFullName() {

		if (getGivenname() == null && getSurname() == null && getEmail() == null) {
			return null;
		}

		String fullName = (getSurname() != null) ? getSurname() : "";
		fullName += (getGivenname() != null) ? " " + getGivenname() : "";
		return fullName;
	}

	@Override
	public int compareTo(Person person) {
		return this.surname.compareTo(person.getSurname());
	}

	@Transient
	@Override
	public String getAutoCompleteData() {

		if (getGivenname() == null && getSurname() == null && getEmail() == null) {
			return null;
		}

		String fullName = (getSurname() != null) ? getSurname() : "";
		fullName += (getGivenname() != null) ? " " + getGivenname() : "";
		fullName += (getUsername() != null) ? " " + getUsername() : "";
		return fullName;
	}
}
