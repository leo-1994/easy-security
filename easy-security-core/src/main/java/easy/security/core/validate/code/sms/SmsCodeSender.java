package easy.security.core.validate.code.sms;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:09
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void send(String mobile, String code);
}
