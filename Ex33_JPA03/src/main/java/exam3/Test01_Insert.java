package exam3;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test01_Insert {

	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			
			Member3 user = new Member3("test@test.com", "홍길동", LocalDate.now());
			System.out.println(111);
			em.persist(user);
			System.out.println(222);

			
			em.getTransaction().commit();
			System.out.println(333);
			System.out.println("가입요청을 처리했습니다.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} 
		em.close();
		emf.close();

	}

}
