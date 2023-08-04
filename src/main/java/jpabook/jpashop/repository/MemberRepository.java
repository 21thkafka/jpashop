package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //spring data가 이름을 보고 (find By Name 형식으로 써야함)알아서 아래처럼 쿼리를 짜줌
    //select m from Member m where m.name = ?
    List<Member> findByName(String name);
}
