package exam3;

import exam4.Member4;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test04_Delete {

	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Member4 user = em.find(Member4.class, "test@test.com");
			if (user == null) {
				System.out.println("존재하지 않습니다.");
				em.getTransaction().rollback();
				return;
			}
			em.remove(user);
			
			em.getTransaction().commit();
			System.out.println("탈퇴처리 했습니다.");
		} catch(Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.close();
		emf.close();
	}

}
