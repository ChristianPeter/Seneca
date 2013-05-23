/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.view;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import sol.neptune.seneca.entities.DocumentType;
import sol.neptune.seneca.entities.ViewportConfig;

/**
 *
 * @author murdoc
 */
@Named
@ApplicationScoped
public class EnumProducers implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Produces
    @Named("viewportConfigs")
    public ViewportConfig[] getViewportConfigs(){
        return ViewportConfig.values();
    }
    
    @Produces
    @Named("documentTypes")
    public DocumentType[] getDocumentTypes(){
        return DocumentType.values();
    }
    
}
