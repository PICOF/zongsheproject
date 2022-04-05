package com.example.zongshe.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class Guard implements Filter {

    /**
     * Default constructor.
     */
    public Guard() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here

        // pass the request along the filter chain
        //记住操作前这个强制转换是因为 Servlet 中的类型都是 HttpServletRequest/HttpServletResponse
        HttpServletRequest req=(HttpServletRequest) request;
        //可以放行的地址清单
        String[] okurl={"/login","/js","/css","/images"};
        //用于判断的地址
        String URL=req.getRequestURI().toString();
        boolean flag=true;
        for(String tar:okurl) {
            if (URL.contains(tar)) {
                //任意一项匹配证明是允许访问的
                flag = false;
                break;
            }
        }
        if(flag) {
            //不再直接放行的清单中，就说明是需要登录的页面
            //获取session值
            HttpSession session = req.getSession();
            String user=(String)session.getAttribute("user");
            if(user==null) {
                req.getRequestDispatcher("login").forward(req, response);
                return;//便不会执行后面的语句
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
