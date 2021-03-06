package easy.security.core.social.support;

import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 17:34
 */
public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected abstract ConnectionFactory<?> createConnectionFactory();
}
