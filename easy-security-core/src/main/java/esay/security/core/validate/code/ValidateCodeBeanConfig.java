package esay.security.core.validate.code;

import esay.security.core.properties.EasySecurityProperties;
import esay.security.core.validate.code.image.ImageCodeGenerator;
import esay.security.core.validate.code.sms.DefaultSmsCodeSender;
import esay.security.core.validate.code.sms.SmsCodeGenerator;
import esay.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 19:23
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        return new ImageCodeGenerator(easySecurityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator() {
        return new SmsCodeGenerator(easySecurityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
