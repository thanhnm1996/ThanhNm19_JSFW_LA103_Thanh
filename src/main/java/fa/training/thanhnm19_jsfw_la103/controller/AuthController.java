package fa.training.thanhnm19_jsfw_la103.controller;

import fa.training.thanhnm19_jsfw_la103.model.dto.MemberLoginDto;
import fa.training.thanhnm19_jsfw_la103.model.dto.MemberRegisterDto;
import fa.training.thanhnm19_jsfw_la103.model.entity.Member;
import fa.training.thanhnm19_jsfw_la103.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthController {

    PasswordEncoder passwordEncoder;
    MemberService memberService;

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("memberLoginDto", new MemberLoginDto());
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model){
        model.addAttribute("memberRegisterDto", new MemberRegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerMember(@Valid MemberRegisterDto memberRegisterDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "auth/register";
        }
        if (!memberRegisterDto.getPassword().equals(memberRegisterDto.getRePassword())) {
            bindingResult.rejectValue("password","error.nomatch.pass");
        }
        Optional<Member> existingMember = memberService.findByEmail(memberRegisterDto.getEmail());
        if (existingMember.isPresent()) {
            bindingResult.rejectValue("email","error.exists.email");
        }
        if (bindingResult.hasErrors()){
            return "auth/register";
        }
        Member member = new Member();
        memberRegisterDto.setPassword(passwordEncoder.encode(memberRegisterDto.getPassword()));
        BeanUtils.copyProperties(memberRegisterDto, member);
        memberService.create(member);
        redirectAttributes.addFlashAttribute("successMessage", "Register Success");
        return "redirect:/login";
    }
}
