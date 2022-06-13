package com.zjsq.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CommunityUtil {

    //生成随机字符串(盐)
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //MD5加密只能加密不能解密
    //用户密码 + salt(随机)= key
    public static String md5(String key){
      if(StringUtils.isBlank(key)){//第三方包的工具判断"各种意义上的判空"
          return null;
      }
      return DigestUtils.md5DigestAsHex(key.getBytes());//把key加密成16进制的结果
    }
}
