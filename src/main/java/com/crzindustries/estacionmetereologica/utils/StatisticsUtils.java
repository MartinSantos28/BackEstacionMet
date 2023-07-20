package com.crzindustries.estacionmetereologica.utils;

import java.util.List;

public class StatisticsUtils {
    public static Float getAverage(List<Float> values) {
        Float sum = 0.0F;

        for (Float value : values) {
            sum += value;
        }

        return sum / values.size();
    }

    public static Float getMode(List<Float> values) {
        Float mode = 0.0F;
        Float maxCount = 0.0F;

        for (Float value : values) {
            Float count = 0.0F;

            for (Float value2 : values) {
                if (value2.equals(value)) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                mode = value;
            }
        }

        return mode;
    }

    public static Float getMedian(List<Float> values) {
        Float median = 0.0F;

        int middle = values.size() / 2;

        if (values.size() % 2 == 1) {
            median = values.get(middle);
        } else {
            median = (values.get(middle - 1) + values.get(middle)) / 2.0F;
        }

        return median;
    }
}
