package easy.security.core.properties;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 15:48
 */
@Data
public class BrowserProperties {
    private String loginPage = EasySecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private String signUpUrl = EasySecurityConstants.DEFAULT_SIGN_UP_URL;

    private String signOutUrl = EasySecurityConstants.DEFAULT_SIGN_OUT_URL;

    private int rememberMeSeconds = 3600;

    private SessionProperties session = new SessionProperties();

}
