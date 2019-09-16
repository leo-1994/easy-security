package easy.security.browser;

import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.social.support.SocialUserInfo;
import easy.security.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 15:35
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证时，跳转到这里
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return SimpleResponse
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping("/authentication/require")
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest saveRequest = requestCache.getRequest(request, response);
        if (saveRequest != null) {
            String targetUrl = saveRequest.getRedirectUrl();
            log.info("引发跳转当请求是:{}", targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, easySecurityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问当前服务需要身份认证");
    }

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setAvatarUrl(connection.getImageUrl());
        return userInfo;
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @GetMapping("/session/invalid")
    public SimpleResponse sessionInvalid() {
        String message = "session失效";
        return new SimpleResponse(message);
    }
}
