package com.zerobase.cms.user.client.config.filter;

import com.zerobase.cms.user.client.service.seller.SellerService;
import com.zerobase.domain.common.UserVo;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/seller/*")
@RequiredArgsConstructor
public class SellerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final SellerService sellerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTO-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        sellerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                ()->new ServletException("Invalid access")
        );
        chain.doFilter(request,response);
    }
}
