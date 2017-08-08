package shop.leshare.weixin.mp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Title: shop.leshare</p>
 * <p/>
 * <p>
 * Description: Spring SwaggerConfig
 * </p>
 * <p/>
 *
 * @author Lynn
 *         CreateTime：4/12/17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket createRestApi(){
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("wx.shop.leshare"))
				.paths(PathSelectors.any())
				.build();
		
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("LeShare API")
				.description("LeShare 本地电商服务平台")
				.termsOfServiceUrl("http://leshare.shop")
				.version("1.0")
				.build();
	}
}