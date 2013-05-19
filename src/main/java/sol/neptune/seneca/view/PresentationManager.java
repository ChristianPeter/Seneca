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
import sol.neptune.seneca.controller.PresentationFacade;
import sol.neptune.seneca.entities.Presentation;

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
    
    
    
    
}
