package org.bty.blog.security;

import lombok.RequiredArgsConstructor;
import org.bty.blog.security.model.BearerTokenAuthenticationToken;
import org.bty.blog.security.model.RedisOAuth2User;
import org.bty.blog.security.model.RedisUserDetail;
import org.bty.blog.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author bty
 * @date 2023/2/6
 * @since 1.8
 **/
@Component
@RequiredArgsConstructor
public class BearerTokenAuthenticationManager implements AuthenticationManager {

    private static final Logger logger = LoggerFactory.getLogger(BearerTokenAuthenticationManager.class);

    private final TokenService tokenService;

    /**
     *
     * @param authentication {@link BearerTokenAuthenticationToken}
     * @return {@link UsernamePasswordAuthenticationToken} or {@link OAuth2AuthenticationToken}
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken bearerToken = (BearerTokenAuthenticationToken) authentication;

        String token = bearerToken.getToken();

        Object o;
        try {
            o = tokenService.verifyToken(token);
        } catch (Exception e) {
            throw new SessionAuthenticationException(e.getMessage());
        }

        Authentication authenticationResult;

        if (o instanceof RedisUserDetail) {

            RedisUserDetail userDetail = (RedisUserDetail) o;
            authenticationResult = new UsernamePasswordAuthenticationToken(userDetail.getUsername(),
                    null,
                    userDetail.getAuthorities());

        } else if (o instanceof RedisOAuth2User) {
            RedisOAuth2User oAuth2User = (RedisOAuth2User) o;
            authenticationResult = new OAuth2AuthenticationToken(oAuth2User, oAuth2User.getAuthorities(), oAuth2User.getRegistrationId());
        } else {
            logger.error("not valid token");
            throw new SessionAuthenticationException("not valid token");
        }
        logger.info("bearer token is authenticated , authentication is :{}",authentication);
        return authenticationResult;
    }
}
