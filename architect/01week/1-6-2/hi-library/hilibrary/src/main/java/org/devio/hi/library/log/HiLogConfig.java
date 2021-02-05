package org.devio.hi.library.log;

/**
 * 配置类
 */
public class HiLogConfig {

    /**
     * 默认的tag
     * @return
     */
    public String getGlobalTag() {
        return "HiLog";
    }

    /**
     * 默认启用
     * @return
     */
    public boolean enable() {
        return true;
    }
}
