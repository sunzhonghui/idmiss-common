package com.idmiss.global.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VstarsConfiguration {

	@Autowired  
	 private RestTemplateBuilder builder; 

   /**   
    * <p>Description: 文件上传配置</p>  
    * @return  
    */ 
   @Bean  
   public MultipartConfigElement multipartConfigElement() {  
       MultipartConfigFactory factory = new MultipartConfigFactory();  
       //单个文件最大  
       factory.setMaxFileSize("102400KB"); //KB,MB  
       /// 设置总上传数据总大小  
       factory.setMaxRequestSize("1024000KB");  
       return factory.createMultipartConfig();  
   }
   
   @Bean  
   public RestTemplate restTemplate() {  
       return builder.build();  
   }  
    
}
