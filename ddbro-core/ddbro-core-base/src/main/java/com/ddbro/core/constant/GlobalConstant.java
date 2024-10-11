package com.ddbro.core.constant;

/**
 * @author miaol
 * @date 2020-05-14 9:52
 */
public interface GlobalConstant {

    /**
     * Header token常量
     */
    String AUTHORIZATION_TOKEN = "Authorization";

    /**
     * Header Bearer token常量
     */
    String AUTHORIZATION_TOKEN_BEARER = "Bearer ";

    /**
     * 默认日期时间格式
     */
    String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式
     */
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认时间格式
     */
    String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 文件上传根目录
     */
    String UPLOAD_ROOT_DIR = "newscp";

    /**
     * 调用成功状态码
     */
    int R_SUCCESS = 200;

    /**
     * 树形结构最顶级父ID
     */
    Long TREE_TOP_PARENT_ID = 0L;

    /**
     * SAAS商户ID
     */
    Long SAAS_TENANT_ID = 1L;

    /**
     * 总部CODE
     */
    String SAAS_TENANT_CODE = "101";
    /**
     * 麻城电商商户ID
     */
    Long E_SAAS_TENANT_ID = -1L;

    /**
     * 麻城电总部CODE
     */
    String E_SAAS_TENANT_CODE = "102";
    /**
     * 用户ID
     */
    Long SYS_USER_ID = 0L;

    /**
     * 默认员工姓名/手机号
     */
    String SYS_USER_NAME = "SYS";

    /**
     * 供应商类型
     */
    Integer SAAS_TENANT_TYPE = 3;

    /**
     * 采购属性--统采
     */
    Integer PURCHASE_TYPE_UNITE = 1;

    /**
     * 采购属性--区采
     */
    Integer PURCHASE_TYPE_AREA = 2;

    /**
     * 采购属性--店采
     */
    Integer PURCHASE_TYPE_SHOP = 3;

    /**
     * 生产环境
     */
    String PROFILES_PROD = "prod";

    /**
     * 测试环境
     */
    String PROFILES_TEST = "test";

    /**
     * 开发环境
     */
    String PROFILES_DEV = "dev";

    Long SYS_ADMIN_USERID = 1296652088951967744L;

    String SYS_ADMIN_USERNAME = "系统管理员";

}
