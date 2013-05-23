/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "viewport")
public class Viewport extends AbstractEntity implements Serializable {
    @ManyToMany(mappedBy = "viewports")
    private Set<Presentation> presentations = new HashSet<Presentation>();
    
    
    private static final long serialVersionUID = 1L;

    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(length=16)
    private ViewportConfig config = ViewportConfig.FULL;
    
  
    /* getter & setter */
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ViewportConfig getConfig() {
        return config;
    }

    public void setConfig(ViewportConfig config) {
        this.config = config;
    }

    
    
}
