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
 *   PersonInfo.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.webservices.client.wrappers;


/**
 * Class for gathering important information about person. Meant to be sent
 * between eegclient and portal's client service.
 * 
 * @author František Liška
 */
public class PersonInfo {
	private int personId;
	private String givenname;
	private String surname;
	private long dateOfBirthInMillis;
	private char gender;
	private String phoneNumber;
	private long registrationDateInMillis;
	private String note;
	private String username;
	private char laterality;
	private int educationLevelId;

	public PersonInfo() {
	}

	public PersonInfo(int personId, String surname, char gender) {
		this.personId = personId;
		this.surname = surname;
		this.gender = gender;
	}

	public PersonInfo(int personId, String givenname, String surname, long dateOfBirthInMillis, char gender, String phoneNumber, String note,
			String username) {
		this.personId = personId;
		this.givenname = givenname;
		this.surname = surname;
		this.dateOfBirthInMillis = dateOfBirthInMillis;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.note = note;
		this.username = username;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getDateOfBirthInMillis() {
		return dateOfBirthInMillis;
	}

	public void setDateOfBirthInMillis(long dateOfBirthInMillis) {
		this.dateOfBirthInMillis = dateOfBirthInMillis;
	}

	public long getRegistrationDateInMillis() {
		return registrationDateInMillis;
	}

	public void setRegistrationDateInMillis(long registrationDateInMillis) {
		this.registrationDateInMillis = registrationDateInMillis;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char getLaterality() {
		return laterality;
	}

	public void setLaterality(char laterality) {
		this.laterality = laterality;
	}

	public int getEducationLevelId() {
		return educationLevelId;
	}

	public void setEducationLevelId(int educationLevelId) {
		this.educationLevelId = educationLevelId;
	}
}
