package esay.security.core.social.support;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 17:20
 */
public abstract class AbstractSocialProperties {
    private String appId;
    private String appSecret;
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppSecret() {
        return this.appSecret;
    }
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}