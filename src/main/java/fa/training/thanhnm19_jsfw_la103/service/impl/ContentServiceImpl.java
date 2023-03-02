package fa.training.thanhnm19_jsfw_la103.service.impl;

import fa.training.thanhnm19_jsfw_la103.config.JpaAuditorAware;
import fa.training.thanhnm19_jsfw_la103.model.entity.Content;
import fa.training.thanhnm19_jsfw_la103.model.entity.Member;
import fa.training.thanhnm19_jsfw_la103.repository.ContentRepository;
import fa.training.thanhnm19_jsfw_la103.repository.MemberRepository;
import fa.training.thanhnm19_jsfw_la103.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final JpaAuditorAware jpaAuditorAware;
    private final MemberRepository memberRepository;

    @Override
    public boolean create(Content content) {
        try {
            //map id(member) vs authorID(content)
            String currentEmail = jpaAuditorAware.getCurrentAuditor().get();
            Member memberAuthor = memberRepository.findByEmail(currentEmail).get();
            content.setAuthorId(memberAuthor);
            Content newContent = contentRepository.save(content);
            return newContent.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Content content) {
        try {
            contentRepository.save(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            contentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Optional<Content> finById(Long id) {
        return contentRepository.findById(id);
    }

    @Override
    public Page<Content> getAll(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    @Override
    public Page<Content> searchByTitleOrBrief(String title, String brief, Pageable pageable) {
        return contentRepository.findByTitleContainingIgnoreCaseOrBriefContainingIgnoreCase(title, brief, pageable);
    }
}
