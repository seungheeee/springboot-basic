package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//ctrl + shift + T test 코드 생성 단축키
//서비스단은 비지니스 로직에 가까운 이름으로 메서드명을 지음

//service 어노테이션을 붙여주면 spring이 알 수 없는 단순 자바 코드였던 것이 스프링이 실행될 때 컨테이너에 등록이 됨
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //new로 직접 생성하지않고 외부에서 넣어주도록 변경
    private final MemberRepository memberRepository;
    //private MemberRepository memberRepository;

/*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
*/
    //DI 직접 생성하지않고 외부에서 넣어주는 의존성 주입
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //참고 result.orElseGet() 값이 있으면 꺼내고 없으면 메소드를 실행하도록 이런 방식으로 get 해옴
        //같은 이름이 있는 중복 확인 X (ctrl+alt+v 변수 추출 단축키)
        //ctrl + shift + alt + T 리팩토링 단축키
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
