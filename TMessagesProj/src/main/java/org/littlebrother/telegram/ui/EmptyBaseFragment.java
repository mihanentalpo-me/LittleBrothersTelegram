package org.littlebrother.telegram.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import org.littlebrother.telegram.ui.ActionBar.BaseFragment;
import org.littlebrother.telegram.ui.Components.SizeNotifierFrameLayout;

public class EmptyBaseFragment extends BaseFragment {

    @Override
    public View createView(Context context) {
        return fragmentView = new SizeNotifierFrameLayout(context);
    }

}
