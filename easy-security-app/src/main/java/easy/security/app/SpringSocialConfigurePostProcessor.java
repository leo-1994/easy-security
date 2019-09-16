package easy.security.app;

import easy.security.core.social.EasySpringSocialConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/11 09:32
 */
@Component
public class SpringSocialConfigurePostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private static final String EASY_SOCIAL_SECURITY_CONFIG_BEAN_NAME="easySocialSecurityConfig";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, EASY_SOCIAL_SECURITY_CONFIG_BEAN_NAME)) {
            EasySpringSocialConfigurer configurer = (EasySpringSocialConfigurer) bean;
            configurer.signupUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
