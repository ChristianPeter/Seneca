/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.primefaces.model.TreeNode;
import sol.neptune.seneca.controller.PresentationFacade;
import sol.neptune.seneca.controller.PresentationItemFacade;
import sol.neptune.seneca.entities.Presentation;
import sol.neptune.seneca.entities.PresentationItem;
import sol.neptune.seneca.entities.PresentationTreeNode;

/**
 *
 * @author murdoc
 */
@Named
@ConversationScoped
public class PresentationViewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Conversation conversation;
    @EJB
    private PresentationFacade pFacade;
    @EJB
    private PresentationItemFacade piFacade;
    private TreeNode root;
    private Map<String, PresentationTreeNode> nodemap;
    private Presentation selectedPresentation;
    private PresentationItem selectedPresentationItem;
    private TreeNode selectedNode;

    @PostConstruct
    public void construct() {
        init();
    }

    @PreDestroy
    public void destroy() {
    }

    public void init() {
        List<Presentation> all = pFacade.findAll();
        if (nodemap == null) {
            nodemap = new HashMap<String, PresentationTreeNode>();
        } else {
            nodemap.clear();
        }

        root = new PresentationTreeNode();

        for (Presentation p : all) {
            PresentationTreeNode pn = new PresentationTreeNode("p", p.toString(), root);
            pn.setEntityClass(p.getClass().getName());
            pn.setEntityId(p.getId());
            nodemap.put(p.getUuid(), pn);

            for (PresentationItem i : p.getPresentationItems()) {
                PresentationTreeNode pin = new PresentationTreeNode("i", i.toString(), pn);
                pin.setEntityClass(i.getClass().getName());
                pin.setEntityId(i.getId());
                nodemap.put(i.getUuid(), pin);
            }
        }
    }

    /* event listener */
    public void addPresentation(AjaxBehaviorEvent event) {

        Presentation back1 = selectedPresentation;
        PresentationItem back2 = selectedPresentationItem;

        selectedPresentation = new Presentation();
        pFacade.create(selectedPresentation);
        PresentationTreeNode pn = new PresentationTreeNode("p", selectedPresentation.toString(), root);
        pn.setEntityClass(selectedPresentation.getClass().getName());
        pn.setEntityId(selectedPresentation.getId());
        nodemap.put(selectedPresentation.getUuid(), pn);

        selectedPresentation = back1;
        selectedPresentationItem = back2;
    }

    public void addPresentationItem(AjaxBehaviorEvent event) {

        if (selectedPresentation != null) {

            selectedPresentationItem = new PresentationItem();
            selectedPresentation.getPresentationItems().add(selectedPresentationItem);
            selectedPresentationItem.setPresentation(selectedPresentation);
            piFacade.create(selectedPresentationItem);

            PresentationTreeNode pin = new PresentationTreeNode("i", selectedPresentationItem.toString(), nodemap.get(selectedPresentation.getUuid()));
            pin.setEntityClass(selectedPresentationItem.getClass().getName());
            pin.setEntityId(selectedPresentationItem.getId());
            nodemap.put(selectedPresentationItem.getUuid(), pin);

            // since we don't want to select the new one.
            // instead we stay where we are.
            selectedPresentationItem = null;

        }

    }

    public void onSave(AjaxBehaviorEvent event) {
        if (selectedPresentation != null) {
            selectedPresentation = pFacade.edit(selectedPresentation);
            nodemap.get(selectedPresentation.getUuid()).setData(selectedPresentation.toString());

        } else if (selectedPresentationItem != null) {
            selectedPresentationItem = piFacade.edit(selectedPresentationItem);
            nodemap.get(selectedPresentationItem.getUuid()).setData(selectedPresentationItem.toString());
        }

    }

    public void onNodeSelect(NodeSelectEvent event) {
        PresentationTreeNode p = (PresentationTreeNode) event.getTreeNode();

        /*
         * 
         * autosave:*/
        if (selectedPresentation != null || selectedPresentationItem != null) {
            onSave(null); // null is okay,since we currently don't use the event at all ;-)
        }
        
        
        if (Presentation.class.getName().equals(p.getEntityClass())) {
            // presentation selected
            setSelectedPresentation(pFacade.find(p.getEntityId()));
            setSelectedPresentationItem(null);
        } else if (PresentationItem.class.getName().equals(p.getEntityClass())) {
            // presentationItem selected
            setSelectedPresentation(null);
            setSelectedPresentationItem(piFacade.find(p.getEntityId()));
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

    public void startConversation() {
        // nothing to do here since we have @PostConstruct doing our work
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String getConversationId() {
        return conversation.getId();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
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

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
