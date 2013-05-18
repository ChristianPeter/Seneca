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
import sol.neptune.seneca.entities.UserAccount;

/**
 *
 * @author murdoc
 */
@Stateless
public class UserAccountFacade extends AbstractFacade<UserAccount> {

    @PersistenceContext(unitName = "sol.neptune_Seneca_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserAccountFacade() {
        super(UserAccount.class);
    }

    /* additional methods */
    public UserAccount findByUsername(String username) {
        Query query = getEntityManager().createQuery("select u from UserAccount u where u.userName = :username");

        query.setParameter("username", username);
        try {
            return (UserAccount) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
//        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//        javax.persistence.criteria.CriteriaQuery cq = criteriaBuilder.createQuery();
//        Root from = cq.from(UserAccount.class);
//        
//        cq.select(from).where(criteriaBuilder.equal(from.get("username"), username));        
//        
//        return (UserAccount) getEntityManager().createQuery(cq).getSingleResult();
    }
}
