package psk.front.validators;

import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator, ClientValidator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }

//        UIInput passConfirmComponent =
//                (UIInput) context.getCurrentInstance().getViewRoot().findComponent(":passwordConfirm");

        UIInput passConfirmComponent =
                (UIInput) component.findComponent("passwordConfirm");

        if(!value.equals(passConfirmComponent.getSubmittedValue())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                      "Password: Validation Error: passwords do not match;",
                    "passwords do not match;"));
        }
    }

    public Map<String, Object> getMetadata() {
        return null;
    }

    public String getValidatorId() {
        return "passwordValidator";
    }

}