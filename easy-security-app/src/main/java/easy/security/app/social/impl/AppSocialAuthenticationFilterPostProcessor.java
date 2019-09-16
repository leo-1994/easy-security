package easy.security.app.social.impl;

import easy.security.app.authentication.EasyAuthenticationSuccessHandler;
import easy.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/10 19:59
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
    @Autowired
    private EasyAuthenticationSuccessHandler easyAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(easyAuthenticationSuccessHandler);

    }
}
