package com.gymnasticbodies.com.currencyconversionhistoryservice;

import brave.sampler.Sampler;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
@EnableFeignClients("com.gymnasticbodies.com.currencyconversionhistoryservice")
@EnableDiscoveryClient
//@EnableSwagger2
public class CurrencyConversionHistoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionHistoryServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

	/*@Bean
	public Docket api() throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader("pom.xml"));
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gymnasticbodies.com.currencyconversionhistoryservice"))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfo("Transfer Service Api Documentation", "Documentation automatically generated", model.getParent().getVersion(), null, new Contact("vidya nirmal", "", "v.nirmal@gmnasticbodies.com"), null, null));
	}*/

}
