package es.upm.dit.isst.tfgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import es.upm.dit.isst.tfgs.model.TFG;

public interface TFGDAO {
	
	public TFG createTFG(EntityManager em, TFG tfg);
	public List<TFG> readTFGs(EntityManager em);
	public void updateTFG(EntityManager em, TFG tfg);
	public void deleteTFG(EntityManager em, TFG tfg);
	
}
