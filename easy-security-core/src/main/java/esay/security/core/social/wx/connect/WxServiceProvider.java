package esay.security.core.social.wx.connect;

import esay.security.core.social.wx.api.Wx;
import esay.security.core.social.wx.api.WxImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/7 17:26
 */
public class WxServiceProvider extends AbstractOAuth2ServiceProvider<Wx> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WxServiceProvider(String appId, String appSecret) {
        super(new WxOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Wx getApi(String accessToken) {
        return new WxImpl(accessToken);
    }
}
