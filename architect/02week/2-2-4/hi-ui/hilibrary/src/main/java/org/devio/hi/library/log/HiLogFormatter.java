package org.devio.hi.library.log;

/**
 * 日志格式化接口
 */
public interface HiLogFormatter<T> {
    String format(T data);
}
