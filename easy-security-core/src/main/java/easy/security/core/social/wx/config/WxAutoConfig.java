package easy.security.core.social.wx.config;

import easy.security.core.social.EasyConnectView;
import easy.security.core.social.support.SocialAutoConfigurerAdapter;
import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.properties.WxProperties;
import easy.security.core.social.wx.connect.WxConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * 微信登录配置
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/7 17:36
 */
@ConditionalOnProperty(prefix = "security.social.wx", name = "app-id")
@Configuration
public class WxAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WxProperties wxConfig = easySecurityProperties.getSocial().getWx();
        return new WxConnectionFactory(wxConfig.getProviderId(), wxConfig.getAppId(), wxConfig.getAppSecret());
    }

    @Bean({"connect/wxConnected", "connect/wxConnect"})
    @ConditionalOnMissingBean(name = "wxConnectedView")
    public View wxConnectedView() {
        return new EasyConnectView();
    }
}
