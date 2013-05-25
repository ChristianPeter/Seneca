/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import java.io.File;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sol.neptune.seneca.controller.DocumentFacade;
import sol.neptune.seneca.entities.Document;
import sol.neptune.seneca.entities.DocumentType;

/**
 *
 * @author murdoc
 */
@Named
@RequestScoped
@Path("/api/document")
public class DocumentService implements Serializable {

    private static final long serialVersionUID = 12345L;
    @Inject
    private DocumentFacade documentFacade;

    public void test() {
        System.out.println("Test");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String demo() {
        return "hello world";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public void upload(File file) {
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.length());


        // delete file to free disk space:

        file.delete();

    }

    @PUT
    @Path("{id}/{token}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public void uploadToDocument(File file, @PathParam("id") Long id, @PathParam("token") String token) {
        System.out.println(file);
        System.out.println("id: " + id);
        System.out.println("token: " + token);
        Document d = documentFacade.find(id);
        
        if (d!= null){
            // check if token matches uuid:
            if (token.equals(d.getUuid())){
                System.out.println(d.getFilename());
                
            }
            else{
                System.out.println("wrong token");
            }
        }
        else{
            System.out.println("doc not found");
        }

    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public DocumentWrapper prepareUpload(@FormParam("filename") String filename, @FormParam("filesize") int filesize) {

        DocumentWrapper dw = new DocumentWrapper();
        dw.setFilename(filename);
        dw.setSize(filesize);


        Document d = new Document();
        d.setFilename(filename);
        d.setName(filename);
        d.setDocumentType(DocumentType.TEXT); // TODO: doctype detection

        documentFacade.create(d);

        dw.setUploadToId(d.getId());
        dw.setToken(d.getUuid());



        return dw;
    }
    
    
    /* as path param */
    
    @PUT
    @Path("{filename}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces({MediaType.APPLICATION_JSON})
    public String uploadFile(File file, @PathParam("filename") String filename){
        System.out.println(file);
        System.out.println(filename);
        return "";
    }
    
    /* as query param */
    
    @PUT
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces({MediaType.APPLICATION_JSON})
    public String uploadFileQP(File file, @QueryParam("filename") String filename){
        System.out.println(file);
        System.out.println(filename);
        return "";
    }
    
    
}
