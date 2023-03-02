package fa.training.thanhnm19_jsfw_la103.service.impl;

import fa.training.thanhnm19_jsfw_la103.model.entity.Member;
import fa.training.thanhnm19_jsfw_la103.repository.MemberRepository;
import fa.training.thanhnm19_jsfw_la103.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void create(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
