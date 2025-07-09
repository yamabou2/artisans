package jp.spring.artisans;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("artisansSession")
public class ArtisansController {
	
//	依存関係の注入
	@Autowired
	private AppConfig appConfig;
	
////	ホーム画面
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
//	画像アップロード
	@PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, Model model) {
        // ファイルを選択せずにフォームを送信したかの確認
        if (file.isEmpty()) {
            model.addAttribute("error", "ファイルを指定してください");
            return "index";
        }
    
        // アップロードされたファイルを指定の名前で保存
        File dest = new File(appConfig.getImageDir(), "picture.jpg");
        try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        return "index";
    }

//	フォームデータのやりとり
	@PostMapping("/formresult")
	public String showIndex(@Valid @ModelAttribute ArtisansForm form,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		}
	    return "formresult";
	}
	@GetMapping("/form")
	public String showForm(@ModelAttribute ArtisansForm form) {
		return "form";
	}
	
//	セッション
	@ModelAttribute("artisansSession")
	public ArtisansSession sessionMemo() {
		return new ArtisansSession();
	}
	
	@GetMapping("/session")
	public String session() {
		return "session";
	}
	
	@PostMapping("/add")
	public String add(ArtisansSession artisansSession,String memo) {
		artisansSession.getMemos().add(memo);
		return "redirect:/session";
	}
	
	@GetMapping("/clear")
	public String clear(SessionStatus sessions) {
		sessions.setComplete();
		return "redirect:/session";
	}
	
}
