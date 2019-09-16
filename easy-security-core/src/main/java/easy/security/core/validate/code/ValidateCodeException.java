package easy.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 17:20
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
