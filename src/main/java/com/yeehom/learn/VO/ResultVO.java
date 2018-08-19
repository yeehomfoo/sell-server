package com.yeehom.learn.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by yFoo on 14/01/2018.
 */
@Data
public class ResultVO<T> {

    private Integer code; // 错误吗

    private String msg = "" ; // 提示信息

    private T data; // 具体内容

}
