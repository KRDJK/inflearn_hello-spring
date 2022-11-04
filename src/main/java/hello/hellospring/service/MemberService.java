package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) { // 의존성 주입 D.I
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원은 안되게 해야 한다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.orElseGet()  : 있으면 그냥 꺼내고 없으면 이걸로 꺼내라고 세팅해줄 수 있다고 함.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        // 리팩토링 버전
//        memberRepository.findByName(member.getName())
//              .isPresent(m -> {
//                      throw  new IllegalArgumentException("이미 존재하는 회원입니다.");
//              });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    * id 값으로 개별 회원 조회
    * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
