package hellojpa;

import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLOutput;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티 매니저 생성, 디비 연결도 된다.
        EntityManager em = emf.createEntityManager(); //닫기 전에 동작하는 코드들을 작성
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            /*
            //저장
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//
//            em.persist(member);

            //출력
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName());

            //삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

            //수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
            //exx.persist(); 를 안해줘도 된다.         --> 이게 영속성

            //JPQL
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

             */

            /*
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();
            System.out.println("==============================");

             */

            /*
            Member member = em.find(Member.class, 155L);
            member.setName("qweqwe");

            em.detach(member);

             */

            /*
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);

            em.persist(movie);

 */

            /*
            Team team = new Team();
            team.setName("temaA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("qwe");
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member.getId());

            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("=========");
            m.getTeam().getName();
            System.out.println("=========");

             */

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getUsername());

            /*
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

             */

            /*
            Address address = new Address("city", "street", "1000");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member1");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            member.getHomeAddress().setCity("newCity");


            em.flush();
            em.clear();

             */

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findmovie = " + findMovie);
            /*
            //JPQL
            List<Member>result = em.createQuery("select m from Member m where m.username like '%kim%'", Member.class).getResultList();
             */

            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            //루트 클래스 (조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);
            //쿼리 생성 CriteriaQuery<Member> cq =
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();

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

    /*
    private static void printMember(Member member) {
        String username = member.getUsername();
        System.out.println("username =" + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
    */


