package exam3;

import java.time.LocalDate;

import exam4.Member4;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test02_Select {

	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		Member4 user = em.find(Member4.class, "test@test.com");
		System.out.println("[" + user + "]");
		if (user != null) {
			System.out.println("이름 : " + user.getName());
			System.out.printf("생성 : %tY-%<tm-%<td\n", user.getCreateDate());
		} else {
			System.out.println("존재하지 않습니다.");
		}
		em.close();
		emf.close();
	}

}
