package exam4;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test02_TypedQuerry {

	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			TypedQuery<Member4> query = em.createQuery(
					"SELECT m FROM Member4 m ORDER BY m.name", 
					Member4.class);
			List<Member4> result = query.getResultList();
			em.getTransaction().commit();
			
			if (result.isEmpty()) {
				System.out.println("사용자가 없습니다.");
			} else {
				result.forEach(user ->
						System.out.printf(
								"| %s | %s |%tY-%<tm-%<td | \n",
								user.getEmail(), user.getName(), user.getCreateDate()));
			}
			
		} catch(Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.close();
		emf.close();

	}

}
