package com.nmmoc7.randommagic.util;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;

/**
 * @author DustW
 */
public class MathUtils {
    public static final int MAX_LIGHT = LightTexture.packLight(15, 15);
    public static final int LIGHT_12 = LightTexture.packLight(12, 12);
    public static final int HALF_LIGHT = LightTexture.packLight(7, 8);

    public static boolean containsInclusive(AxisAlignedBB boundingBox, Vector3d vec) {
        return containsInclusive(boundingBox, vec.getX(), vec.getY(), vec.getZ());
    }

    public static boolean containsInclusive(AxisAlignedBB boundingBox, double x, double y, double z) {
        return x >= boundingBox.getMin(Direction.Axis.X) && x <= boundingBox.getMax(Direction.Axis.X)
                && y >= boundingBox.getMin(Direction.Axis.Y) && y <= boundingBox.getMax(Direction.Axis.Y)
                && z >= boundingBox.getMin(Direction.Axis.Z) && z <= boundingBox.getMax(Direction.Axis.Z);
    }
}
