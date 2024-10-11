package com.ddbro.dataCore.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 可审计基础
 * MongoCommon
 *
 * @author liumiao
 * @since 2022/09/10
 */
@Setter
@Getter
public class BaseAuditable {
    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime gmtModified;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime gmtCreated;
    /**
     * 创造者id
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创造者id")
    protected String creatorId;
    /**
     * 修理者id
     */
    @Schema(description = "修理者id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String menderId;
}
