package cz.zcu.kiv.eegdatabase.logic.controller.person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author JiPER
 */
public class AddHearingDefectToPersonValidator implements Validator {

    private Log log = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return clazz.equals(AddDefectToPersonCommand.class);
    }

    public void validate(Object command, Errors errors) {
        AddDefectToPersonCommand data = (AddDefectToPersonCommand) command;

        if (data.getDefectId() < 0) {
            errors.rejectValue("defectId", "required.field");
        }
    }
}