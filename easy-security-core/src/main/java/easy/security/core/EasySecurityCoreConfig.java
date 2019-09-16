package easy.security.core;

import easy.security.core.authorize.AuthorizeConfigProvider;
import easy.security.core.authorize.EasyAuthorizeConfigManage;
import easy.security.core.authorize.EasyAuthorizeConfigProvider;
import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.social.EasyConnectionStatusView;
import easy.security.core.validate.code.image.ImageCodeProcessor;
import easy.security.core.validate.code.sms.SmsCodeProcessor;
import easy.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 15:49
 */
@Configuration
@EnableConfigurationProperties(EasySecurityProperties.class)
public class EasySecurityCoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Order(Integer.MIN_VALUE)
    @Bean
    public EasyAuthorizeConfigProvider easyAuthorizeConfigProvider() {
        return new EasyAuthorizeConfigProvider(easySecurityProperties);
    }

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviderList;

    @Bean
    public EasyAuthorizeConfigManage easyAuthorizeConfigManage() {
        return new EasyAuthorizeConfigManage(authorizeConfigProviderList);
    }

    @Bean(name = "connect/status")
    public EasyConnectionStatusView easyConnectionStatusView() {
        return new EasyConnectionStatusView();
    }

}
