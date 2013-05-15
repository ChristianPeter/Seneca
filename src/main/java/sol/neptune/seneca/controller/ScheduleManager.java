/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sol.neptune.seneca.entities.Schedule;

/**
 *
 * @author murdoc
 */
@ManagedBean(name = "scheduleManager")
@ViewScoped
public class ScheduleManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private ScheduleFacade scheduleFacade;
    private Schedule selectedEntry;
    private DataModel<Schedule> datamodel;
    private List<Schedule> list;
    private String testString;

    public String getTestString() {
        testString = "counting: " + scheduleFacade.count();
        return testString;
    }

    public void createTest() {

        selectedEntry.setName("test");
        scheduleFacade.create(selectedEntry);
        
        init();
    }

    @PostConstruct
    public void construct() {
        selectedEntry = new Schedule();
        init();

    }

    @PreDestroy
    public void destroy() {
        datamodel = null;
        if (list != null){
            list.clear();
            list = null;
        }
        selectedEntry = null;
    }

    public void init() {
        setList(scheduleFacade.findAll());
        datamodel = new ListDataModel<Schedule>(getList());
    }

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
