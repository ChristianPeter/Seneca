/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.security;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import sol.neptune.seneca.controller.UserAccountFacade;
import sol.neptune.seneca.entities.UserAccount;

/**
 *
 * @author murdoc
 */
@Named("authenticator")
@RequestScoped
public class Authenticator implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB
    private UserAccountFacade userAccountFacade;
    private String username;
    private String password;
    
    @Inject
    private Conversation conversation;
    

    public String login() {


        if (username != null) {
            UserAccount user = userAccountFacade.findByUsername(username);
            if (user== null){
                // no user found!
                return "";
            }
            if (user.getPassword().equals(password)) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("user", user.getId());
                return "/view/home?faces-redirect=true";
            }
        }


        return "";
    }

    public String logout() {
        if (!conversation.isTransient()){
            conversation.end();
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return AuthenticationPhaseListener.USER_LOGIN_OUTCOME;

    }

    /* getter & setter */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
