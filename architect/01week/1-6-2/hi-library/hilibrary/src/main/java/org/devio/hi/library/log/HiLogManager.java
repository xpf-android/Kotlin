package org.devio.hi.library.log;

import androidx.annotation.NonNull;

public class HiLogManager {
    private static HiLogConfig config;
//    private static HiLogManager instance;

    private HiLogManager() {
    }

    private HiLogManager(HiLogConfig config) {
        this.config = config;
    }



    public HiLogManager init(@NonNull HiLogConfig config) {
        this.config = config;
        return this;
    }

    /**
     * 静态内部类单例
     */
    private static class Holder{
        private static final HiLogManager instance = new HiLogManager();
    }

    public static HiLogManager getInstance() {
        return Holder.instance;
    }

    public HiLogConfig getConfig() {
        return config;
    }
}
