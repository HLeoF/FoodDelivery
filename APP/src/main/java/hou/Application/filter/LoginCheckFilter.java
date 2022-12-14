package hou.Application.filter;

import com.alibaba.fastjson.JSON;
import hou.Application.common.BaseContext;
import hou.Application.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = {"/*"})
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符写法
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        //1.获取本次请求的URL
        String requestURL = request.getRequestURI();
        log.info("拦截到请求 {}", requestURL);

        //不需要处理请求的路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls,requestURL);

        //3.如果不需要处理，直接放行
        if(check){
            log.info("本次请求{}不需要处理", requestURL);
            filterChain.doFilter(request, response);
            return;
        }

        //4.判断登录状态，如果已经登录，直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id: {}",request.getSession().getAttribute("employee"));

            //Threadlocal get Id
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        //5. 未登录则显示未登录结果，通过输出流向客户端响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));


    }

    /**
     * 路径匹配，检查url是否可以放行
     * @param urls
     * @param requestURL
     * @return
     */
    public  boolean check(String[] urls, String requestURL){
        for(String url: urls){
            boolean match = PATH_MATCHER.match(url,requestURL);
            if(match){
                return true;
            }
        }
        return false;
    }
}
