package esay.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/9 20:06
 */
@Data
public class SessionProperties {
    /**
     * 同一个用户在系统中的最大session数，默认1
     */
    private int maximumSessions = 1;
    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin = false;
    /**
     * session失效时跳转的地址
     */
    private String sessionInvalidUrl = EasySecurityConstants.DEFAULT_SESSION_INVALID_URL;
}
