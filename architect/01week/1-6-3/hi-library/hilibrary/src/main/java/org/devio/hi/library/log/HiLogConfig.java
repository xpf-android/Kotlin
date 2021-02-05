package org.devio.hi.library.log;

/**
 * 配置类
 */
public class HiLogConfig {
    //日志显示每一行的最大长度
    static int MAX_LEN = 512;

    static HiThreadFormatter HI_THREAD_FORMATTER = new HiThreadFormatter();
    static HiStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }


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

    /**
     * 是否包含线程信息
     * @return false 默认不包含
     */
    public boolean includeTread() {
        return false;
    }

    /**
     * 堆栈信息深度
     * 可以获取较大的深度，但是不利于排查分析
     * 一般获取前几条堆栈信息
     */
    public int stackTraceDepth() {
        return 5;
    }

    public HiLogPrinter[] printers() {
        return null;
    }

    /**
     * 序列化接口
     */
    public interface JsonParser {
        //Object--->String
        String toJson(Object src);
    }
}
