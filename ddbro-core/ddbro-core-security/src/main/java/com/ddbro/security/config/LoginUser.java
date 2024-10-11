package com.ddbro.security.config;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 身份验证用户
 *
 * @author miaol
 * @date 2022/10/20
 */
@Getter
public class LoginUser extends User implements Serializable {

    private static final InheritableThreadLocal<LoginUser> currentUser = new InheritableThreadLocal<>();
    private static final long serialVersionUID = 1L;
    /**
     * 账号ID
     **/
    private String id;
    /**
     * 密码
     */
    private String password;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 姓名
     **/
    private String name;
    /**
     * 有效邮箱
     **/
    private String email;
    /**
     * 角色
     */
    private List<String> auths;

    /**
     * 登录用户构造器
     *
     * @param id                 id
     * @param loginName          登录名
     * @param name               名字
     * @param email              电子邮件
     * @param pwd                松材线虫病
     * @param status             状态
     * @param auths              身份验证
     */
    public LoginUser(String id, String loginName, String name, String email, String pwd, Integer status, List<String> auths) {
        super(loginName,
                pwd,
                status == 1,
                true,
                true,
                true,
//                角色
                auths.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        this.id = id;
        this.password = pwd;
        this.loginName = loginName;
        this.status = status;
        this.name = name;
        this.email = email;
        this.auths = auths;

    }

    public static void setUser(LoginUser user) {
        currentUser.set(user);
    }

//
//    /**
//     * 特权  1,2,3   都好分割
//     */
//    private String privilege;

    public static LoginUser currentUser() {
        return currentUser.get();
    }

    public static void remove() {
        currentUser.remove();
    }
}
