/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "schedule")
public class Schedule extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private boolean active;
    /* getter and setter */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
