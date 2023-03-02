package fa.training.thanhnm19_jsfw_la103.security;
import fa.training.thanhnm19_jsfw_la103.model.entity.Member;
import fa.training.thanhnm19_jsfw_la103.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        //FIXME Object
        Optional<Member> memberOptional = memberService.findByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("Can not fund employee with email: " + email);
        }
        return createUserDetails(memberOptional.get());
    }

    private User createUserDetails(Member member) {
        List<GrantedAuthority> roles = Collections.emptyList();
        //FIXME Object
        return new User(member.getEmail(), member.getPassword(), roles);
    }

}
