/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "document")
public class Document extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;
    private String filename;
    
    @Enumerated(EnumType.STRING)
    @Column(length=16)
    private DocumentType documentType = DocumentType.PICTURE;
    
    @Lob
    private byte[] imageData;
    
    @OneToMany(mappedBy = "document",  cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<PresentationItem> presentationItems = new HashSet<PresentationItem>();

    
    /* getter and setter */
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Set<PresentationItem> getPresentationItems() {
        return presentationItems;
    }

    public void setPresentationItems(Set<PresentationItem> presentationItems) {
        this.presentationItems = presentationItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
    
    
    
    
    
}
