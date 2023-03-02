package fa.training.thanhnm19_jsfw_la103.service;

import fa.training.thanhnm19_jsfw_la103.model.entity.Member;

import java.util.Optional;

public interface MemberService {

    void create(Member member);

    Optional<Member> findByEmail(String email);
}
