package dev.ctdmodding.cubelettask.util;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Jon created on 8/2/2020
 */
public class MathUtil {

    private static List<Vector> sphereCache = new ArrayList<>();

    public static List<Vector> getSphere() {
        if (sphereCache.isEmpty()) {
            double spacing = Math.PI / 5;
            for (double phi = 0; phi <= Math.PI; phi += spacing) {
                double r = Math.sin(phi);
                double y = Math.cos(phi);
                for (double theta = 0; theta < Math.PI * 2; theta += spacing) {
                    double x = Math.cos(theta) * r;
                    double z = Math.sin(theta) * r;
                    sphereCache.add(new Vector(x, y, z));
                }
            }
        }
        return sphereCache;
    }
}
