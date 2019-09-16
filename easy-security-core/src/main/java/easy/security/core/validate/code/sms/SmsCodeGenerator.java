package easy.security.core.validate.code.sms;

import easy.security.core.validate.code.ValidateCodeGenerator;
import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.validate.code.ValidateCode;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 19:16
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    private EasySecurityProperties easySecurityProperties;

    public SmsCodeGenerator(EasySecurityProperties easySecurityProperties) {
        this.easySecurityProperties = easySecurityProperties;
    }

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(easySecurityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, easySecurityProperties.getCode().getSms().getExpireIn());
    }

}
