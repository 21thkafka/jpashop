package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryOld {

    //@PersistenceContext       //스프링이 @PersistenceContext ->@Autowired로 지원, ->@RequiredArgsConstructor 사용가능
    private final EntityManager em;
//@RequiredArgsConstructor 사용하므로 주석
//    public MemberRepository(EntityManager em){
//        this.em = em;
//    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id){     // 단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){      // 목록 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
