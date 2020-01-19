package com.example.rajat.notes;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Rajat Sangrame on 19/1/20.
 * http://github.com/rajatsangrame
 */
public class SimpleIdlingResource implements IdlingResource {

    @Nullable
    private volatile ResourceCallback mCallback;

    // Idleness is controlled with this boolean.
    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);
    private static final String TAG = "SimpleTimeIdlingResource";

    SimpleIdlingResource() {
    }

    @Override
    public String getName() {
        return TAG;
    }

    @Override
    public void registerIdleTransitionCallback(
            ResourceCallback resourceCallback) {
        mCallback = resourceCallback;
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }


    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     *
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}
