package easy.security.core.social.wx.api;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/7 17:00
 */
public class WxImpl extends AbstractOAuth2ApiBinding implements Wx {
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    private WxUserInfo userInfo;

    public WxImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }


    @Override
    public WxUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public WxUserInfo getUserInfo(String openId) {
        if (userInfo == null) {
            String url = URL_GET_USER_INFO + openId;
            String response = getRestTemplate().getForObject(url, String.class);
            if (StringUtils.contains(response, "errcode")) {
                return null;
            }
            userInfo = JSON.parseObject(response, WxUserInfo.class);
        }
        return userInfo;
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return messageConverters;
    }

}
