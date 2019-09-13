package easy.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/9 19:54
 */
public class EasyExpireSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public EasyExpireSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }
}
