/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import java.io.Serializable;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author murdoc
 */
@Named
@Path("/api/document")
public class DocumentService implements Serializable {

    private static final long serialVersionUID = 12345L;

    public void test() {
        System.out.println("Test");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String demo() {
        return "hello world";
    }
}
