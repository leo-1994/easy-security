package easy.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/11 10:06
 */
@Data
public class OAuth2Properties {

    private String storeType;

    private String jwtSigningKey = "easy-security";

    private OAuth2ClientProperties[] clients = {};
}
