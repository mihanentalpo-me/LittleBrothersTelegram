package org.littlebrother.telegram.ui.Stories.recorder;

import org.littlebrother.telegram.messenger.AndroidUtilities;
import org.littlebrother.telegram.messenger.Utilities;

public class FfmpegAudioWaveformLoader {

    private volatile boolean running = true;

    private native void init(String path, int count);

    public FfmpegAudioWaveformLoader(String path, int count, Utilities.Callback2<short[], Integer> onChunkReceived) {
        this.onChunkReceived = onChunkReceived;
        Utilities.phoneBookQueue.postRunnable(() -> {
            init(path, count);
        });
    }

    private Utilities.Callback2<short[], Integer> onChunkReceived;
    private void receiveChunk(short[] data, int len) {
        AndroidUtilities.runOnUIThread(() -> {
            onChunkReceived.run(data, len);
        });
    }

    public void destroy() {
        Utilities.phoneBookQueue.postRunnable(() -> {
            running = false;
        });
    }
}
