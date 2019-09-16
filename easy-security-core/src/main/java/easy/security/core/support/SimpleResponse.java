package easy.security.core.support;

import lombok.Data;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 15:42
 */
@Data
public class SimpleResponse {
    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;
}
