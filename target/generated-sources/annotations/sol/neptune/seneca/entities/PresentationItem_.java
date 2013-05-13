package sol.neptune.seneca.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sol.neptune.seneca.entities.Document;
import sol.neptune.seneca.entities.Presentation;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-13T18:38:27")
@StaticMetamodel(PresentationItem.class)
public class PresentationItem_ extends AbstractEntity_ {

    public static volatile SingularAttribute<PresentationItem, Document> document;
    public static volatile SingularAttribute<PresentationItem, Presentation> presentation;

}