package org.devio.hi.library.log;

/**
 * 日志线程格式化器
 */
public class HiThreadFormatter implements HiLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread: " + data.getName();
    }
}
