package easy.security.core.social.wx.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/7 17:18
 */
public class WxAccessGrant extends AccessGrant {

    private static final long serialVersionUID = -5846815296987265844L;
    private String openId;

    public WxAccessGrant() {
        super("");
    }

    public WxAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
