package psk.front.validators;

import java.util.Map;
import java.util.regex.Pattern;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.validate.ClientValidator;
import psk.businessLogic.AuthenticatedAccountHolder;
import psk.database.dao.AccountDAO;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator, ClientValidator {
    @Inject
    private Instance<AccountDAO> accountDAO;

    private Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }

        if(!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email: Validation Error: " + value + " cannot be used;",
                    value + " is not a valid email;"));
        }

        if(accountDAO.get().isAccountExists(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email: Validation Error: " + value + " cannot be used;",
                    value + " already exists;"));
        }
    }

    public Map<String, Object> getMetadata() {
        return null;
    }

    public String getValidatorId() {
        return "emailValidator";
    }

}