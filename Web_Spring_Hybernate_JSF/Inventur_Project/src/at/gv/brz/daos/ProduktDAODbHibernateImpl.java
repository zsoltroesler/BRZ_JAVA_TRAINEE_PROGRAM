package at.gv.brz.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.gv.brz.entitaeten.Produkt;


/**
 * @author roeslerz
 * 
 * ProduktDAO Implementation mit HibernateTemplate
 *
 */
@Repository
public class ProduktDAODbHibernateImpl implements ProduktDAO {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Produkt findById(int id) {
        Produkt produkt = hibernateTemplate.get(Produkt.class, id);
        return produkt;
    }

    @Override
    public List<Produkt> findAll() {
        List<Produkt> produkte = (List<Produkt>) hibernateTemplate.loadAll(Produkt.class);
        return produkte;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean insert(Produkt produkt) {
        hibernateTemplate.save(produkt);
        return true;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean update(Produkt produkt) {
        hibernateTemplate.update(produkt);
        return true;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean delete(Produkt produkt) {
        hibernateTemplate.delete(produkt);
        return true;
    }
}
