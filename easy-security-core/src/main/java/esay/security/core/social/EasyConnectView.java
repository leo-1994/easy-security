package esay.security.core.social;


import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/9 17:50
 */
public class EasyConnectView extends AbstractView {

    private static final String CONNECTION = "connection";

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8");
        if (model.get(CONNECTION) == null) {
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            response.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
