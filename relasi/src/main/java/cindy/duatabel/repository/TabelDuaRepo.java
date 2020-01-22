package cindy.duatabel.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import cindy.duatabel.model.TabelDua;
import cindy.duatabel.intfc.TabelDuaInterface;

@Singleton
public class TabelDuaRepo implements TabelDuaInterface {
    @PersistenceContext
    private EntityManager entityManager;

    public TabelDuaRepo(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TabelDua> findAll(int page, int limit) {
        TypedQuery<TabelDua> query = entityManager.createQuery("from TabelDua", TabelDua.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0).setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public TabelDua save(@NotNull TabelDua tabelDua) {
        entityManager.persist(tabelDua);
        return tabelDua;
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return entityManager.createQuery("select count(*) from TabelDua", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public TabelDua findById(@NotNull Long id) {
        return entityManager.find(TabelDua.class, id);
    }

   
    @Transactional
    @Override
    public TabelDua update(@NotNull Long id, TabelDua tabelDua) {
        String name = tabelDua.getName();
        String category = tabelDua.getCategory();
        tabelDua = entityManager.find(TabelDua.class, id);
        if (name != null)
            tabelDua.setName(name);
        if (category != null)
            tabelDua.setCategory(category);
        tabelDua.setUpdated_at(new Date());
        return tabelDua;
    }

    @Transactional
    @Override
    public TabelDua destroy(@NotNull Long id) {
        TabelDua tabelDua = entityManager.find(TabelDua.class, id);
        // if(projecttype != null) {
        // entityManager.remove(projecttype);
        // }
        entityManager.merge(tabelDua);
        tabelDua.setDeleted_at(new Date());
        return tabelDua;
    }
}