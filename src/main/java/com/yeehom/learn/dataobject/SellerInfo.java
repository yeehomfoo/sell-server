package com.yeehom.learn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SellerInfo {

    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
}
