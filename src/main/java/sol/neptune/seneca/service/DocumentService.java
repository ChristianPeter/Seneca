/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @GET
    //@Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("{file}")
    public Response download(@PathParam("file") String file) {
        if (file == null || file.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Document d= documentFacade.findByUUID(file);
        
        if (d != null) {
            return Response.ok(new ByteArrayInputStream(d.getImageData()),"image/jpeg").build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /* upload a file as query param */
    @PUT
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadFileQP(File file, @QueryParam("filename") String filename, @QueryParam("type") String type) {
        System.out.println(file);
        System.out.println(filename);
        System.out.println(type);

        // TODO: do validity check..... too big exception etc.?
        Document d = new Document();
        d.setFilename(sanitizeFilename(filename));
        d.setDocumentType(convertFiletypeToDocumentType(type));
        d.setName(d.getFilename()); // let the user later decide how to name it
        try {
            // read file
            d.setImageData(readFromFile(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // delete file to free disk space:
            file.delete();
        }
        System.out.println("loaded: " + d.getImageData().length);


        documentFacade.create(d);
        try {
            List<Document> converted = PowerPointConverter.convertPPTX(d.getImageData());
            
            for (Document dd: converted){
                documentFacade.create(dd);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return Response.ok().build();
    }

    private byte[] readFromFile(File f) throws FileNotFoundException, IOException {
        byte[] data = Files.readAllBytes(f.toPath());
        return data;
    }

    private String sanitizeFilename(String filename) {
        // TODO: validate user input here again! ensure filename is valid. but it doesn't matter because there's no real usage for the filename anyhow.
        return filename;
    }

    private DocumentType convertFiletypeToDocumentType(String filetype) {
        // TODO: implement filetype -> doctype handling
        return DocumentType.PICTURE;
    }
}
/*
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
 */

/* as path param */
//    @PUT
//    @Path("{filename}")
//    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
//    @Produces({MediaType.APPLICATION_JSON})
//    public String uploadFile(File file, @PathParam("filename") String filename){
//        System.out.println(file);
//        System.out.println(filename);
//        return "";
//    }
//    