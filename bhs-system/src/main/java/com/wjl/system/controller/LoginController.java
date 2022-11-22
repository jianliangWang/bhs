package com.wjl.system.controller;

import com.google.code.kaptcha.Producer;
import com.wjl.common.system.ResultJson;
import com.wjl.consts.system.CaptchaConsts;
import com.wjl.system.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录")
@RestController
public class LoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public ResultJson captcha() throws IOException {
        String captchaKey = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, CaptchaConsts.IMAGE_JPG, outputStream);

        Base64.Encoder encoder = Base64.getEncoder();

        String base64Img = CaptchaConsts.IMAGE_TYPE + encoder.encodeToString(outputStream.toByteArray());

        redisUtil.set(captchaKey, code, 300);
        Map<String, String> map = new HashMap<>();
        map.put(CaptchaConsts.CAPTCHA_KEY, captchaKey);
        map.put(CaptchaConsts.CAPTCHA_BASE64IMAGE, base64Img);
        return ResultJson.success(map);

    }

   /* @PostMapping("/login")
    public Result login() {
        return Result.success("登录成功");
    }*/
}
