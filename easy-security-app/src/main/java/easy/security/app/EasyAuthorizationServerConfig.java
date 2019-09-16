package easy.security.app;

import easy.security.core.properties.EasySecurityProperties;
import easy.security.core.properties.OAuth2ClientProperties;
import easy.security.core.support.EasyUserDetailsService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/10 11:20
 */
@EnableAuthorizationServer
@Configuration
public class EasyAuthorizationServerConfig
        extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EasyUserDetailsService easyUserDetailsService;

    @Autowired
    private EasySecurityProperties easySecurityProperties;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(easyUserDetailsService)
                .tokenStore(tokenStore);
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);

            endpoints
                    .tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(easySecurityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties clientConfig : easySecurityProperties.getOauth2().getClients()) {
                builder.withClient(clientConfig.getClientId())
                        .secret(passwordEncoder.encode(clientConfig.getClientSecret()))
                        .redirectUris(StringUtils.split(clientConfig.getRedirectUris(), ","))
                        .authorizedGrantTypes(StringUtils.split(clientConfig.getAuthorizedGrantTypes(), ","))
                        .scopes(StringUtils.split(clientConfig.getScopes(), ","))
                        .autoApprove(true)
                        .accessTokenValiditySeconds(clientConfig.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(clientConfig.getRefreshTokenValiditySeconds());
            }
        }
    }

}
