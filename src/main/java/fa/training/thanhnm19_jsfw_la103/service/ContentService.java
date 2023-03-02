package fa.training.thanhnm19_jsfw_la103.service;

import fa.training.thanhnm19_jsfw_la103.model.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContentService {

    boolean create(Content content);

    boolean update(Content content);

    boolean delete(Long id);
    Optional<Content> finById(Long id);

    Page<Content> getAll(Pageable pageable);

    Page<Content> searchByTitleOrBrief(String title, String brief, Pageable pageable);

}
