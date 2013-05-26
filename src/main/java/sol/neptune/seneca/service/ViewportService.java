/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Path;

/**
 *
 * @author murdoc
 */
@Named
@RequestScoped
@Path("/api/viewport")
public class ViewportService implements Serializable {
}
