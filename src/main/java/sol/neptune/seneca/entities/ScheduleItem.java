/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "scheditem")
public class ScheduleItem extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "pres_id")
    private Presentation presentation;
    @ManyToOne
    @JoinColumn(name = "sched_id")
    private Schedule schedule;
    
    
    @ManyToOne
    private Viewport viewport;
    private boolean active;
    

    /* getter and setter */
    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        // not sure if all this logic is really required...
        if(this.getSchedule() != null){
            this.getSchedule().getScheduleItems().remove(this);
        }
        this.schedule = schedule;
        this.getSchedule().getScheduleItems().add(this);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
    
    
    
}
