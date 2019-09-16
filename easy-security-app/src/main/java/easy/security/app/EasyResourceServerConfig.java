package easy.security.app;

import easy.security.app.authentication.EasyAuthenticationFailureHandler;
import easy.security.app.authentication.EasyAuthenticationSuccessHandler;
import easy.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import easy.security.core.ValidateCodeSecurityConfig;
import easy.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import easy.security.core.authorize.AuthorizeConfigManage;
import easy.security.core.properties.EasySecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/10 15:49
 */
@Configuration
@EnableResourceServer
public class EasyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired(required = false)
    private EasyAuthenticationFailureHandler easyAuthenticationFailureHandler;

    @Autowired(required = false)
    private EasyAuthenticationSuccessHandler easyAuthenticationSuccessHandler;

    @Autowired(required = false)
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired(required = false)
    private SpringSocialConfigurer easySocialSecurityConfig;

    @Autowired(required = false)
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired(required = false)
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired(required = false)
    private AuthorizeConfigManage authorizeConfigManage;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(EasySecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                .loginProcessingUrl(EasySecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(easyAuthenticationSuccessHandler)
                .failureHandler(easyAuthenticationFailureHandler);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(easySocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();
        authorizeConfigManage.config(http.authorizeRequests());
    }

}
