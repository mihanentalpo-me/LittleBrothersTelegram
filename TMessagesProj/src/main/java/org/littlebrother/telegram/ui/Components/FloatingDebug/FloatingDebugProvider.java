package org.littlebrother.telegram.ui.Components.FloatingDebug;

import java.util.List;

public interface FloatingDebugProvider {
    List<FloatingDebugController.DebugItem> onGetDebugItems();
}
