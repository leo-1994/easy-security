package esay.security.core.social.qq.config;

import esay.security.core.properties.EasySecurityProperties;
import esay.security.core.properties.QQProperties;
import esay.security.core.social.EasyConnectView;
import esay.security.core.social.qq.connect.QQConnectionFactory;
import esay.security.core.social.support.SocialAutoConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * qq登录配置
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/6 17:29
 */
@ConditionalOnProperty(prefix = "security.social.qq", name = "app-id")
@Configuration
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = easySecurityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

    @Bean({"connect/qqConnected", "connect/qqConnect"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View qqConnectedView() {
        return new EasyConnectView();
    }
}
