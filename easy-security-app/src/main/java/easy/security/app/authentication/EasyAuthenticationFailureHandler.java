package easy.security.app.authentication;

import com.alibaba.fastjson.JSON;
import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.properties.LoginType;
import easy.security.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 16:33
 */
@Slf4j
@Component
public class EasyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final EasySecurityProperties easySecurityProperties;

    public EasyAuthenticationFailureHandler(EasySecurityProperties easySecurityProperties) {
        this.easySecurityProperties = easySecurityProperties;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败");
        if (LoginType.JSON.equals(easySecurityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSON.toJSONString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
