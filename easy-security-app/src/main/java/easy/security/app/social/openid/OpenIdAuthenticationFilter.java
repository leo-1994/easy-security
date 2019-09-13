package easy.security.app.social.openid;

import esay.security.core.properties.EasySecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chao.li@quvideo.com
 * @date 2019/9/10 19:01
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openidParameter = EasySecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;
    private String providerIdParameter = EasySecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDER_ID;
    private boolean postOnly = true;

    private static final String METHOD_POST = "POST";

    protected OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(EasySecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, METHOD_POST));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals(METHOD_POST)) {
            throw new AuthenticationServiceException("Authentication method not supported:" + request.getMethod());
        }
        String openid = obtainOpenid(request);
        String providerId = obtainProviderId(request);
        if (openid == null) {
            openid = "";
        }
        if (providerId == null) {
            providerId = "";
        }
        openid = openid.trim();
        providerId = providerId.trim();
        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openid, providerId);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainOpenid(HttpServletRequest request) {
        return request.getParameter(openidParameter);
    }

    private String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

}
