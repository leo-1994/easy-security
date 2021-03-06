package easy.security.browser;

import easy.security.core.ValidateCodeSecurityConfig;
import easy.security.core.authentication.AbstractChannelSecurityConfig;
import easy.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import easy.security.core.authorize.EasyAuthorizeConfigManage;
import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.support.EasyUserDetailsService;
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

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EasyUserDetailsService easyUserDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer easySocialSecurityConfig;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private EasyAuthorizeConfigManage easyAuthorizeConfigManage;

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
                .apply(easySocialSecurityConfig)
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
        easyAuthorizeConfigManage.config(http.authorizeRequests());
    }
}
