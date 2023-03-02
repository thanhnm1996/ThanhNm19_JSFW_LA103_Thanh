package fa.training.thanhnm19_jsfw_la103.repository;

import fa.training.thanhnm19_jsfw_la103.model.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content,Long> {

    Page<Content>findByTitleContainingIgnoreCaseOrBriefContainingIgnoreCase(String title, String brief, Pageable pageable);

}
