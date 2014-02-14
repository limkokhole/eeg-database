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
 * FormServiceController.java, 3. 1. 2014 10:08:47, Jakub Krauz
 *
 **********************************************************************************************************************/
package cz.zcu.kiv.eegdatabase.webservices.rest.forms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.zcu.kiv.eegdatabase.webservices.rest.forms.wrappers.AvailableLayoutsData;
import cz.zcu.kiv.eegdatabase.webservices.rest.forms.wrappers.AvailableLayoutsDataList;
import cz.zcu.kiv.formgen.Form;
import cz.zcu.kiv.formgen.Writer;
import cz.zcu.kiv.formgen.odml.OdmlWriter;


/**
 * Controller class mapping the REST form-layout service.
 *
 * @author Jakub Krauz
 */
//@Secured("IS_AUTHENTICATED_FULLY")     // temporarily disabled for testing purposes
@Controller
@RequestMapping("/form-layouts")
public class FormServiceController {
	
	/** The service object providing data. */
	@Autowired
	private FormService service;
	
	
	/**
	 * Gets the count of forms available.
	 * @return count of forms available
	 */
	@RequestMapping(value = "/form/count")
	public @ResponseBody int availableFormsCount() {
		return service.availableFormsCount();
	}
	
	
	/**
	 * Gets names of all forms with available layouts.
	 * @return names of forms with available layouts
	 */
	@RequestMapping(value = "/form/available")
	public @ResponseBody List<String> availableForms() {
		return service.availableForms();
	}
	
	
	/**
	 * Gets the count of form-layouts available.
	 * @param formName - name of a specific form, or null (any form)
	 * @return count of form-layouts available
	 */
	@RequestMapping(value = "/count")
	public @ResponseBody int availableLayoutsCount(@RequestParam(value = "form", required = false) String formName) {
		if (formName == null)
			return service.availableLayoutsCount();
		else
			return service.availableLayoutsCount(formName);
	}
	
	
	/**
	 * Gets names of all form layouts available.
	 * @param formName - name of a specific form, or null (any form)
	 * @return names of form layouts available
	 */
	@RequestMapping(value = "/available")
	public @ResponseBody AvailableLayoutsDataList availableLayouts(@RequestParam(value = "form", required = false) String formName) {
		if (formName == null)
			return service.availableLayouts();
		else
			return service.availableLayouts(formName);
	}

	
	/**
	 * Gets a form layouts with the specified name.
	 * @param formName - name of the form
	 * @param layoutName - name of the layout
	 * @param response - HTTP response object
	 * @throws IOException if an error writing to response's output stream occured
	 */
	@RequestMapping(value = "/get/{formName}/{layoutName}" /*, produces = "application/xml"*/ )
	public void getForm(@PathVariable String formName, @PathVariable String layoutName, HttpServletResponse response) throws IOException {
		Form form = service.getLayout(formName, layoutName);
		if (form == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		Writer writer = new OdmlWriter();
		response.setContentType("application/xml");
		//response.setContentLength(???);
		writer.write(form, response.getOutputStream());
		response.flushBuffer();
	}
	
}
