package easy.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/6 10:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = -300695032591282284L;

    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
