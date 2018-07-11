package com.referazi.security;

import com.referazi.models.Auth;
import com.referazi.models.User;

public final class SecurityUtils {

    private static ThreadLocal<Auth> usersAuth = new ThreadLocal<Auth>();

    private SecurityUtils() {

    }

    public static Auth getAuthDetails() {
        return usersAuth.get();
    }

    public static User getUser() {
        return usersAuth.get().getUser();
    }

    public static void setUserAuthInfo(Auth userAuth) {
        usersAuth.set(userAuth);
    }

    public static void resetUserAuthInfo() {
        usersAuth.remove();
    }

}

