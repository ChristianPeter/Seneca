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
import sol.neptune.seneca.controller.DocumentFacade;
import sol.neptune.seneca.entities.Document;

/**
 *
 * @author murdoc
 */
@Named("documentManager")
@ConversationScoped
public class DocumentManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Conversation conversation;
    
    @EJB
    private DocumentFacade facade;
    
    private List<Document> list;
    private DataModel<Document> model;
    private Document current;
    
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
        model = new ListDataModel<Document>(getList());
    }

    public String create() {
        setCurrent(new Document());
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

    public List<Document> getList() {
        return list;
    }

    public void setList(List<Document> list) {
        this.list = list;
    }

    public DataModel<Document> getModel() {
        return model;
    }

    public void setModel(DataModel<Document> model) {
        this.model = model;
    }

    public Document getCurrent() {
        return current;
    }

    public void setCurrent(Document current) {
        this.current = current;
    }
    
    
    
    
}
