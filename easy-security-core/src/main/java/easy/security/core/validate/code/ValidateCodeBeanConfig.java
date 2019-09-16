package easy.security.core.validate.code;

import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.validate.code.image.ImageCodeGenerator;
import easy.security.core.validate.code.image.ImageCodeProcessor;
import easy.security.core.validate.code.sms.DefaultSmsCodeSender;
import easy.security.core.validate.code.sms.SmsCodeGenerator;
import easy.security.core.validate.code.sms.SmsCodeProcessor;
import easy.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.util.Map;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 19:23
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

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

    @Bean
    public ImageCodeProcessor imageValidateCodeProcessor() {
        return new ImageCodeProcessor();
    }

    @Bean
    public SmsCodeProcessor smsValidateCodeProcessor() {
        return new SmsCodeProcessor(smsCodeSender);
    }

    @Bean
    public ValidateCodeFilter validateCodeFilter() {
        return new ValidateCodeFilter(authenticationFailureHandler, validateCodeProcessorHolder, easySecurityProperties);
    }

    @Bean
    public ValidateCodeProcessorHolder validateCodeProcessorHolder() {
        return new ValidateCodeProcessorHolder(validateCodeProcessors);
    }
}
