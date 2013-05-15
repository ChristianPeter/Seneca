/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.security;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author murdoc
 */
public class AuthenticationPhaseListener implements PhaseListener {

    public static String USER_LOGIN_OUTCOME = "/view/login.xhtml?faces-redirect=true";

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();

        if (!userExists(context)) {
            if (requestingSecureView(context)) {
                context.responseComplete();  
                context.getApplication().
                        getNavigationHandler().handleNavigation(context,
                        null,
                        USER_LOGIN_OUTCOME);
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(Authenticator.USER_SESSION_KEY));
    }

    private boolean requestingSecureView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        String path = extContext.getRequestPathInfo();
        return (!"/view/login.xhtml".equals(path) && !"/index.xhtml".equals(path) && ! path.startsWith("/assets"));
    }
}
