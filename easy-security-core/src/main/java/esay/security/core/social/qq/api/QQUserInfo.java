package esay.security.core.social.qq.api;

import lombok.Data;

/**
 * qq用户信息
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/6 15:54
 */
@Data
public class QQUserInfo {
    private String openId;
    private Integer ret;
    private String msg;
    private String nickname;
    private String figureurl;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq_1;
    private String figureurl_qq_2;
    private String gender;
}
