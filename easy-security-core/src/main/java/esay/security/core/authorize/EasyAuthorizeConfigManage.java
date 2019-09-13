package esay.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/11 19:36
 */
@Component
public class EasyAuthorizeConfigManage implements AuthorizeConfigManage {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviderList;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviderList) {
            authorizeConfigProvider.config(config);
        }
    }
}
