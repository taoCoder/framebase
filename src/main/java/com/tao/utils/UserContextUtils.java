package com.tao.utils;


import com.tao.domain.User;

/**
 * @author huangtao54
 * @description
 * @date 2018/8/31
 */
public class UserContextUtils {
    private final static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    public static void set(User user) {
        userThreadLocal.set(user);
    }

    public static User get() {
        return userThreadLocal.get();
    }

    public static boolean isLogin() {
        User user = get();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void clear(){
        userThreadLocal.remove();
    }
}
