package com.idmiss.common.order.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.idmiss.common.order.service.OrderGenerateService;
import com.idmiss.global.abs.AbstractBase;
/**  

* <p>Title: OrderGenerateServiceImpl</p>  

* <p>Description: 生成订单号，效率 2500+/s 1000W+/h 订单的生成 </p>  

* @author szh  

* @date 2018年6月20日  

*/  
@Component
public class OrderGenerateServiceImpl extends AbstractBase implements OrderGenerateService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private static Integer orderNoInitSize=10000;//order生成的速度/可按照生产环境调节
	private static Integer orderNoCreateLength=5000;//需要重新生成的阈值/可按照生产环境调节
	private static String orderLength="6";//订单顺序号的位数 ，默认6位支持每天生成999999个订单号
	
	
/**  
	
	 * <p>Title: getOrder</p>  
	
	 * <p>Description: 生产订单号方法</p>  
	
	 * @param type
	 * @return  
	
	 */
	@Override
	public String getOrder(String type) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dayDf=df.format(new Date());
		String orderKey=type.toString().concat("_").concat(dayDf);
		if(getRedisSize(orderKey)<=orderNoCreateLength) {
			this.generateInit(orderKey,type,dayDf);
		}

		return getOrderNo(orderKey);
	}
	
	//初始化
	synchronized private void generateInit(String orderKey,String type,String dayDf) {
		
		List<String> orders=new ArrayList<>();
		Long max=this.generate(orderKey.concat("_max"), 1, getTodayEndTime());
		for(long i=max;i<max+orderNoInitSize;i++) {
			orders.add(format(i,type.toString(),dayDf,orderLength));
		}
		redisTemplate.opsForList().leftPushAll(orderKey, orders);
		redisTemplate.expireAt(orderKey, getTodayEndTime());
		this.generate(orderKey.concat("_max"), orderNoInitSize-1, getTodayEndTime());
	}

	@SuppressWarnings("unused")
	private String format(Long id,String prefix,String date,String minLength){ 
	  StringBuffer sb = new StringBuffer();
	  sb.append(prefix);
	  sb.append(date);  
	  return sb.toString().concat(String.format("%1$0".concat(orderLength).concat("d"), id));
	 }
	
	//通过key获取自增并设定过期时间
	  public  Long generate(String key,Date expireTime) {  
	  RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());  
	  counter.expireAt(expireTime); 
	  return counter.incrementAndGet();  
	 }
	//通过key获取增加的值 并设定过期时间
	 public long generate(String key,int increment,Date expireTime) {
	        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());  
	        counter.expireAt(expireTime);  
	        return counter.addAndGet(increment);                
	 } 
	 
	 // 获取当天过期时间  
	 
	private Date getTodayEndTime() {    
	        Calendar todayEnd = Calendar.getInstance();    
	        todayEnd.set(Calendar.HOUR_OF_DAY, 23);    
	        todayEnd.set(Calendar.MINUTE, 59);    
	        todayEnd.set(Calendar.SECOND, 59);    
	        todayEnd.set(Calendar.MILLISECOND, 999);    
	        return todayEnd.getTime();    
	} 
	
	// 获取redis list size

	private Long getRedisSize(String key) {
		return redisTemplate.opsForList().size(key);
	}
	
	//获取订单号
	private String getOrderNo(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}
	
}
