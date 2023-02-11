package org.bty.blog.controller;

import lombok.RequiredArgsConstructor;
import org.bty.blog.service.CaptchaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bty
 * @date 2023/2/11
 * @since 1.8
 **/
@RestController()
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

    @GetMapping()
    public ResponseEntity getCaptcha(){
        return ResponseEntity.ok(captchaService.getCaptcha());
    }


}
