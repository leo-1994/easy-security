package esay.security.core.social;

import esay.security.core.properties.EasySecurityProperties;
import esay.security.core.social.support.EasyJdbcConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 16:57
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    private JdbcUsersConnectionRepository repository;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        if (repository == null) {
            repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
            repository.setTablePrefix("");
            if (connectionSignUp != null) {
                repository.setConnectionSignUp(connectionSignUp);
            }
        }
        return repository;
    }

    private EasyJdbcConnectionRepository getCustomJdbcConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new EasyJdbcConnectionRepository(getUserIdSource(), new JdbcTemplate(dataSource), connectionFactoryLocator, Encryptors.noOpText(), "");
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Bean
    public SpringSocialConfigurer customSocialSecurityConfig() {
        EasySpringSocialConfigurer configurer = new EasySpringSocialConfigurer(easySecurityProperties.getSocial().getFilterProcessesUrl());
        configurer.signupUrl(easySecurityProperties.getBrowser().getSignUpUrl());
        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return configurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

    @Bean
    @ConditionalOnMissingBean({ConnectController.class})
    public ConnectController connectController(ConnectionFactoryLocator factoryLocator) {
        return new ConnectController(factoryLocator, getCustomJdbcConnectionRepository(factoryLocator));
    }
}
