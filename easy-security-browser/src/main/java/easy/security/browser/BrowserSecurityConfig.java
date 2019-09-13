package easy.security.browser;

import esay.security.core.ValidateCodeSecurityConfig;
import esay.security.core.authentication.AbstractChannelSecurityConfig;
import esay.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import esay.security.core.authorize.AuthorizeConfigManage;
import esay.security.core.properties.EasySecurityProperties;
import esay.security.core.support.EasyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/2 17:53
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired(required = false)
    private EasySecurityProperties easySecurityProperties;

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private EasyUserDetailsService easyUserDetailsService;

    @Autowired(required = false)
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired(required = false)
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired(required = false)
    private SpringSocialConfigurer customSocialSecurityConfig;

    @Autowired(required = false)
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired(required = false)
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManage authorizeConfigManage;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(customSocialSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(easySecurityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(easyUserDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .invalidSessionUrl(easySecurityProperties.getBrowser().getSession().getSessionInvalidUrl())
                .maximumSessions(easySecurityProperties.getBrowser().getSession().getMaximumSessions())
                // 当session数量达到限制时，阻止后续登陆
                .maxSessionsPreventsLogin(easySecurityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                // 如果要记录被挤掉的session，可以实现SessionInformationExpiredStrategy接口
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();
        authorizeConfigManage.config(http.authorizeRequests());
    }
}
