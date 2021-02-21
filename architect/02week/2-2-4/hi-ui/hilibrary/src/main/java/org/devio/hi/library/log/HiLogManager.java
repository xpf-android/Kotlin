package org.devio.hi.library.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HiLogManager {
    private static HiLogConfig config;
    private List<HiLogPrinter> printers = new ArrayList<>();

//    private static HiLogManager instance;

    private HiLogManager() {
    }

    public HiLogManager init(@NonNull HiLogConfig config, HiLogPrinter... printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
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

    public List<HiLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(HiLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(HiLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }

}
