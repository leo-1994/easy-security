package esay.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 18:36
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
