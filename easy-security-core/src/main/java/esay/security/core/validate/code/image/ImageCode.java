package esay.security.core.validate.code.image;

import esay.security.core.validate.code.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 15:57
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ImageCode extends ValidateCode {
    private static final long serialVersionUID = -3979974066774369020L;
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

}
