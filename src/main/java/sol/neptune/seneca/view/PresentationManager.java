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
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sol.neptune.seneca.controller.PresentationFacade;
import sol.neptune.seneca.controller.PresentationItemFacade;
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
    @EJB
    private PresentationItemFacade piFacade;
    private List<Presentation> list;
    
    
    private TreeNode root = null;
    private TreeNode selectedNode;
    private Presentation selectedPresentation;
    private PresentationItem selectedPresentationItem;

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destroy() {
        if (list != null) {
            list.clear();
            list = null;
        }
    }

    public void init() {
        setList(facade.findAll());
        rebuildTree();
    }

    /* event listener */
    public void startConversation() {
        // nothing to do here since we have @PostConstruct doing our work
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        // unselect:
        setSelectedPresentation(null);
        setSelectedPresentationItem(null);
        Object source = event.getTreeNode().getData();

        // select new one
        if (source instanceof Presentation) {
            setSelectedPresentation((Presentation) source);

        } else if (source instanceof PresentationItem) {
            setSelectedPresentationItem((PresentationItem) source);
        }
    }

    public void onNodeExpand(NodeExpandEvent event) {
        System.out.println(event);
        System.out.println(event.getTreeNode().isExpanded());
        event.getTreeNode().setExpanded(true);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        System.out.println(event);
        System.out.println(event.getTreeNode().isExpanded());
        event.getTreeNode().setExpanded(false);
    }

    public void saveAll(AjaxBehaviorEvent event) {
        System.out.println("Save All");

        for (Presentation p : getList()) {
            facade.edit(p);
        }

        // refresh all!
        init();
        selectedPresentation = null;
        selectedPresentationItem = null;
        selectedNode = null;

        // set selection and expand / collapse state back

    }

    public void addPresentationItem(AjaxBehaviorEvent event) {
        if (selectedPresentation == null) {
            // TODO: add message
            return;
        }
        PresentationItem pi = new PresentationItem();
        //pi.setPresentation(selectedPresentation);
        selectedPresentation.getPresentationItems().add(pi);
        piFacade.create(pi);
        rebuildTree();
    }

    public void addPresentation(AjaxBehaviorEvent event) {
        Presentation p = new Presentation();
        facade.create(p);
        setSelectedPresentation(p);
        setSelectedPresentationItem(null);
        // add at the end
        getList().add(p);
        rebuildTree();
    }

    /* helper*/
    private void rebuildTree() {
        root = new DefaultTreeNode();
        for (Presentation p : getList()) {
            DefaultTreeNode pnode = new DefaultTreeNode("p", p, root);
            if (selectedPresentation != null && selectedPresentation.equals(p)) {
                pnode.setExpanded(true);
                setSelectedNode(pnode);
            }
            // level for p-items:
            for (PresentationItem pi : p.getPresentationItems()) {
                DefaultTreeNode pinode = new DefaultTreeNode("i", pi, pnode);
                if (selectedPresentationItem != null && selectedPresentationItem.equals(pi)) {
                    pnode.setExpanded(true);

                    setSelectedNode(pinode);
                }
            }
        }
    }
    /* getter & setter */

    public List<Presentation> getList() {
        return list;
    }

    public void setList(List<Presentation> list) {
        this.list = list;
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

    public String getConversationId() {
        return conversation.getId();
    }
}
