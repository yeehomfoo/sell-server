package com.yeehom.learn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class SellerInfo implements Serializable {

    private static final long serialVersionUID = -8614720204255309942L;

    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
}
