package cindy.duatabel.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tabelsatu")
public class TabelSatu {


    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable=false, updatable=false)
    private TabelDua tabelDua;
    private Long tabelDua_id;

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "name harus diisi")
    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = true)
    private String email;

   
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at", nullable = true)
    private Date deleted_at;

    public TabelSatu(){}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    

    public TabelDua getTabelDua() {
        return tabelDua;
    }

    public void setTabelDua(TabelDua tabelDua) {
        this.tabelDua = tabelDua;
    }

    public Long getTabelDua_id() {
        return tabelDua_id;
    }

    public void setTabelDua_id(Long tabelDua_id) {
        this.tabelDua_id = tabelDua_id;
    }

  
}    
