package fa.training.thanhnm19_jsfw_la103.controller;

import fa.training.thanhnm19_jsfw_la103.config.JpaAuditorAware;
import fa.training.thanhnm19_jsfw_la103.controller.common.AppConstant;
import fa.training.thanhnm19_jsfw_la103.model.dto.ContentDto;
import fa.training.thanhnm19_jsfw_la103.model.entity.Content;
import fa.training.thanhnm19_jsfw_la103.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;
    private final JpaAuditorAware jpaAuditorAware;

    @GetMapping
    public String showListContentView(@RequestParam("size") Optional<Integer> sizeOpt,
                                      @RequestParam("page") Optional<Integer> pageOpt,
                                      @RequestParam(name = "q", required = false) String query,
                                      Model model) {
        int page = pageOpt.orElse(AppConstant.DEFAULT_PAGE);
        int size = sizeOpt.orElse(AppConstant.DEFAULT_PAGE_SIZE);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Content> contentPage;
        if (query == null){
            contentPage = contentService.getAll(pageRequest);
        }else {
            contentPage = contentService.searchByTitleOrBrief(query, query, pageRequest);
        }
        contentPage.forEach(System.out::println);
        model.addAttribute("contentPage", contentPage);
        return "content/view-content";
    }

    @GetMapping("/add")
    public String showContentForm(Model model) {
        ContentDto contentDto = new ContentDto();
        model.addAttribute("contentDto", contentDto);
        return "content/form-content";
    }

    @PostMapping("/add")
    public String addContentForm(@Valid ContentDto contentDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "content/form-content";
        }
        Content content = new Content();
        BeanUtils.copyProperties(contentDto, content);

        contentService.create(content);
        redirectAttributes.addFlashAttribute("successMessage", "Add Content Success");
        return "redirect:/contents";
    }

    @GetMapping("/update/{id}")
    public String showContentUpdate(@PathVariable Long id, Model model) {
        Optional<Content> content = contentService.finById(id);
        if (content.isEmpty()) {
            throw new IllegalArgumentException("Can not found entity with id: " + id);
        }
        ContentDto contentDto = new ContentDto();
        BeanUtils.copyProperties(content.get(), contentDto);
        model.addAttribute("contentDto", contentDto);
        return "content/form-content";
    }

    @PostMapping("/update/{id}")
    public String updateContent(ContentDto contentDto, RedirectAttributes redirectAttributes, Model model) {
        //Từ id truyền vào từ contentDto, Lấy ra được content
        Content content = contentService.finById(contentDto.getId()).get();
        //Từ content thông qua authorId lấy ra được email của memberAuthor
        String email = content.getAuthorId().getEmail();
        //Kiểm tra email Author với current email
        if (email.equals(jpaAuditorAware.getCurrentAuditor().get())) {
            BeanUtils.copyProperties(contentDto, content);
            contentService.update(content);
            redirectAttributes.addFlashAttribute("messageSuccess", "Update Success");
            return "redirect:/contents";
        }
        model.addAttribute("forbiddenError", "You do not have permission to update");
        return "content/form-content";
    }

    @GetMapping("delete/{id}")
    public String deleteContent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Content content = contentService.finById(id).get();
        String email = content.getAuthorId().getEmail();
        if (email.equals(jpaAuditorAware.getCurrentAuditor().get())) {
            contentService.delete(id);
            redirectAttributes.addFlashAttribute("messageSuccess", "Delete Success");
            return "redirect:/contents";
        }
        redirectAttributes.addFlashAttribute("forbiddenError", "You do not have permission to delete");
        return "redirect:/contents";

    }


}
