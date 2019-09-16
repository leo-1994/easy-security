package easy.security.core.authorize;

import easy.security.core.properties.EasySecurityConstants;
import easy.security.core.properties.EasySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/11 19:34
 */
@Order(Integer.MIN_VALUE)
@Component
public class EasyAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                .antMatchers(EasySecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                        "/error",
                        EasySecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        easySecurityProperties.getBrowser().getLoginPage(),
                        EasySecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        easySecurityProperties.getBrowser().getSignUpUrl(),
                        "/user/register",
                        easySecurityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                        easySecurityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                        easySecurityProperties.getBrowser().getSignOutUrl())
                .permitAll();
    }
}
