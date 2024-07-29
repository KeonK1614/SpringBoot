package exam3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test03_Update {
	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Member3 user = em.find(Member3.class, "test@test.com");
			if (user == null) {
				System.out.println("존재하지 않습니다.");
				em.getTransaction().rollback();
				return;
			}
			user.changeName("전우치");
			
			em.getTransaction().commit();
			System.out.println("이름을 변경했습니다.");
		} catch(Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.close();
		emf.close();
	}
}
