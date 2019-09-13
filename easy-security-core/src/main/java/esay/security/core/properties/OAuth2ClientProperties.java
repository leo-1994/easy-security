package esay.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/11 10:06
 */
@Data
public class OAuth2ClientProperties {

    private String clientId;
    private String clientSecret;
    private String redirectUris;
    private String authorizedGrantTypes;
    private String scopes;
    private int accessTokenValiditySeconds = 7200;
    private int refreshTokenValiditySeconds = 12000;
}
