package com.ddbro.core.exception;

import com.ddbro.core.constant.ResultCodeEnum;
import org.junit.jupiter.api.Test;

class AssertTest {

    @Test
    void isTrue() {
        Boolean b = null;
        Assert.isTrue(b, "123{0}456{1}", "789", "789");
        ResultCodeEnum.SERVER_ERROR.isTrue(false, "");
    }
}