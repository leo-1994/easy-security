package esay.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/10 17:38
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     *
     * @param request          ServletWebRequest
     * @param code             ValidateCode
     * @param validateCodeType ValidateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request          ServletWebRequest
     * @param validateCodeType ValidateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request  ServletWebRequest
     * @param validateCodeType ValidateCodeType
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}
