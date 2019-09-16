package easy.security.browser;

import easy.security.browser.authentication.EasyAuthenticationFailureHandler;
import easy.security.browser.authentication.EasyAuthenticationSuccessHandler;
import easy.security.browser.impl.SessionValidateCodeRepository;
import easy.security.browser.logout.EasyLogoutSuccessHandler;
import easy.security.browser.session.EasyExpireSessionStrategy;
import easy.security.browser.session.EasyInvalidSessionStrategy;
import easy.security.core.properties.EasySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/9 20:18
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new EasyInvalidSessionStrategy(easySecurityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new EasyExpireSessionStrategy(easySecurityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new EasyLogoutSuccessHandler(easySecurityProperties.getBrowser().getSignOutUrl());
    }

    @Bean
    public EasyAuthenticationSuccessHandler easyAuthenticationSuccessHandler(){
        return new EasyAuthenticationSuccessHandler(easySecurityProperties);
    }

    @Bean
    public EasyAuthenticationFailureHandler easyAuthenticationFailureHandler(){
        return new EasyAuthenticationFailureHandler(easySecurityProperties);
    }

    @Bean
    public SessionValidateCodeRepository sessionValidateCodeRepository(){
        return new SessionValidateCodeRepository();
    }
}
