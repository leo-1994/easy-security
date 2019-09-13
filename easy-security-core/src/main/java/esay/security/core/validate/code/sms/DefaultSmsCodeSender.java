package esay.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:10
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("发送给手机:{} 验证码:{}", mobile, code);
    }
}
