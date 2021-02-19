package com.udemy.microservices.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreElapsedTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreElapsedTimeFilter.class);

    // Three types of filter: pre - post - route. Should return one of them
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    // By default 0. This method returns execution order for this specific filter (this helps when there are numerous filters)
    @Override
    public int filterOrder() {
        return 1;
    }

    // This method can perform some type of validation to check if we should run this filter with the incoming request
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // Here should be the logic
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request routed to %s",request.getMethod(), request.getRequestURL().toString()));

        Long initTime = System.currentTimeMillis();
        request.setAttribute("initTime",initTime);
        return null;
    }
}
