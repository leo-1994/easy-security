package easy.security.core.validate.code.image;

import easy.security.core.validate.code.ValidateCodeGenerator;
import easy.security.core.properties.EasySecurityProperties;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/5 19:16
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private EasySecurityProperties easySecurityProperties;

    public ImageCodeGenerator(EasySecurityProperties easySecurityProperties) {
        this.easySecurityProperties = easySecurityProperties;
    }

    private final Random random = new Random();

    @Override
    public ImageCode generate(HttpServletRequest request) {
        int width = ServletRequestUtils.getIntParameter(request, "width",
                easySecurityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height",
                easySecurityProperties.getCode().getImage().getHeight());
        int expireIn = easySecurityProperties.getCode().getImage().getExpireIn();
        int length = easySecurityProperties.getCode().getImage().getLength();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + random.nextInt(10), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageCode(image, sRand.toString(), expireIn);
    }

    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
