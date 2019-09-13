package esay.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/10 19:54
 */
public interface SocialAuthenticationFilterPostProcessor {
   void process(SocialAuthenticationFilter filter);
}
