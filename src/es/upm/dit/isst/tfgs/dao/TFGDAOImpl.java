package es.upm.dit.isst.tfgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import es.upm.dit.isst.tfgs.model.TFG;

public class TFGDAOImpl implements TFGDAO{

	private static TFGDAOImpl instance;
	private TFGDAOImpl () {
	}
	public static TFGDAOImpl getInstance() {
		if (instance == null)
			instance = new TFGDAOImpl();
		return instance;
	}
	
	@Override
	public TFG createTFG(EntityManager em, TFG tfg) {
		em.getTransaction().begin();
		em.persist(tfg);
		em.getTransaction().commit();
		
		return tfg;
	}

	@Override
	public List<TFG> readTFGs(EntityManager em) {
		Query q = em.createQuery("select t from TFG t");
		List<TFG> res = q.getResultList();
		return res;
	}

	@Override
	public void updateTFG(EntityManager em, TFG tfg) {
		em.getTransaction().begin();
		em.merge(tfg);
		em.getTransaction().commit();
	}

	@Override
	public void deleteTFG(EntityManager em, TFG tfg) {
		em.getTransaction().begin();
		em.remove(tfg);
		em.getTransaction().commit();
	}

}
