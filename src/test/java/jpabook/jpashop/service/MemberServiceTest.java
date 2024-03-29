package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em; //@Rollback(false) 없이 테스트 결과 기능 보기 위해 필요
    @Test
    @Rollback(false) //기본적으로 @Transactional은 롤백시킴, 테스트 결과를 보기 위해 false처리
    public void joinTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush(); 강제로 db에 쿼리를 날림
        assertEquals(member, memberRepository.findById(savedId).get());
    }

    @Test(expected = IllegalStateException.class) // 예외발생 try catch (IllegalArgumentException e) 역할함
    public void sameMemberTest() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2);    //똑같은 이름이므로 예외가 발생해야한다.

        //then
        fail("예외가 발생해야 한다.");
    }
}