package esay.security.core.social.wx.api;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/7 16:58
 */
public interface Wx {

    WxUserInfo getUserInfo();

    WxUserInfo getUserInfo(String openId);
}
