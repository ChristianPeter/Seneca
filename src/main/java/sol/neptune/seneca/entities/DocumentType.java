/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

/**
 *
 * @author murdoc
 */
public enum DocumentType {

    PDF("application/pdf"),
    TEXT("text/plain"),
    PICTURE("image/jpeg"),
    PPT("application/powerpoint"),
    VPLAN("NA");
    private String contentType;

    private DocumentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
