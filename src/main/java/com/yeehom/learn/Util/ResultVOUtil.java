package com.yeehom.learn.util;

import com.yeehom.learn.vo.ResultVO;

/**
 * Created by yFoo on 16/01/2018.
 */
public class ResultVOUtil {

    public static <T> ResultVO<T> success(T object) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return  resultVO;
    }

    public static ResultVO success() {
        return  success(null);
    }

    public static ResultVO<String> error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
