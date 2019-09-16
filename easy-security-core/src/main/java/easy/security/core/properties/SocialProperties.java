package easy.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 17:27
 */
@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WxProperties wx = new WxProperties();
}
