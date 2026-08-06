package org.epsda.user.constants;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户服务常量
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 10:40
 * Package Name: org.epsda.user.constants
 * Project Name: online-oj
 */
public record Constants() {
    public static final String SYSTEM_USER_EMAIL_SUFFIX = "@online-oj.admin.com";
    public static final String USER_DEFAULT_EMAIL_SUFFIX = "@online-oj.com";
    public static final String DEFAULT_AVATAR_URL =
            "https://online-oj-platform-bucket.oss-cn-hangzhou.aliyuncs.com/default_avatar.png";
}
