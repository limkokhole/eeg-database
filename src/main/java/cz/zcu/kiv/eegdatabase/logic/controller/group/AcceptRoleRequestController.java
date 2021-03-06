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
 *   AcceptRoleRequestController.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.logic.controller.group;

import cz.zcu.kiv.eegdatabase.data.dao.GenericDao;
import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.dao.ResearchGroupDao;
import cz.zcu.kiv.eegdatabase.data.pojo.GroupPermissionRequest;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroupMembership;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author M.Brozek
 */
public class AcceptRoleRequestController extends SimpleFormController {
    private GenericDao<GroupPermissionRequest, Integer> gpr;
    private ResearchGroupDao researchGroupDao;

    private PersonDao personDao;
    private int requestId;


    // <editor-fold defaultstate="collapsed" desc="Constructor, getters and setters">
    public AcceptRoleRequestController() {
        setCommandClass(EditGroupRoleCommand.class);
        setCommandName("editGroupRoleCommand");
    }


    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map map = new HashMap<String, Object>();
        requestId = Integer.parseInt(request.getParameter("id"));
        GroupPermissionRequest pr = gpr.read(requestId);
        Person user = pr.getPerson();    //TODO fix: when requestId is not found, throws NullPointer
        String choosenGroup = pr.getResearchGroup().getTitle();
        String requestRole = pr.getRequestedPermission();
        map.put("person", user);
        map.put("requestRole", requestRole);
        map.put("choosenGroup", choosenGroup);
        GroupMultiController.setPermissionToRequestGroupRole(map, personDao.getLoggedPerson());
        return map;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) throws Exception {
        EditGroupRoleCommand groupRoleCommand = (EditGroupRoleCommand) command;
        GroupPermissionRequest actual = gpr.read(requestId);
//    String role;
//    if (groupRoleCommand.isAdmin()){
//      role = "ROLE_ADMIN";
//    }
//    else {
//      role = "ROLE_EXPERIMENTER";
//    }
        actual.setRequestedPermission(groupRoleCommand.getRole());
        actual.setGranted(true);
        gpr.update(actual);

        Person person = actual.getPerson();
        Set<ResearchGroupMembership> groups = person.getResearchGroupMemberships();
        for (ResearchGroupMembership member : groups) {
            if ((member.getPerson().getPersonId() == person.getPersonId()) &&
                    (member.getResearchGroup().getResearchGroupId() == actual.getResearchGroup().getResearchGroupId())) {
                member.setAuthority(groupRoleCommand.getRole());

            }
        }

        ModelAndView mav = new ModelAndView(getSuccessView());
        return mav;
    }


    public ResearchGroupDao getResearchGroupDao() {
        return researchGroupDao;
    }

    public void setResearchGroupDao(ResearchGroupDao researchGroupDao) {
        this.researchGroupDao = researchGroupDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public GenericDao<GroupPermissionRequest, Integer> getGpr() {
        return gpr;
    }

    public void setGpr(GenericDao<GroupPermissionRequest, Integer> gpr) {
        this.gpr = gpr;
    }

}
