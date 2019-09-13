package esay.security.core;

import esay.security.core.properties.EasySecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/3 15:49
 */
@Configuration
@EnableConfigurationProperties(EasySecurityProperties.class)
public class EasySecurityCoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
