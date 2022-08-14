package com.wjl.system.controller;

import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.wjl.common.ResultJson;
import com.wjl.consts.CaptchaConsts;
import com.wjl.system.utils.RedisUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/captcha")
    public ResultJson captcha() throws IOException {
        String captchaKey = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, CaptchaConsts.IMAGE_JPG, outputStream);

        Base64.Encoder encoder = Base64.getEncoder();

        String base64Img = CaptchaConsts.IMAGE_TYPE + encoder.encodeToString(outputStream.toByteArray());

        redisUtil.hset(CaptchaConsts.CAPTCHA_HASH_KEY, captchaKey, code, 300);
        return ResultJson.success(
            MapUtil.builder().put(CaptchaConsts.CAPTCHA_KEY, captchaKey).put(CaptchaConsts.CAPTCHA_BASE64IMAGE,
                base64Img).build());

    }

   /* @PostMapping("/login")
    public Result login() {
        return Result.success("登录成功");
    }*/
}
