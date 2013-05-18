/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import sol.neptune.seneca.entities.Schedule;
import sol.neptune.seneca.service.DocumentService;

/**
 *
 * @author murdoc
 */
//@ManagedBean(name = "scheduleManager")
//@ViewScoped
@Named("scheduleManager")
@ConversationScoped
@ManagedBean
public class ScheduleManager implements Serializable {

    private static final long serialVersionUID = 815L;
    @Inject
    private Conversation conversation;
    @EJB
    private ScheduleFacade scheduleFacade;
    private Schedule selectedEntry;
    private DataModel<Schedule> datamodel;
    private List<Schedule> list;
    @Inject
    private DocumentService documentService;
    private String testString;

    public String getTestString() {
        testString = "counting: " + scheduleFacade.count();
        return testString;
    }

    @PostConstruct
    public void construct() {

        if (conversation.isTransient()) {
            conversation.begin();
        }
        init();

    }

    @PreDestroy
    public void destroy() {
        datamodel = null;
        if (list != null) {
            list.clear();
            list = null;
        }
        selectedEntry = null;
        //conversation.end();
    }

    public void init() {
        setList(scheduleFacade.findAll());
        datamodel = new ListDataModel<Schedule>(getList());
    }

    public String create() {
        setSelectedEntry(new Schedule());
        return "create?faces-redirect=true";
    }

    public String edit() {
        setSelectedEntry(datamodel.getRowData());
        return "edit?faces-redirect=true";
    }

    public String save() {

        if (selectedEntry.isNew()) {
            scheduleFacade.create(selectedEntry);

        } else {
            scheduleFacade.edit(selectedEntry);
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
    /* getter & setter */

    public Schedule getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(Schedule selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    public DataModel<Schedule> getDatamodel() {
        return datamodel;
    }

    public void setDatamodel(DataModel<Schedule> datamodel) {
        this.datamodel = datamodel;
    }

    public List<Schedule> getList() {
        return list;
    }

    public void setList(List<Schedule> list) {
        this.list = list;
    }
}
