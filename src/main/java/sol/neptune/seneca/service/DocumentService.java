/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import java.io.Serializable;
import javax.inject.Named;

/**
 *
 * @author murdoc
 */
@Named
public class DocumentService implements Serializable{
    
    private static final long serialVersionUID = 12345L;
    public void test(){
        System.out.println("Test");
    }
}
