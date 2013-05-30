/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sol.neptune.seneca.entities.Presentation;

/**
 *
 * @author murdoc
 */
@Stateless
public class PresentationFacade extends AbstractFacade<Presentation> {
    @PersistenceContext(unitName = "sol.neptune_Seneca_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PresentationFacade() {
        super(Presentation.class);
    }
    
    public Presentation update(Presentation p){
        return em.merge(p);
    }
}
