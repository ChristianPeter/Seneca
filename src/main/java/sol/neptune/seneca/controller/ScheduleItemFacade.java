/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sol.neptune.seneca.entities.ScheduleItem;

/**
 *
 * @author murdoc
 */
@Stateless
public class ScheduleItemFacade extends AbstractFacade<ScheduleItem> {
    @PersistenceContext(unitName = "sol.neptune_Seneca_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScheduleItemFacade() {
        super(ScheduleItem.class);
    }
    
}
