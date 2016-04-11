package cn.itcast.web.filter;

import cn.itcast.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//index.jsp   dao   dao    
		//1.jpg
		try{
			//拦截请求,获取链接Connection,开启事务,并把链接绑定到当前线程,延迟到Dao开启
			//JdbcUtils.StartTransaction();   //JdbcUtils.getConnection();
			
			chain.doFilter(request, response);  //目标资源执行 dao1   conn =  JdbcUtils.getConnection();   dao2   JdbcUtils.getConnection()
			
			//获取当前线程Connection上绑定的事务,提交事务,并关闭链接,释放链接Connection和当前线程的绑定
			JdbcUtils.commitTransaction();
		}finally{
			JdbcUtils.closeConn();
		}
	
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
