/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sol.neptune.seneca.entities.Document;

/**
 *
 * @author murdoc
 */
@Stateless
public class DocumentFacade extends AbstractFacade<Document> {
    @PersistenceContext(unitName = "sol.neptune_Seneca_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentFacade() {
        super(Document.class);
    }
    
}
