package easy.security.core.authentication;

import easy.security.core.properties.EasySecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 14:28
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private AuthenticationSuccessHandler easyAuthenticationSuccessHandler;

    @Autowired(required = false)
    private AuthenticationFailureHandler easyAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(EasySecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                .loginProcessingUrl(EasySecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(easyAuthenticationSuccessHandler)
                .failureHandler(easyAuthenticationFailureHandler);
    }
}
