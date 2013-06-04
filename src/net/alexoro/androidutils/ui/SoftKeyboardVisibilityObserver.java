package net.alexoro.androidutils.ui;

import android.app.Activity;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * User: UAS
 * Date: 03.06.13 16:14
 */
public class SoftKeyboardVisibilityObserver {

    public interface OnSoftKeyboardVisibilityChangedListener {
        void onSoftKeyboardVisibilityChanged(boolean isVisible);
    }

    private static final int MAGIC_MIN_KEYBOARD_HEIGHT_IN_DP = 120;

    private Activity mActivity;
    private View vContentView;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    private boolean mIsKeyboardDisplayed;
    private OnSoftKeyboardVisibilityChangedListener mListener;

    public SoftKeyboardVisibilityObserver() {

    }

    /**
     *
     * @param activity
     * @param listener
     */
    public void registerSoftKeyboardListener(Activity activity, OnSoftKeyboardVisibilityChangedListener listener) {
        // validate args
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        if (mActivity != null) {
            throw new IllegalStateException("There is an already registered listener");
        }

        // setup args
        mActivity = activity;
        mListener = listener;

        // find root view
        vContentView = mActivity.getWindow().getDecorView();

        // check out keyboard visibility
        int heightDiff = vContentView.getRootView().getHeight() - vContentView.getHeight();
        mIsKeyboardDisplayed = heightDiff > getMinKeyboardHeight();

        // set layout listener
        mOnGlobalLayoutListener = new OnGlobalLayoutListenerImpl();
        vContentView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    /**
     *
     * @param activity
     */
    public void unregisterSoftkeyboardListener(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null");
        }
        if (activity != mActivity) {
            throw new IllegalStateException("This activity is not observed");
        }
        vContentView.getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
        mOnGlobalLayoutListener = null;
        vContentView = null;
        mActivity = null;
    }

    /**
     *
     * @return
     */
    public boolean isSoftKeyboardVisible() {
        if (mActivity == null) {
            throw new IllegalStateException("No activity is observed");
        }
        return mIsKeyboardDisplayed;
    }

    class OnGlobalLayoutListenerImpl implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            int windowHeight = vContentView.getHeight();

            Rect r = new Rect();
            vContentView.getWindowVisibleDisplayFrame(r);
            int visibleHeight = r.height();

            int heightDiff = windowHeight - visibleHeight;
            if ((heightDiff > getMinKeyboardHeight()) != mIsKeyboardDisplayed) {
                mIsKeyboardDisplayed = heightDiff > getMinKeyboardHeight();
                mListener.onSoftKeyboardVisibilityChanged(mIsKeyboardDisplayed);
            }
        }
    }

    protected int getMinKeyboardHeight() {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                MAGIC_MIN_KEYBOARD_HEIGHT_IN_DP,
                mActivity.getResources().getDisplayMetrics()
        );
    }

}