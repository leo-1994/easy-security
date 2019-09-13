package esay.security.core.properties;

import esay.security.core.social.support.AbstractSocialProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信登录配置属性
 * @author chao.li@quvideo.com
 * @date 2019/9/7 16:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxProperties extends AbstractSocialProperties {
    private String providerId = "wx";
}
