package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // [opt + n] 눌러서 constructor 생성 - Repository를 외부에서 넣어주도록
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 1. 회원가입
    public Long join(Member member) {
        // 같은 이름이면 회원가입 불가
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    // 1-1. 중복 체크
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // 값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 2. 전체 회원 조회 기능
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
