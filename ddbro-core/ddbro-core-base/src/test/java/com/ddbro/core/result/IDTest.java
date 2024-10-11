package com.ddbro.core.result;

import com.ddbro.core.util.ID;
import org.junit.jupiter.api.Test;

class IDTest {

    @Test
    void getIdStr() {
        System.out.println(ID.getIdStr().length());
        System.out.println(ID.getId().toString().length());
    }
}