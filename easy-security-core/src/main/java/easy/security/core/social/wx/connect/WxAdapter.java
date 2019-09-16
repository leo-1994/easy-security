package easy.security.core.social.wx.connect;

import easy.security.core.social.wx.api.Wx;
import easy.security.core.social.wx.api.WxUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/7 17:25
 */
public class WxAdapter implements ApiAdapter<Wx> {

    private String openId;

    public WxAdapter() {
    }

    public WxAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(Wx api) {
        return true;
    }

    @Override
    public void setConnectionValues(Wx api, ConnectionValues values) {
        WxUserInfo profile = api.getUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Wx api) {
        return null;
    }

    @Override
    public void updateStatus(Wx api, String message) {

    }
}
