package easy.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 15:46
 */
@Data
@ConfigurationProperties(prefix = "easy.security")
public class EasySecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();
}
