package easy.security.core.social.support;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/7 15:22
 */
@Data
public class SocialUserInfo {
    private String providerId;
    private String providerUserId;
    private String nickname;
    private String avatarUrl;
}
