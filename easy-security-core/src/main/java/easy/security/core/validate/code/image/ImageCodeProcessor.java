package easy.security.core.validate.code.image;

import easy.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:46
 */
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        assert request.getResponse() != null;
        ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
