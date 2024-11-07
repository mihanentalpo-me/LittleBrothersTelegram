package org.littlebrother.telegram.ui.Components.Premium.boosts;

import android.app.Activity;

import org.littlebrother.telegram.ui.ActionBar.BaseFragment;
import org.littlebrother.telegram.ui.ActionBar.Theme;
import org.littlebrother.telegram.ui.Stories.DarkThemeResourceProvider;
import org.littlebrother.telegram.ui.WrappedResourceProvider;

public class DarkFragmentWrapper extends BaseFragment {

    private final BaseFragment parentFragment;

    DarkFragmentWrapper(BaseFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public boolean isLightStatusBar() {
        return false;
    }

    @Override
    public Activity getParentActivity() {
        return parentFragment.getParentActivity();
    }

    @Override
    public Theme.ResourcesProvider getResourceProvider() {
        return new WrappedResourceProvider(new DarkThemeResourceProvider());
    }

    @Override
    public boolean presentFragment(BaseFragment fragment) {
        return false;
    }
}
