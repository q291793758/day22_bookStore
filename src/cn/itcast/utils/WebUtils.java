package cn.itcast.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	
	public static <T> T request2Bean(HttpServletRequest request,Class<T> beanClass){
		try{
			
			T bean = beanClass.newInstance();
			Map map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}	
