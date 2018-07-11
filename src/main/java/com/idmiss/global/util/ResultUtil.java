package com.idmiss.global.util;

import com.idmiss.global.model.Result;

/**  

* <p>Title: ResultUtil</p>  

* <p>Description: 通用返回对象工具类</p>  

* @author szh  

* @date 2018年4月9日  

*/ 
public class ResultUtil {

	/**   
	 * <p>Description:成功的返回，可传描述信息 </p>  
	 * 
	 * @param object 返回的数据对象 
	 * @param msg 描述信息
	 * @return  
	 */ 
	public static Result success(Object object,String msg){
        Result result = new Result();
        result.setCode(100);
        result.setMsg(msg);
        result.setData(object);
        return  result;
    }
	/**   
	 * <p>Description:成功的返回 </p>  
	 * 
	 * @param object 返回的数据对象 
	 * @return  
	 */ 
	public static Result success(Object object){
        Result result = new Result();
        result.setCode(100);
        result.setMsg("success");
        result.setData(object);
        return  result;
    }
    /**   
     * <p>Description: 成功返回，没有数据对象</p>  
     * @return  
     */ 
    public static Result successNoData(){
        return  success(null);
    }
    /**   
     * <p>Description: 失败返回，定义失败码，跟描述信息，没有返回对象</p>  
     * @param code 失败码
     * @param msg 描述信息
     * @return  
     */ 
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
    /**   
     * <p>Description: 失败返回，定义失败码，跟描述信息，有返回对象</p>  
     * @param code 失败码
     * @param msg 描述信息
     * @param data 失败要返回的对象
     * @return  
     */ 
    public static Result error(Integer code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return  result;
    }
}
