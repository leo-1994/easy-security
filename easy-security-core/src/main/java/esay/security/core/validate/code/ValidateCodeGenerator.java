package esay.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 19:14
 */
public interface ValidateCodeGenerator {

    /**
     * 创建校验码
     *
     * @param request HttpServletRequest
     * @return ValidateCode
     */
    ValidateCode generate(HttpServletRequest request);
}
