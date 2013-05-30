/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author murdoc
 */
@MappedSuperclass
public abstract class AbstractEntity implements PersistentEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String uuid;

    public String getUuid() {
        if (this.uuid == null) {
            setUuid(UUID.randomUUID().toString());
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @Version
    private Integer objectVersion;

    public Integer getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(Integer objectVersion) {
        this.objectVersion = objectVersion;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastModified;

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean isNew() {
        return (this.id == null);
    }

//    @Override
//    public abstract boolean equals(Object object);
//
//    @Override
//    public abstract int hashCode();
//
//    @Override
//    public abstract String toString();
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUuid()!= null ? getUuid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity other = (AbstractEntity) object;
        if ((this.getUuid() == null && other.getUuid() != null) || (this.getUuid() != null && !this.getUuid().equals(other.getUuid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity: " + this.getClass().getName() + "[ id=" + getId() + " ] uuid: "+ getUuid();
    }

    @PrePersist
    public void initDateCreated() {
        setDateCreated(new Date());
        setLastModified(new Date());
        if (getUuid() == null) {
            setUuid(UUID.randomUUID().toString());
        }
    }

    @PreUpdate
    public void updateLastModified() {
        setLastModified(new Date());
    }
}
