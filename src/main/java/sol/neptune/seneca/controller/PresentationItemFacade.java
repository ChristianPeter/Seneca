/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sol.neptune.seneca.entities.PresentationItem;

/**
 *
 * @author murdoc
 */
@Stateless
public class PresentationItemFacade extends AbstractFacade<PresentationItem> {
    @PersistenceContext(unitName = "sol.neptune_Seneca_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PresentationItemFacade() {
        super(PresentationItem.class);
    }
    
}
