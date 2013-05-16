/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.security;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sol.neptune.seneca.controller.UserAccountFacade;

/**
 *
 * @author murdoc
 */
@ManagedBean(name = "authenticator")
@RequestScoped
public class Authenticator implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String USER_SESSION_KEY = "user";
    @EJB
    private UserAccountFacade userAccountFacade;
    private String username;
    private String password;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, "dummy");
        return "/view/home?faces-redirect=true";
    }

    public String logout() {
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
