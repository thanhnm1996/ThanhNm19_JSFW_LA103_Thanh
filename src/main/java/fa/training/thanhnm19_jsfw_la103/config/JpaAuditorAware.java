package fa.training.thanhnm19_jsfw_la103.config;

import fa.training.thanhnm19_jsfw_la103.security.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return SecurityUtil.getCurrentUserLogin();
    }
}
