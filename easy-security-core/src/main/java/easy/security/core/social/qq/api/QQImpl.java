package easy.security.core.social.qq.api;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 16:03
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    private String appId;
    private String openId;

    private QQUserInfo userInfo;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        if (userInfo == null) {
            String url = String.format(URL_GET_USER_INFO, appId, openId);
            String result = getRestTemplate().getForObject(url, String.class);
            userInfo = JSON.parseObject(result, QQUserInfo.class);
            if (userInfo == null) {
                throw new RuntimeException(String.format("获取用户信息失败，url:%s,result:%s", url, result));
            }
            userInfo.setOpenId(openId);
        }
        return userInfo;
    }
}
