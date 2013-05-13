package sol.neptune.seneca.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sol.neptune.seneca.entities.Presentation;
import sol.neptune.seneca.entities.Schedule;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-13T18:38:27")
@StaticMetamodel(ScheduleItem.class)
public class ScheduleItem_ extends AbstractEntity_ {

    public static volatile SingularAttribute<ScheduleItem, Schedule> schedule;
    public static volatile SingularAttribute<ScheduleItem, Presentation> presentation;
    public static volatile SingularAttribute<ScheduleItem, Boolean> active;

}