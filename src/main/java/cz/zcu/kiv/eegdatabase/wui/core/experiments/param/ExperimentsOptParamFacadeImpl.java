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
 *   ExperimentsOptParamFacadeImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.experiments.param;

import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDef;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDefGroupRel;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamVal;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamValId;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ExperimentsOptParamFacadeImpl implements ExperimentsOptParamFacade {

    protected Log log = LogFactory.getLog(getClass());

    ExperimentsOptParamService service;

    @Required
    public void setService(ExperimentsOptParamService service) {
        this.service = service;
    }

    @Override
    public Integer create(ExperimentOptParamDef newInstance) {
        return service.create(newInstance);
    }

    @Override
    public ExperimentOptParamDef read(Integer id) {
        return service.read(id);
    }

    @Override
    public List<ExperimentOptParamDef> readByParameter(String parameterName, Object parameterValue) {
        return service.readByParameter(parameterName, parameterValue);
    }

    @Override
    public void update(ExperimentOptParamDef transientObject) {
        service.update(transientObject);
    }

    @Override
    public void delete(ExperimentOptParamDef persistentObject) {
        service.delete(persistentObject);
    }

    @Override
    public List<ExperimentOptParamDef> getAllRecords() {
        return service.getAllRecords();
    }

    @Override
    public List<ExperimentOptParamDef> getRecordsAtSides(int first, int max) {
        return service.getRecordsAtSides(first, max);
    }

    @Override
    public int getCountRecords() {
        return service.getCountRecords();
    }

    @Override
    public List<ExperimentOptParamDef> getUnique(ExperimentOptParamDef example) {
        return service.getUnique(example);
    }

    @Override
    public List<ExperimentOptParamDef> getItemsForList() {
        return service.getItemsForList();
    }

    @Override
    public boolean canDelete(int id) {
        return service.canDelete(id);
    }

    @Override
    public List<ExperimentOptParamDef> getRecordsByGroup(int groupId) {
        return service.getRecordsByGroup(groupId);
    }

    @Override
    public void createDefaultRecord(ExperimentOptParamDef experimentOptParamDef) {
        service.createDefaultRecord(experimentOptParamDef);
    }

    @Override
    public List<ExperimentOptParamDef> getDefaultRecords() {
        return service.getDefaultRecords();
    }

    @Override
    public boolean hasGroupRel(int id) {
        return service.hasGroupRel(id);
    }

    @Override
    public void deleteGroupRel(ExperimentOptParamDefGroupRel experimentOptParamDefGroupRel) {
        service.deleteGroupRel(experimentOptParamDefGroupRel);
    }

    @Override
    public ExperimentOptParamDefGroupRel getGroupRel(int experimentOptParamDefId, int researchGroupId) {
        return service.getGroupRel(experimentOptParamDefId, researchGroupId);
    }

    @Override
    public void createGroupRel(ExperimentOptParamDefGroupRel experimentOptParamDefGroupRel) {
        service.createGroupRel(experimentOptParamDefGroupRel);
    }

    @Override
    public void createGroupRel(ExperimentOptParamDef experimentOptParamDef, ResearchGroup researchGroup) {
        service.createGroupRel(experimentOptParamDef, researchGroup);
    }

    @Override
    public boolean isDefault(int id) {
        return service.isDefault(id);
    }

    @Override
    @Transactional
    public ExperimentOptParamValId create(ExperimentOptParamVal newInstance) {
        return service.create(newInstance);
    }

    @Override
    @Transactional(readOnly = true)
    public ExperimentOptParamVal read(ExperimentOptParamValId id) {
        return service.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperimentOptParamVal> readValByParameter(String parameterName, Object parameterValue) {
        return service.readValByParameter(parameterName, parameterValue);
    }

    @Override
    @Transactional
    public void update(ExperimentOptParamVal transientObject) {
        service.update(transientObject);
    }

    @Override
    @Transactional
    public void delete(ExperimentOptParamVal persistentObject) {
        service.delete(persistentObject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperimentOptParamVal> getAllValRecords() {
        return service.getAllValRecords();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperimentOptParamVal> getValRecordsAtSides(int first, int max) {
        return service.getValRecordsAtSides(first, max);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperimentOptParamVal> getUnique(ExperimentOptParamVal example) {
        return service.getUnique(example);
    }

}
