/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author murdoc
 */
@XmlRootElement
public class DocumentWrapper {
    private String filename;
    private int size;
    private String token;
    private Long uploadToId;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUploadToId() {
        return uploadToId;
    }

    public void setUploadToId(Long uploadToId) {
        this.uploadToId = uploadToId;
    }

 
    
    
    
}
