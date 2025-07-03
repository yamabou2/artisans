package jp.spring.artisans;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArtisansController {
	
////	ホーム画面
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
////	フォーム画面
//	@GetMapping("form")
//	public String form() {
//		return "form";
//	}
//	
//	フォームデータのやりとり
	@PostMapping("/formresult")
	public String showIndex(@ModelAttribute ArtisansForm form,Model model) {
		model.addAttribute("name",form.getName());
	    model.addAttribute("mail",form.getMail());
	    model.addAttribute("age",form.getAge());
	    model.addAttribute("contents",form.getContents());
	    return "formresult";
	}
	@GetMapping("/form")
	public String showForm(Model model) {
		model.addAttribute("artisansForm",new ArtisansForm());
		return "form";
	}
	
}
