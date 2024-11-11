package org.littlebrother.telegram.ui.Components;

import org.littlebrother.telegram.messenger.ImageReceiver;

public interface AttachableDrawable {
    void onAttachedToWindow(ImageReceiver parent);
    void onDetachedFromWindow(ImageReceiver parent);
}
