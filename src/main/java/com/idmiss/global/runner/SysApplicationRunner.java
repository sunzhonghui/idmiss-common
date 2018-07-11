package com.idmiss.global.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**  

* <p>Title: 初始化容器</p>  

* <p>Description: 容器加载完成后执行</p>  

* @author szh  

* @date 2018年4月24日  

*/  
@Component
public class SysApplicationRunner implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
	}

}
