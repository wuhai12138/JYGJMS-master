package com.summ.filter;

import com.alibaba.druid.util.StringUtils;
import com.summ.mapper.JAdminMapper;
import com.summ.model.JAdmin;
import com.summ.utils.HttpServletRequestWrapperUtil;
import com.summ.utils.JsonUtil;
import com.summ.utils.RequestUtil;
import com.summ.utils.StringUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by summ on 17/11/13.
 */
@Component
public class AccessFilter implements Filter {

    @Autowired
    private JAdminMapper jAdminMapper;

    private String encoding = null;

    private String excludedPage;
    private String excludedPageAliPayCallback;
    private String[] excludedPageArray;

    public void destroy() {
        encoding = null;
    }

    public AccessFilter() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest req = (HttpServletRequest) request;
//        ServletContext servletContext = req.getSession().getServletContext();
//        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        if (cxt != null && cxt.getBean("jAdminMapper") != null && jAdminMapper == null){
//            jAdminMapper = (JAdminMapper) cxt.getBean("jAdminMapper");
//        }

        //判断请求路径是否是登录请求
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {
            if (((HttpServletRequest) request).getServletPath().equals(page)) {
                isExcludedPage = true;
                break;
            }
        }
        if (isExcludedPage) {
            chain.doFilter(request, response);

        } else {
            ServletRequest requestWrapper = null;
            if (request instanceof HttpServletRequest) {
                requestWrapper = new HttpServletRequestWrapperUtil((HttpServletRequest) request);
            }
            String sign = RequestUtil.sign(requestWrapper);
            if (!sign.equals("")) {
                //验证请求参数加密的合法性
                httpResponse.getOutputStream().write(sign.getBytes("UTF-8"));
                return;
            }

            //判断用户id
            int id;
            if (request.getParameter("id") != null) {
                id = StringUtil.parseInt(request.getParameter("id"));
                JAdmin jAdmin = jAdminMapper.getAdminById(id);
                request.setAttribute("admin", jAdmin);
            } else {
                id = 0;
            }
            System.out.println("进入过滤器》》》》》》》》》");
            if (id == 0) {
                System.out.println("未登录");
                String outJson = "{\"code\":\"101\",\"msg\":\"not login in !\"}";
                httpResponse.getOutputStream().write(outJson.getBytes("UTF-8"));
                return;
            }

            if (requestWrapper == null) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        }


    }

    public void init(FilterConfig filterConfig) throws ServletException {
        //获取需要排除在外的url
        excludedPage = filterConfig.getInitParameter("excludedPages");
        excludedPageArray = excludedPage.split(",");


        this.encoding = filterConfig.getInitParameter("encoding");
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        jAdminMapper = ctx.getBean(JAdminMapper.class);

    }
}
