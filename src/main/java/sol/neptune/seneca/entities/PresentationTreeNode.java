/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author murdoc
 */
public class PresentationTreeNode extends DefaultTreeNode{
    private Long entityId;
    private String entityClass;
    
    
    private String label;

    public PresentationTreeNode() {
    }

    public PresentationTreeNode(String type, Object data, TreeNode parent) {
        super(type, data, parent);
    }
    
    

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
