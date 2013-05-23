/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "presentation")
public class Presentation extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private boolean active = true;
    @OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresentationItem> presentationItems = new ArrayList<PresentationItem>();
    @ManyToMany
    private Set<Viewport> viewports = new HashSet<Viewport>();


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

    public List<PresentationItem> getPresentationItems() {
        return presentationItems;
    }

    public void setPresentationItems(List<PresentationItem> presentationItems) {
        this.presentationItems = presentationItems;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Viewport> getViewports() {
        return viewports;
    }

    public void setViewports(Set<Viewport> viewports) {
        this.viewports = viewports;
    }
}
