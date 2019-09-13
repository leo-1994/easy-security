package esay.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同检验码的处理逻辑
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:38
 */
public interface ValidateCodeProcessor {


    /**
     * 创建校验码
     *
     * @param request ServletWebRequest
     * @throws Exception exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 执行校验
     *
     * @param request ServletWebRequest
     */
    void validate(ServletWebRequest request);
}
