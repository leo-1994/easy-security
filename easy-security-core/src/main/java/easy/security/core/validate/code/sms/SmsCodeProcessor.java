package easy.security.core.validate.code.sms;

import easy.security.core.properties.EasySecurityConstants;
import easy.security.core.validate.code.ValidateCode;
import easy.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:50
 */
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    private final SmsCodeSender smsCodeSender;

    public SmsCodeProcessor(SmsCodeSender smsCodeSender) {
        this.smsCodeSender = smsCodeSender;
    }

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), EasySecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
