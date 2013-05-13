/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "document")
public class Document extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Lob
    private byte[] imageData;

    /* getter and setter */
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
