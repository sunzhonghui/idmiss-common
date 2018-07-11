package com.idmiss.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.idmiss.common.order.controller.OrderGenerateController;
import com.idmiss.common.order.service.OrderGenerateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

	@Autowired
	private OrderGenerateService orderGenerateService;
	@Test
	public void contextLoads() throws InterruptedException {
		
		long startTime=System.currentTimeMillis(); 
		ExecutorService executor=Executors.newFixedThreadPool(30);
		for(int i=0;i<100000;i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(orderGenerateService.getOrder("PA"));
				}
			});
		}
		while(true) {
			int threadCount = ((ThreadPoolExecutor)executor).getActiveCount();
			if(threadCount>=30) {
				Thread.sleep(10);
				continue;
			}
			if(threadCount<=0) {
				break;
			}
		}
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
	}

}
