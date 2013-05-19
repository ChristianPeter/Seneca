/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import sol.neptune.seneca.controller.DocumentFacade;
import sol.neptune.seneca.controller.PresentationFacade;
import sol.neptune.seneca.controller.UserAccountFacade;
import sol.neptune.seneca.entities.Document;
import sol.neptune.seneca.entities.Presentation;
import sol.neptune.seneca.entities.PresentationItem;
import sol.neptune.seneca.entities.UserAccount;

/**
 *
 * @author murdoc
 */
@Singleton
@Startup
public class InstallationManager {

    @EJB
    private UserAccountFacade userAccountFacade;
    @EJB
    private PresentationFacade presentationFacade;
    @EJB
    private DocumentFacade documentFacade;

    @PostConstruct
    public void test() {
        createAdmin();
        createPresentation();
    }

    public void createAdmin() {
        Logger.getLogger(InstallationManager.class.getName()).log(Level.INFO, "searching for admin user");
        UserAccount admin = userAccountFacade.findByUsername("admin");
//       UserAccount admin = null;

        if (admin == null) {

            Logger.getLogger(InstallationManager.class.getName()).log(Level.INFO, "no admin. creating one.");
            admin = new UserAccount();
            admin.setUserName("admin");
            admin.setPassword("batman!");
            admin.setFamilyName("Sysop");
            admin.setGivenName("Friendly");
            userAccountFacade.create(admin);
        }

        Logger.getLogger(InstallationManager.class.getName()).log(Level.INFO, "admin user: {0}", admin);
    }

    private void createPresentation() {
        Presentation p = new Presentation();
        p.setName("Demo presentation");
        p.setDescription("this is some kind of demo.");

        Document d = new Document();

        PresentationItem i = new PresentationItem();
        i.setDuration(10);
        i.setPresentation(p);
        p.getPresentationItems().add(i);
        i.setDocument(d);
        d.getPresentationItems().add(i);


        presentationFacade.create(p);



    }
}
