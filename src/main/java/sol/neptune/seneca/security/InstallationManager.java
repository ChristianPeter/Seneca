/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.security;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import sol.neptune.seneca.controller.PresentationFacade;
import sol.neptune.seneca.controller.ScheduleFacade;
import sol.neptune.seneca.controller.ScheduleItemFacade;
import sol.neptune.seneca.controller.UserAccountFacade;
import sol.neptune.seneca.entities.Presentation;
import sol.neptune.seneca.entities.Schedule;
import sol.neptune.seneca.entities.ScheduleItem;
import sol.neptune.seneca.entities.UserAccount;

/**
 *
 * @author murdoc
 */
@Singleton
@Startup
public class InstallationManager  {

    @EJB 
    private UserAccountFacade userAccountFacade;

    @EJB
    private ScheduleFacade scheduleFacade;
    
    @EJB
    private ScheduleItemFacade scheduleItemFacade;
    
    @EJB
    private PresentationFacade presentationFacade;
    
    @PostConstruct
    public void test() {
       createAdmin();
       createSchedule();
    }
    
    public void createSchedule(){
        Schedule schedule = new Schedule();
        schedule.setActive(true);
        schedule.setDescription("demo schedule");
        schedule.setName("default");
        
        ScheduleItem item = new ScheduleItem();
        item.setActive(true);
        item.setSchedule(schedule);
        
        Presentation pres = new Presentation();
        pres.setName("my presentation");
        pres.setDescription("lorem ipsum");
        item.setPresentation(pres);
        
        scheduleItemFacade.create(item);
        
        
        presentationFacade.create(pres);
        scheduleFacade.create(schedule);
        
        
        
        
    }
    public void createAdmin(){
         Logger.getLogger(InstallationManager.class.getName()).log(Level.INFO, "searching for admin user");
        UserAccount admin = userAccountFacade.findByUsername("admin");
//       UserAccount admin = null;
        
        if (admin == null){
            
            Logger.getLogger(InstallationManager.class.getName()).log(Level.INFO, "no admin. creating one.");
            admin = new UserAccount();
            admin.setUserName("admin");
            admin.setPassword("batman!");
            admin.setFamilyName("Sysop");
            admin.setGivenName("Friendly");
            userAccountFacade.create(admin);
        }
        
        Logger.getLogger(InstallationManager.class.getName()).log(Level.INFO, "admin user: {0}",admin);
    }
}
