package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Donors;

public class DonorHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Donors");
	
	public void insertAmount(Donors d) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Donors> showAllDonors() {
		EntityManager em = emfactory.createEntityManager();
		List<Donors> allDonors = em.createQuery("SELECT i FROM Donors i").getResultList();
		return allDonors;
	}
	
	public void deleteDonor (Donors toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Donors> typedQuery = em.createQuery("select d from Donors d where d.name = :selectedName and d.amount = :selectedAmount", Donors.class);
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedAmount", toDelete.getAmount());
		typedQuery.setMaxResults(1);
		Donors result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public Donors searchForDonorById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Donors found = em.find(Donors.class, idToEdit);
		em.close();

		return found;
	}
	
	public void updateDonor(Donors toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<Donors> searchForDonorByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Donors> typedQuery = em.createQuery("select d from Donors d where d.name = :selectedName", Donors.class);
		typedQuery.setParameter("selectedName", name);
		List<Donors> foundDonor = typedQuery.getResultList();
		em.close();
		
		return foundDonor;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
}
