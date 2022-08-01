package hou.Application.common;

/**
 * Base ThreadLocal封装工具类， 用于用户保存和获取当前登录的用户ID
 */
public class BaseContext {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
