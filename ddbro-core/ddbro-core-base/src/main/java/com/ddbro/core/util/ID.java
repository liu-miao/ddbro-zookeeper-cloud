package com.ddbro.core.util;


import cn.hutool.core.util.IdUtil;

/**
 * 雪花算法工具
 *
 * @author miaol
 * @date 2023/02/16
 */
public class ID {
    /**
     * 获取 ID
     *
     * @return {@code Long}
     */
    public static Long getId() {
        // 根据每个ip 生成的 唯一id  再生成一个唯一id
        // 使用自动生成的工作节点ID和数据中心ID
        return IdUtil.getSnowflake().nextId();
    }

    /**
     * 获取 id str
     *
     * @return {@code String}
     */
    public static String getIdStr() {
        return IdUtil.getSnowflake().nextIdStr();
    }

}