/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;

/**
 *
 * @author murdoc
 */
public interface PersistentEntity<PK extends Serializable> extends Serializable {

    PK getId();

    boolean isNew();
    
}
