package com.udemy.microservices.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Component
public class PostElapsedTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreElapsedTimeFilter.class);

    // Three types of filter: pre - post - route. Should return one of them
    @Override
    public String filterType() {
        return POST_TYPE;
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

        log.info("Running post filter");

        Long initTime = (Long) request.getAttribute("initTime");
        Long currentTime = System.currentTimeMillis();
        Long elapsedTime = currentTime - initTime;

        log.info(String.format("Elapsed time in seconds: %s",elapsedTime/1000));
        log.info(String.format("Elapsed time in milliseconds: %s",elapsedTime));

        return null;
    }

}
