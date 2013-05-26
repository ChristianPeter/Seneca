/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sol.neptune.seneca.entities.Document;
import sol.neptune.seneca.entities.PresentationItem;

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

    @Override
    public void remove(Document entity) {
        Document doc = getEntityManager().merge(entity);
        for (PresentationItem item : doc.getPresentationItems()) {
            item.setDocument(null);
        }

        getEntityManager().remove(doc);
        getEntityManager().flush();

    }

    public Document findByUUID(String uuid) throws NoResultException {
        Query query = getEntityManager().createQuery("select d from Document d where d.uuid = :uuid");

        query.setParameter("uuid", uuid);
        try {
            Document d = (Document) query.getSingleResult();
            return d;
        } catch (NoResultException e) {
        }

        return null;
    }
}
