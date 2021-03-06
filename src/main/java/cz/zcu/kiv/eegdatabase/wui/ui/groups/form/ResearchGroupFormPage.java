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
 *   ResearchGroupFormPage.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.groups.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.StringValidator;

import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.BasePage;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.group.ResearchGroupFacade;
import cz.zcu.kiv.eegdatabase.wui.core.person.PersonFacade;
import cz.zcu.kiv.eegdatabase.wui.core.security.SecurityFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.groups.GroupPageLeftMenu;
import cz.zcu.kiv.eegdatabase.wui.ui.groups.ListResearchGroupsPage;
import cz.zcu.kiv.eegdatabase.wui.ui.groups.ResearchGroupsDetailPage;

/**
 * Page for add / edit action on research group.
 * 
 * @author Jakub Rinkes
 *
 */
@AuthorizeInstantiation(value = { "ROLE_READER", "ROLE_USER", "ROLE_EXPERIMENTER", "ROLE_ADMIN" })
public class ResearchGroupFormPage extends MenuPage {

    private static final long serialVersionUID = -5458823756761783078L;

    protected Log log = LogFactory.getLog(getClass());

    @SpringBean
    SecurityFacade securityFacade;

    @SpringBean
    ResearchGroupFacade facade;

    @SpringBean
    PersonFacade personFacade;

    public ResearchGroupFormPage() {

        setPageTitle(ResourceUtils.getModel("pageTitle.createGroup"));
        add(new Label("title", ResourceUtils.getModel("pageTitle.createGroup")));

        add(new ButtonPageMenu("leftMenu", prepareLeftMenu()));

        add(new ResearchGroupForm("form", new Model<ResearchGroup>(new ResearchGroup()), facade, getFeedback()));
    }

    public ResearchGroupFormPage(PageParameters parameters) {

        setPageTitle(ResourceUtils.getModel("pageTitle.editGroup"));
        add(new Label("title", ResourceUtils.getModel("pageTitle.editGroup")));

        add(new ButtonPageMenu("leftMenu", prepareLeftMenu()));

        StringValue value = parseParameters(parameters);

        int groupId = value.toInt();

        add(new ResearchGroupForm("form", new Model<ResearchGroup>(facade.getResearchGroupById(groupId)), facade, getFeedback()));
    }

    private GroupPageLeftMenu[] prepareLeftMenu() {

        List<GroupPageLeftMenu> list = new ArrayList<GroupPageLeftMenu>();
        boolean authorizedToRequestGroupRole = securityFacade.isAuthorizedToRequestGroupRole();

        for (GroupPageLeftMenu tmp : GroupPageLeftMenu.values())
            list.add(tmp);

        if (!authorizedToRequestGroupRole)
            list.remove(GroupPageLeftMenu.REQUEST_FOR_GROUP_ROLE);

        GroupPageLeftMenu[] array = new GroupPageLeftMenu[list.size()];
        return list.toArray(array);
    }

    private StringValue parseParameters(PageParameters parameters) {

        StringValue value = parameters.get(BasePage.DEFAULT_PARAM_ID);
        if (value.isNull() || value.isEmpty())
            throw new RestartResponseAtInterceptPageException(ListResearchGroupsPage.class);

        return value;
    }

    private class ResearchGroupForm extends Form<ResearchGroup> {

        private static final long serialVersionUID = 1L;

        public ResearchGroupForm(String id, IModel<ResearchGroup> model, final ResearchGroupFacade facade, final FeedbackPanel feedback) {
            super(id, new CompoundPropertyModel<ResearchGroup>(model));

            final TextField<String> title = new TextField<String>("title");
            title.setRequired(true);
            title.setLabel(ResourceUtils.getModel("label.researchGroupTitle"));
            title.add(StringValidator.maximumLength(100));

            final TextArea<String> description = new TextArea<String>("description");
            description.setRequired(true);
            description.setLabel(ResourceUtils.getModel("label.researchGroupDescription"));
            description.add(StringValidator.maximumLength(250));

            AjaxButton submit = new AjaxButton("submit", ResourceUtils.getModel("button.save"), this) {

                private static final long serialVersionUID = 1L;

                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                    target.add(feedback);
                }

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    target.add(feedback);

                    ResearchGroup group = ResearchGroupForm.this.getModelObject();

                    if (group.getResearchGroupId() > 0) {
                        if (!facade.canSaveTitle(group.getTitle(), group.getResearchGroupId())) {
                            error(ResourceUtils.getString("error.valueAlreadyInDatabase"));
                        } else {
                            facade.update(group);
                            setResponsePage(ResearchGroupsDetailPage.class, PageParametersUtils.getDefaultPageParameters(group.getResearchGroupId()));
                        }
                    } else {
                        group.setPerson(EEGDataBaseSession.get().getLoggedUser());
                        int groupId = facade.create(group);
                        setResponsePage(ResearchGroupsDetailPage.class, PageParametersUtils.getDefaultPageParameters(groupId));
                    }
                }
            };

            add(title, description, submit);
        }

    }
}
