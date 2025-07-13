package org.homework.weather;

import java.util.Map;

public class WeatherDataPrinter {
    public void mapDataPrinter(Map<?, ?> map, String format, String message, boolean onStart) {
        if (onStart) System.out.println(message);
        mapDataPrinter(map, format);
        if (!onStart) System.out.println(message);
    }

    public void mapDataPrinter(Map<?, ?> map, String format, String startMessage, String endMessage) {
        System.out.println(startMessage);
        mapDataPrinter(map, format);
        System.out.println(endMessage);
    }

    public void mapDataPrinter(Map<?, ?> map, String format) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String line = format.formatted(entry.getKey(), entry.getValue());
            System.out.println(line);
        }
    }
}
