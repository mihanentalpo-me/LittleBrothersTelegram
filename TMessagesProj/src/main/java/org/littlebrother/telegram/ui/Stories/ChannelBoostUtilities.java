package org.littlebrother.telegram.ui.Stories;

import android.text.TextUtils;

import org.littlebrother.telegram.messenger.ChatObject;
import org.littlebrother.telegram.messenger.MessagesController;
import org.littlebrother.telegram.tgnet.TLRPC;

public class ChannelBoostUtilities {
    public static String createLink(int currentAccount, long dialogId) {
        TLRPC.Chat chat = MessagesController.getInstance(currentAccount).getChat(-dialogId);
        String username = ChatObject.getPublicUsername(chat);
        if (!TextUtils.isEmpty(username)) {
            return "https://t.me/boost/" + ChatObject.getPublicUsername(chat);
        } else {
            return "https://t.me/boost/?c=" + -dialogId;
        }
    }
}
