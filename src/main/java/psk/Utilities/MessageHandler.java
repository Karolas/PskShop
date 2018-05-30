package psk.Utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class MessageHandler implements Serializable {

    public void addMessage(String title, String details){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title,  details) );
    }
    public  void addErrorMessage(String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, details));
    }
}
