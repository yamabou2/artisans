package jp.spring.artisans;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ArtisansForm {

	@NotBlank(message = "名前を入力してください")
	private String name;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しいメールアドレスを入力してください")
	private String mail;
	
	@NotNull(message = "年齢を入力してください")
	@Min(value = 18,message = "18才以下は保護者の許可が必要です")
	private Integer age;
	
	@NotBlank(message = "内容を入力してください")
	private String contents;

}
