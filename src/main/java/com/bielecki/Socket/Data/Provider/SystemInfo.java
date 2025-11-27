package com.bielecki.Socket.Data.Provider;

import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Map.entry;

public class SystemInfo {

    public Map<String,Object> getSystemInfo(){
        oshi.SystemInfo si = new oshi.SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        CentralProcessor processor = si.getHardware().getProcessor();
        processor.getLogicalProcessorCount();

        long uptime = os.getSystemUptime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        GlobalMemory memory = si.getHardware().getMemory();

        Map<String, Object> map = Collections.unmodifiableMap(
                new LinkedHashMap<>() {{
                    put("Welcome on raspberryPI Zero! ", "");
                            put("local time: ", LocalDate.now().format(formatter));
                            put("manufacturer: ", os.getManufacturer());
                            put("family: ", os.getFamily());
                            put("version: ", os.getVersionInfo());
                            put("uptime: ", Math.round((float) uptime / 3600) + " hours");
                            put("memory usage: ", Math.round((float) memory.getAvailable() / memory.getTotal()));
                            put("cpuLoad: ", Math.round(cpuLoad) + "%");
                }}
        );
        return map;

//        List<HWDiskStore> diskStores = si.getHardware().getDiskStores();
//        for (HWDiskStore disk : diskStores) {
//            System.out.println("disk.getModel(): "+disk.getModel());
//            System.out.println("disk.getSize(): "+disk.getSize());
//        }


    }

}
