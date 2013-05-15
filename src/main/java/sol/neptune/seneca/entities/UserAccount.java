/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author murdoc
 */
@Entity
@Table(name = "useraccount",
        uniqueConstraints=@UniqueConstraint(columnNames = {"username"}))

public class UserAccount extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private String givenName;
    private String familyName;
    @Column(name = "username")
    private String userName;
    private String password;
    
    
    
    /* getter & setter */
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
