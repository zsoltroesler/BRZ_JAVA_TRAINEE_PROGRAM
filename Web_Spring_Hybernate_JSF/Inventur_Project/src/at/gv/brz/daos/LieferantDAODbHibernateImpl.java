package at.gv.brz.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.gv.brz.entitaeten.Lieferant;

/**
 * @author roeslerz
 *
 * LieferantDAO Implementation mit HibernateTemplate
 */
@Repository
public class LieferantDAODbHibernateImpl implements LieferantDAO {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
    @Override
    public Lieferant findById(int id) {
        Lieferant lieferant = hibernateTemplate.get(Lieferant.class, id);
        return lieferant;
       
    }

    @Override
    public List<Lieferant> findAll() {
        List<Lieferant> lieferant = hibernateTemplate.loadAll(Lieferant.class);
        return lieferant;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean insert(Lieferant lieferant) {
        System.out.println("INSERT");
        hibernateTemplate.save(lieferant);
        return true;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean update(Lieferant lieferant) {
        System.out.println(lieferant);
        hibernateTemplate.update(lieferant);
        return true;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean delete(Lieferant lieferant) {
        hibernateTemplate.delete(lieferant);
        return true;
    }

}
