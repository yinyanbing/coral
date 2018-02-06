package org.yyb.coral.core.eventbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.common.extension.Activation;
import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.CompomentInfo;
import org.yyb.coral.core.CoralApplication;
import org.yyb.coral.core.CoralCompomentsEnum;
import org.yyb.coral.core.CoralCoreConstans;
import org.yyb.coral.core.IStartStop;
import org.yyb.coral.core.eventbus.provider.ILocalSubPubProvider;
import org.yyb.coral.core.eventbus.provider.IRemoteSubPubProvider;

/**
 * 事件总线启动停止实现，主要用于启动监听线程及销毁监听等
 * 
 * @author: yybg
 * @date: 2017年10月17日 下午3:23:32
 */
@SpiMeta(name = "eventBus")
@Activation(key = "eventBus")
public class EventBusStartStopImpl implements IStartStop {
    private static final Logger logger = LoggerFactory.getLogger(EventBusStartStopImpl.class);

    private static final String EVENTBUS_NAME = "eventBus";

    @Override
    public String getStartInfo() {
        return EVENTBUS_NAME;
    }

    @Override
    public String getStopInfo() {
        return EVENTBUS_NAME;
    }

    @Override
    public String getName() {
        return EVENTBUS_NAME;
    }

    @Override
    public void start() {
        // 事件总线远程实现provider初始化
        CompomentInfo compomentInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_REMOTE.getName());
        if (compomentInfo != null) {
            IRemoteSubPubProvider remoteSubPub = ExtensionLoader.getExtensionLoader(IRemoteSubPubProvider.class)
                    .getExtension(compomentInfo.getProviderName());
            remoteSubPub.start();
        } else {
            logger.warn(BaseUtils.getLogText("eventBus can not find default remote provider!"));
        }
        //// 事件总线本地实现provider初始化
        CompomentInfo compometLocalInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_LOCAL.getName());
        if (compometLocalInfo != null) {
            ILocalSubPubProvider localSubPub = ExtensionLoader.getExtensionLoader(ILocalSubPubProvider.class)
                    .getExtension(compometLocalInfo.getProviderName());
            localSubPub.start();
        } else {
            logger.warn(BaseUtils.getLogText("eventBus can not find default local provider!"));
        }
    }

    @Override
    public void stop() {
        // 事件总线远程实现provider初始化
        CompomentInfo compomentInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_REMOTE.getName());
        if (compomentInfo != null) {
            IRemoteSubPubProvider remoteSubPub = ExtensionLoader.getExtensionLoader(IRemoteSubPubProvider.class)
                    .getExtension(compomentInfo.getProviderName());
            remoteSubPub.stop();
        } else {
            logger.warn(BaseUtils.getLogText("eventBus can not find default remote provider!"));
        }
        //// 事件总线本地实现provider初始化
        CompomentInfo compometLocalInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_LOCAL.getName());
        if (compometLocalInfo != null) {
            ILocalSubPubProvider localSubPub = ExtensionLoader.getExtensionLoader(ILocalSubPubProvider.class)
                    .getExtension(compometLocalInfo.getProviderName());
            localSubPub.stop();
        } else {
            logger.warn(BaseUtils.getLogText("eventBus can not find default local provider!"));
        }
    }

    @Override
    public int getOrder() {
        return CoralCoreConstans.ORDERED_EVENTBUS;
    }

}
