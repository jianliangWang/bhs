package com.wjl.consts.system;

public class CaptchaConsts {

    public static final String IMAGE_JPG = "jpg";

    public static final String IMAGE_TYPE = "data:image/jpeg;base64,";

    public static final String CAPTCHA_HASH_KEY = "captchaHash";

    /**
     *  验证码的key，
     *  获取验证码的时候回传给前端的参数名字
     *  验证验证码的时候传给后台的参数名字
     */
    public static final String CAPTCHA_KEY = "key";

    public static final String CAPTCHA_VALUE = "verifyCode";// 验证码前台传入的值的参数名字

    public static final String CAPTCHA_BASE64IMAGE = "captchaImg";
}
