package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티 매니저 생성, 디비 연결도 된다.
        EntityManager em = emf.createEntityManager(); //닫기 전에 동작하는 코드들을 작성
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원2");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select distinct t from Team t join fetch t.members m";

            List<Team> result = em.createQuery(query, Team.class).getResultList();

            for (Team team : result) {
                System.out.println("team = " + team.getName() + "," + team.getMembers().size());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();

            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}


