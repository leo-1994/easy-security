package easy.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/11 19:36
 */
public class EasyAuthorizeConfigManage implements AuthorizeConfigManage {

    private final List<AuthorizeConfigProvider> authorizeConfigProviderList;

    public EasyAuthorizeConfigManage(List<AuthorizeConfigProvider> authorizeConfigProviderList) {
        this.authorizeConfigProviderList = authorizeConfigProviderList;
    }

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviderList) {
            authorizeConfigProvider.config(config);
        }
        config.anyRequest().authenticated();
    }
}
