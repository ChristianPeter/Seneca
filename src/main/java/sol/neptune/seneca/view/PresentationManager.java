/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sol.neptune.seneca.controller.PresentationFacade;
import sol.neptune.seneca.entities.Presentation;
import sol.neptune.seneca.entities.PresentationItem;

/**
 *
 * @author murdoc
 */
@Named("presentationManager")
@ConversationScoped
public class PresentationManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Conversation conversation;
    
    @EJB
    private PresentationFacade facade;
    
    private List<Presentation> list;
    private DataModel<Presentation> model;
    private Presentation current;
    
    
    private TreeNode root = null;
    private TreeNode selectedNode;
    
    
    private Presentation selectedPresentation;
    private PresentationItem selectedPresentationItem;
    
    @PostConstruct
    public void construct() {

        if (conversation.isTransient()) {
            conversation.begin();
        }
        init();

    }

    @PreDestroy
    public void destroy() {
        model = null;
        if (list != null) {
            list.clear();
            list = null;
        }
        current = null;
    }
    
    public void init() {
        setList(facade.findAll());
        model = new ListDataModel<Presentation>(getList());
        
        root = new DefaultTreeNode();
        for (Presentation p : getList()){
            TreeNode pnode = new DefaultTreeNode("p",p,root);
            // level for p-items:
            for (PresentationItem pi : p.getPresentationItems()){
                TreeNode pinode = new DefaultTreeNode("i", pi, pnode);
            }
        }
        
    }

    public String create() {
        setCurrent(new Presentation());
        return "create?faces-redirect=true";
    }

    public String edit() {
        setCurrent(model.getRowData());
        return "edit?faces-redirect=true";
    }

    public String save() {

        if (current.isNew()) {
            facade.create(current);

        } else {
            facade.edit(current);
        }
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "show?faces-redirect=true";
    }

    public String cancel() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "show?faces-redirect=true";
    }
    
    public String delete(){
        setCurrent(model.getRowData());
        facade.remove(current);
        current = null;
        init();
        return "";
    }

    
    /* event listener */
    
    public void onNodeSelect(NodeSelectEvent event){
        // unselect:
        setSelectedPresentation(null);
        setSelectedPresentationItem(null);
        Object source = event.getTreeNode().getData();

        // select new one
        if (source instanceof Presentation){
            setSelectedPresentation((Presentation) source);
            
        }
        else if (source instanceof PresentationItem){
            setSelectedPresentationItem((PresentationItem) source);
        }
    }
    
    /* getter & setter */
    
    public List<Presentation> getList() {
        return list;
    }

    public void setList(List<Presentation> list) {
        this.list = list;
    }

    public DataModel<Presentation> getModel() {
        return model;
    }

    public void setModel(DataModel<Presentation> model) {
        this.model = model;
    }

    public Presentation getCurrent() {
        return current;
    }

    public void setCurrent(Presentation current) {
        this.current = current;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Presentation getSelectedPresentation() {
        return selectedPresentation;
    }

    public void setSelectedPresentation(Presentation selectedPresentation) {
        this.selectedPresentation = selectedPresentation;
    }

    public PresentationItem getSelectedPresentationItem() {
        return selectedPresentationItem;
    }

    public void setSelectedPresentationItem(PresentationItem selectedPresentationItem) {
        this.selectedPresentationItem = selectedPresentationItem;
    }
    
    
    
    
    
    
}
