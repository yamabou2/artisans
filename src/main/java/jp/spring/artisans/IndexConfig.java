package jp.spring.artisans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IndexConfig implements WebMvcConfigurer{
	@Autowired
	private AppConfig appConfig;

//	画像へのアクセス設定
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String imageDirUri = appConfig.getImageDir().toURI().toString();
        registry.addResourceHandler("/images/**").addResourceLocations(imageDirUri);
	}

}
