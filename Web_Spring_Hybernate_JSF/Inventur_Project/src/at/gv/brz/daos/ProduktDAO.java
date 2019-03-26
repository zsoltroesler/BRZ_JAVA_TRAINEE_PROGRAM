package at.gv.brz.daos;

import java.util.List;
import at.gv.brz.entitaeten.Produkt;

/**
 * @author roeslerz
 *
 * Data Access Object Interface für {@Produkt}
 */
public interface ProduktDAO{
       
	public Produkt findById(int id);	
	
	public List<Produkt> findAll(); 
	
	public boolean insert(Produkt produkt);
	
	public boolean update(Produkt produkt);
	
	public boolean delete(Produkt produkt);

}
