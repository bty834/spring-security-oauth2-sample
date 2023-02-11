package org.bty.blog.controller;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.bty.blog.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author bty
 * @date 2023/2/11
 * @since 1.8
 **/
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refresh(String refreshToken) {

        if(Strings.isNullOrEmpty(refreshToken)){
            return ResponseEntity.ok(Collections.singletonMap("msg","no refreshToken or invalid refreshToken"));
        }
        String accessToken = tokenService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(Collections.singletonMap("accessToken",accessToken));
    }
}
