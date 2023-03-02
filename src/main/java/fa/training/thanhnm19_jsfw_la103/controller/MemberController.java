package fa.training.thanhnm19_jsfw_la103.controller;

import fa.training.thanhnm19_jsfw_la103.config.JpaAuditorAware;
import fa.training.thanhnm19_jsfw_la103.model.dto.MemberDto;
import fa.training.thanhnm19_jsfw_la103.model.entity.Member;
import fa.training.thanhnm19_jsfw_la103.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;
    private JpaAuditorAware jpaAuditorAware;


    @GetMapping
    public String showMember(Model model) {
        //currentEmailLogin
        Optional<String> emailMember = jpaAuditorAware.getCurrentAuditor();
        //Get member thông qua currentEmail
        Optional<Member> member = memberService.findByEmail(emailMember.get());

        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member.get(), memberDto);
        model.addAttribute("memberDto", memberDto);
        return "member/edit-profile";
    }


    @PostMapping("/update")
    public String updateMember(@Valid MemberDto memberDto,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            return "member/edit-profile";
        }
        //currentEmailLogin
        Optional<String> emailMember = jpaAuditorAware.getCurrentAuditor();
        //Get member thông qua currentEmail
        Optional<Member> member = memberService.findByEmail(emailMember.get());
        //Coppy giá trị từ memberDto -> member
        BeanUtils.copyProperties(memberDto, member.get());
        member.get().setEmail(emailMember.get());
        //Update member
        memberService.create(member.get());
        redirectAttributes.addFlashAttribute("messageSuccess", "Update profile success");
        return "redirect:/contents";
    }

}
