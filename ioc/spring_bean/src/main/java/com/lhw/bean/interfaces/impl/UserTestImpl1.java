package com.lhw.bean.interfaces.impl;

import com.lhw.bean.interfaces.UserTestInterface;

/**
 * @author ：linhw
 * @date ：22.5.15 11:43
 * @description：测试实现类1
 * @modified By：
 */
public class UserTestImpl1 implements UserTestInterface {
    @Override
    public String getMessage() {
        return "测试实现类1";
    }
}
