
package cindy.duatabel.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import cindy.duatabel.model.TabelSatu;
import cindy.duatabel.intfc.TabelSatuInterface;

@Singleton
public class TabelSatuRepository implements TabelSatuInterface {

    @PersistenceContext 
    private EntityManager manager;

    public TabelSatuRepository(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size(){
        Long count = manager.createQuery("select count(*) from TabelSatu", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TabelSatu> findAll(int page, int limit){
        TypedQuery<TabelSatu> query = manager
            .createQuery("from School", TabelSatu.class)
            .setFirstResult(page > 1 ? page * limit - limit : 0 )
            .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public TabelSatu findById(@NotNull Long id) {
        TabelSatu query = manager.find(TabelSatu.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String name, String email){
        try {

            // handle nilai null, ketika ubah data school awal dari (super admin)
            if (name == null) {
                name = "";
            }
            if(email == null){
                email = "";
            }
            // proses ubah data school di database
            TabelSatu sc = manager.find(TabelSatu.class, id);
            if (name.equals("-")==false) sc.setName(name);
            if (email.equals("-")==false) sc.setEmail(email);
            sc.setUpdated_at(new Date());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional
    public Long save(@NotNull TabelSatu tabelSatu){
        try {
            manager.persist(tabelSatu);
            return tabelSatu.getId();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean destroy(@NotNull Long id){
        try {
            TabelSatu tabelSatu = manager.find(TabelSatu.class, id);
            manager.remove(tabelSatu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
