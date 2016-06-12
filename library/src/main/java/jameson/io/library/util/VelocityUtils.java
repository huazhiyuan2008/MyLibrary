package jameson.io.library.util;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.ViewConfiguration;

/**
 * 从android.widget.Scroller中剥出来的速度函数
 * Created by jameson on 5/18/16.
 */
public class VelocityUtils {
    private static final float INFLEXION = 0.35f;
    private static float DECELERATION_RATE = (float) (Math.log(0.78) / Math.log(0.9));
    private static float mFlingFriction = ViewConfiguration.getScrollFriction();
    private static float mPhysicalCoeff;
    private static float mPpi;

    public static int getFinalDistance(Context context, int velocity) {
        return getFinalDistance(context, 0, velocity, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getFinalDistance(Context context, int startX, int velocity, int minDistance, int maxDistance) {
        init(context);

        int distance = (int) getSplineFlingDistance(velocity);
        if (velocity < 0) {
            distance = -distance;
        }
        int mFinalX = startX + distance;
        // LogUtils.d(String.format("mFinalX=%s, deltaX=%s", mFinalX, (mFinalX - startX)));
        mFinalX = Math.min(mFinalX, maxDistance);
        mFinalX = Math.max(mFinalX, minDistance);
        // LogUtils.d(String.format("mFinalX2=%s", mFinalX));

        return mFinalX;
    }

    private static void init(Context context) {
        if (mPpi > 0) return;

        mPpi = context.getResources().getDisplayMetrics().density * 160.0f;
        mPhysicalCoeff = computeDeceleration(0.84f);
    }

    private static float computeDeceleration(float friction) {
        return SensorManager.GRAVITY_EARTH   // g (m/s^2)
                * 39.37f               // inch/meter
                * mPpi                 // pixels per inch
                * friction;
    }

    private static double getSplineFlingDistance(float velocity) {
        final double l = getSplineDeceleration(velocity);
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        return mFlingFriction * mPhysicalCoeff * Math.exp(DECELERATION_RATE / decelMinusOne * l);
    }

    private static double getSplineDeceleration(float velocity) {
        return Math.log(INFLEXION * Math.abs(velocity) / (mFlingFriction * mPhysicalCoeff));
    }
}
