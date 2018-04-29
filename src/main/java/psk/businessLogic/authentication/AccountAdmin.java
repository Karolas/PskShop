package psk.businessLogic.authentication;

import org.apache.deltaspike.security.api.authorization.SecurityBindingType;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
@SecurityBindingType
public @interface AccountAdmin {
}
