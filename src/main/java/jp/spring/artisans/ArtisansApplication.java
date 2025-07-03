package jp.spring.artisans;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArtisansApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtisansApplication.class, args);
	}
	
//	画像の依存関係作成
	@Bean
    AppConfig appConfig() {
        File imageDir = new File("images");
        imageDir = imageDir.getAbsoluteFile();

        // imagesフォルダがなかったら作成する
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
        AppConfig appConfig = new AppConfig();
        appConfig.setImageDir(imageDir);
        return appConfig;
    }

}
