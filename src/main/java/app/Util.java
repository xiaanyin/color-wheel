package app;

public class Util {
    public static Double[][] getCoordinate(Double height, Double width, Double radius, Double margin) {
        var centerX = width / 2.0D - radius;
        var centerY = height / 2.0D - radius;
        var cycle_radius = (height - margin * 2.0D - radius * 2.0D) / 2.0D;
        var step1 = cycle_radius / 2.0D;
        var step2 = cycle_radius * Math.sqrt(3) / 2.0D;
        return new Double[][]{
                {centerX, centerY - cycle_radius},
                {centerX + step1, centerY - step2},
                {centerX + step2, centerY - step1},
                {centerX + cycle_radius, centerY},
                {centerX + step2, centerY + step1},
                {centerX + step1, centerY + step2},
                {centerX, centerY + cycle_radius},
                {centerX - step1, centerY + step2},
                {centerX - step2, centerY + step1},
                {centerX - cycle_radius, centerY},
                {centerX - step2, centerY - step1},
                {centerX - step1, centerY - step2},
        };
    }
}
