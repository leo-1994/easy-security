package esay.security.core.properties;

import esay.security.core.social.support.AbstractSocialProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * qq登录配置属性
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/6 17:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QQProperties extends AbstractSocialProperties {
    private String providerId = "qq";

}
