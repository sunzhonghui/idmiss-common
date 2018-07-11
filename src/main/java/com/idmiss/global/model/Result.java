package com.idmiss.global.model;

/**  

* <p>Title: Result</p>  

* <p>Description: 通用返回对象类</p>  

* @author szh  

* @date 2018年4月9日  

*/  
public class Result {

		/** 
			code 错误码
			100 为成功 否则为失败
			失败编码自定含义
		*/ 
		private Integer code;
		
		/** 
		  msg 返回描述
		*/ 
		private String msg;
		
		/** 
		  Data 返回的数据
		*/ 
		private Object Data;

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return Data;
		}

		public void setData(Object data) {
			Data = data;
		}

		@Override
		public String toString() {
			return "Result [code=" + code + ", msg=" + msg +  ", Data=" + Data + "]";
		}
		
		
		
}
