/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "viewport")
public class Viewport extends AbstractEntity implements Serializable {
    
    
    private static final long serialVersionUID = 1L;

    private String name;
    
    @OneToMany(mappedBy = "viewport")
    private List<ScheduleItem> scheduleItems;

    /* getter & setter */
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public void setScheduleItems(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }
    
    
    
    
}
