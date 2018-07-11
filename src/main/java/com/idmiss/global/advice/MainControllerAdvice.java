package com.idmiss.global.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.idmiss.global.abs.AbstractBase;
import com.idmiss.global.model.Result;
import com.idmiss.global.util.ResultUtil;

/**  

* <p>Title: VstarsControllerAdvice</p>  

* <p>Description: 拦截异常并统一处理类</p>  

* @author szh  

* @date 2018年4月10日  

*/ 
@ControllerAdvice
public class MainControllerAdvice extends AbstractBase{
	
	/**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {}

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
    	logger.error("!! Request error:{}",ex);
    	
        return ResultUtil.error(500, "unknown mistake");
    }

}
