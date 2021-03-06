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
 *   IAutoCompletable.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.experiments.converters;

/**
 * Created by IntelliJ IDEA.
 * User: Matej Sutr
 * Date: 6.5.13
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public interface IAutoCompletable {

    /**
     * It must return some String by which I can get correct object from DB furthermore
     * it has to be in human readable form, because users will need to choose among these.
     *
     * @return Human readable String representing the object.
     */
    public String getAutoCompleteData();
}
