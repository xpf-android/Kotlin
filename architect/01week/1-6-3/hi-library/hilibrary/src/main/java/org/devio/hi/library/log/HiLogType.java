package org.devio.hi.library.log;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 日志类型/级别
 */
public class HiLogType {

    @IntDef({V, D, I, W, E, A})// 取值范围
    @Retention(RetentionPolicy.SOURCE)// 作用域为源码阶段
    public @interface TYPE {
    }


    public final static int V = Log.VERBOSE;
    public final static int D = Log.DEBUG;
    public final static int I = Log.INFO;
    public final static int W = Log.WARN;
    public final static int E = Log.ERROR;
    public final static int A = Log.ASSERT;
}
