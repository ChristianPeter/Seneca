/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import sol.neptune.seneca.entities.Schedule;

/**
 *
 * @author murdoc
 */
@ManagedBean(name = "scheduleManager")
@SessionScoped
public class ScheduleManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private Schedule selectedEntry;
    private DataModel<Schedule> datamodel;
    private List<Schedule> list;
}
