package easy.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.properties.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆成功处理
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/3 16:06
 */
@Slf4j
@Component
public class EasyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功");
        if (LoginType.JSON.equals(easySecurityProperties.getBrowser().getLoginType())) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSON.toJSONString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
