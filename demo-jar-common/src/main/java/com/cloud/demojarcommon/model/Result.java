package com.cloud.demojarcommon.model;

import com.cloud.demojarcommon.error.CommonErrorCode;
import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object value;

    public Result succ(Object value){
        this.code = 0;
        this.message = "成功";
        this.value = value;
        return this;
    }

    public Result fail(){
        this.code = -1;
        this.message = "失败";
        this.value = null;
        return this;
    }

    public Result fail(CommonErrorCode commonErrorCode){
        this.code = Integer.valueOf(commonErrorCode.code);
        this.message = commonErrorCode.message;
        this.value = null;
        return this;
    }
}
