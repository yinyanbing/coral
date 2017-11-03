package org.yyb.coral.core.eventbus.listener;

import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.core.CompomentInfo;
import org.yyb.coral.core.CoralApplication;
import org.yyb.coral.core.CoralCompomentsEnum;
import org.yyb.coral.core.eventbus.provider.ILocalSubPubProvider;

/**
 * 本地监听订阅管理，用于注册监听到监听提供者中
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:58:03
 */
public class LocalListenerManager {
    public static void regesterEventListener(IEventListener eventListener) {
        if (eventListener != null) {
            CompomentInfo compomentInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_LOCAL.getName());
            if (compomentInfo != null) {
                ILocalSubPubProvider localSubPubProvider = ExtensionLoader.getExtensionLoader(ILocalSubPubProvider.class)
                        .getExtension(compomentInfo.getProviderName());
                if (localSubPubProvider != null) {
                    localSubPubProvider.registerListener(eventListener);
                }
            } else {
                // logger.warn(BaseUtils.getLogText("Eventbus can not find
                // ILocalSubPubProvider!"));
            }
        }
    }

    private LocalListenerManager() {
    }
}
