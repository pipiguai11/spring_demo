package com.lhw.jwt_common;

import com.lhw.jwt_common.utils.RsaUtils;
import org.junit.jupiter.api.Test;

/**
 * @author ：linhw
 * @date ：22.4.18 13:39
 * @description：测试根据指定的盐和密钥长度生成公钥与私钥
 * @modified By：
 */
public class JwtCommonTest {

    private static final String pubKeyPath = "E:\\temp\\jwt\\auth_key\\jwt_key_rsa.pub";
    private static final String priKeyPath = "E:\\temp\\jwt\\auth_key\\jwt_key_rsa";

    @Test
    void test1() throws Exception {
        // 公钥和私钥生成路径，盐，密钥长度
        RsaUtils.generateKey(pubKeyPath,priKeyPath,"lhw", 1024);
    }

}
