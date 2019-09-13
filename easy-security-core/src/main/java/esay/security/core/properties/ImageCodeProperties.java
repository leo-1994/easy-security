package esay.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 18:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;
}
