package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)     //조회에서 성능 최적화
//@AllArgsConstructor     //모든 필드 생성자 생성
@RequiredArgsConstructor    //final 필드만 생성자 생성
public class MemberService {

    // 필드에 @Autowired 거는 아래와 같은 방법은 많이 쓰지만 테스트하기 힘들다는 단점이 있음
    //    @Autowired
    //    private MemberRepository memberRepository;

    // 테스트 가능하게 아래와 같이 생성자로 주입하는 방식을 씀.
    private final MemberRepository memberRepository;    //컴파일 단계에서 값이 비어있는지 검증 가능하므로 final 추천
    //@Autowired    //필드가 하나일 경우 생략이 가능하다, @RequiredArgsConstructor가 있으므로 주석처리
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}

    /**
     * 회원 가입
     */
    @Transactional      //기본으로 readOnly = false 임
    public Long join(Member member){
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)     //조회에서 성능 최적화
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){   //단건 조회
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);

    }
}
