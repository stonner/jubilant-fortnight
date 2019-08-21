package zcq.myjpa.component.filters;

import zcq.myjpa.common.Constants;
import zcq.myjpa.component.BodyReaderHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/21
 */
@WebFilter(filterName = "bodyReaderFilter",urlPatterns = "/*")
public class BodyReaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest req = servletRequest;
        //附表单提交方式以及contentLength<0不作处理
        if (!Constants.MediaTypes.APPLICATION_FORM.equals(servletRequest.getContentType()) && servletRequest.getContentLength() > 0 ) {
            if(servletRequest instanceof HttpServletRequest) {
                req = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
            }
        }
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
