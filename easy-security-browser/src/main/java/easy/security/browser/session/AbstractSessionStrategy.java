package easy.security.browser.session;

import com.alibaba.fastjson.JSON;
import esay.security.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/9 20:11
 */
@Slf4j
public class AbstractSessionStrategy {

    /**
     * 跳转的url
     */
    private String destinationUrl;
    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (createNewSession) {
            request.getSession();
        }
        String sourceUrl = request.getRequestURI();
        String targetUrl;
        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            targetUrl = destinationUrl + ".html";
            log.info("session失效,跳转到" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            String message = "session已失效";
            if (isConcurrency()) {
                message = message + "，有可能是并发登录导致的";
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSON.toJSONString(new SimpleResponse(message)));
        }

    }

    /**
     * session失效是否是并发导致的
     *
     * @return boolean
     */
    protected boolean isConcurrency() {
        return false;
    }


    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
