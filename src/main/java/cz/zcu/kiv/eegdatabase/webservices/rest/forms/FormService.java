/***********************************************************************************************************************
 *
 * This file is part of the EEG-database project
 *
 * =============================================
 *
 * Copyright (C) 2014 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 *
 * FormService.java, 8. 1. 2014 12:05:51, Jakub Krauz
 *
 **********************************************************************************************************************/
package cz.zcu.kiv.eegdatabase.webservices.rest.forms;

import java.util.List;

import cz.zcu.kiv.eegdatabase.webservices.rest.forms.wrappers.AvailableLayoutsDataList;
import cz.zcu.kiv.formgen.Form;

/**
 * Service interface providing form-layouts data and operations on REST service.
 * 
 * @author Jakub Krauz
 */
public interface FormService {
	
	
	/**
	 * Gets the count of forms with available layouts.
	 * @return count of forms
	 */
	int availableFormsCount();
	
	
	/**
	 * Gets names of forms with available layouts.
	 * @return names of forms
	 */
	List<String> availableForms();
	
	
	/**
	 * Gets the count of all form-layouts available.
	 * @return count of form-layouts
	 */
	int availableLayoutsCount();
	
	
	/**
	 * Gets the count of form-layouts available for the specified form.
	 * @param formName - name of the form
	 * @return count of form-layouts
	 */
	int availableLayoutsCount(String formName);
	
	
	/**
	 * Gets names of all form layouts available.
	 * @return names of form layouts
	 */
	AvailableLayoutsDataList availableLayouts();
	
	
	/**
	 * Gets names of form layouts available for the specified form.
	 * @param formName - name of the form
	 * @return names of form layouts
	 */
	AvailableLayoutsDataList availableLayouts(String formName);
	
	
	/**
	 * Gets a form-layout with the specified name.
	 * @param formName - name of the form
	 * @param layoutName - name of the layout
	 * @return requested form-layout
	 */
	Form getLayout(String formName, String layoutName);

}
