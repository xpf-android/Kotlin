package org.devio.hi.library.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static org.devio.hi.library.log.HiLogConfig.MAX_LEN;

/**
 * 控制台打印器
 */
public class HiConsolePrinter implements HiLogPrinter {
    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        // 行数
        int countOfSub = len/MAX_LEN;
        if (countOfSub > 0 ) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            // 打印完整行数之外剩余的部分
            if (index != len) {
                log.append(printString.substring(index, len));
                Log.println(level, tag, printString.substring(index, len));
            }

            Log.println(level, tag, log.toString());
        } else {// 内容不足一行(小于512)，全部打印
            Log.println(level, tag, printString);
        }
    }
}
