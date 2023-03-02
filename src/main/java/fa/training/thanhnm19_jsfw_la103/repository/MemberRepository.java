package fa.training.thanhnm19_jsfw_la103.repository;

import fa.training.thanhnm19_jsfw_la103.model.entity.Content;
import fa.training.thanhnm19_jsfw_la103.model.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

}
