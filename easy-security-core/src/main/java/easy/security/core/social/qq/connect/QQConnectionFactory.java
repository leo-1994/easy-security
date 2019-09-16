package easy.security.core.social.qq.connect;

import easy.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * qq连接工厂
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/6 16:54
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {


    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
