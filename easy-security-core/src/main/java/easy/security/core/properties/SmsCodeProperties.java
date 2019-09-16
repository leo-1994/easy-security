package easy.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:23
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url;
}
