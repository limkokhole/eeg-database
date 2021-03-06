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
 *   PersonMapper.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.person;

import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.wui.core.Gender;
import cz.zcu.kiv.eegdatabase.wui.core.dto.FullPersonDTO;
import cz.zcu.kiv.eegdatabase.wui.ui.security.components.PersonFormObject;
import org.joda.time.DateTime;

import java.sql.Timestamp;

public class PersonMapper {

    public FullPersonDTO convertToDTO(Person person) {

        FullPersonDTO dto = new FullPersonDTO();
        dto.setId(person.getPersonId());
        dto.setGivenname(person.getGivenname());
        dto.setSurname(person.getSurname());
        dto.setDateOfBirth(person.getDateOfBirth() == null ? null : person.getDateOfBirth());
        //dto.setEmail(person.getUsername().toLowerCase());
        dto.setUsername(person.getUsername());
        dto.setGender(Gender.getGenderByShortcut(person.getGender()));
        dto.setConfirmed(person.isConfirmed());
        dto.setRegistrationDate(new DateTime(person.getRegistrationDate().getTime()));

        dto.setEducationLevel(person.getEducationLevel());
        dto.setLaterality(person.getLaterality());
        dto.setAuthority(person.getAuthority());

        return dto;
    }

    public Person convertToEntity(PersonFormObject reg, Person person) {

        FullPersonDTO dto = reg.getPanelPerson();
        person = convertToEntity(dto, person);

        person.setUsername(reg.getEmail().toLowerCase());
        person.setEmail(reg.getEmail().toLowerCase());
        //person.setGender(dto.getGender().getShortcut());

        person.setPassword(reg.getPassword());

        return person;
    }

    public Person convertToEntity(FullPersonDTO dto, Person person) {
        person.setPersonId(dto.getId());
        person.setGivenname(dto.getGivenname());
        person.setSurname(dto.getSurname());
        person.setDateOfBirth(new Timestamp(dto.getDateOfBirth().getTime()));

        //person.setGender(dto.getGender().getShortcut());
        person.setConfirmed(dto.isConfirmed());
        person.setRegistrationDate(new Timestamp(dto.getRegistrationDate().getMillis()));
        person.setLaterality(dto.getLaterality());
        person.setAuthority(dto.getAuthority());

        person.setEducationLevel(dto.getEducationLevel());

        person.setTitle(dto.getTitle());
        if (dto.getTitle().equals("Mr.")) {
            person.setGender('M');
        } else {
            person.setGender('F');
        }
        person.setAddress(dto.getAddress());
        person.setCity(dto.getCity());
        person.setState(dto.getState());
        person.setZipCode(dto.getZipCode());
        person.setCountry(dto.getCountry());
        person.setUrl(dto.getUrl());
        person.setPhoneNumber(dto.getPhone());

        person.setOrganizationType(dto.getOrganizationType());
        person.setOrganization(dto.getOrganization());
        person.setJobTitle(dto.getJobTitle());
        person.setOrgAddress(dto.getOrgAddress());
        person.setOrgCity(dto.getOrgCity());
        person.setOrgState(dto.getOrgState());
        person.setOrgZipCode(dto.getOrgZipCode());
        person.setOrgUrl(dto.getOrgUrl());
        person.setOrgCountry(dto.getOrgCountry());
        person.setVAT(dto.getVAT());
        person.setOrgPhone(dto.getOrgPhone());


        return person;

    }
}
