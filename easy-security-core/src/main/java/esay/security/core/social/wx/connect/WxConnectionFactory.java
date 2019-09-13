package esay.security.core.social.wx.connect;

import esay.security.core.social.wx.api.Wx;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * 微信连接工厂
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/7 17:32
 */
public class WxConnectionFactory extends OAuth2ConnectionFactory<Wx> {

    public WxConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WxServiceProvider(appId,appSecret), new WxAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WxAccessGrant) {
            return ((WxAccessGrant) accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<Wx> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<Wx> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<Wx> getApiAdapter(String providerUserId) {
        return new WxAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<Wx> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<Wx>) getServiceProvider();
    }
}
