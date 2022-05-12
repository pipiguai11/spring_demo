package com.lhw.container.domain;

import com.lhw.container.annotations.Super;
import lombok.Data;

/**
 * @author ：linhw
 * @date ：22.5.11 11:38
 * @description：超类
 * @modified By：
 */
@Super
@Data
public class SuperUser extends User {

    private String address;

    @Override
    public String toString() {
        return "Super(id = " + getId() + " , name = " + getName() + " , address = " + this.address + ")";
    }

}
