package org.yyb.coral.core.eventbus.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.CompomentInfo;
import org.yyb.coral.core.CoralApplication;
import org.yyb.coral.core.CoralCompomentsEnum;
import org.yyb.coral.core.eventbus.AppIdentificationEventEnum;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.eventbus.LocalApplicationEventImpl;
import org.yyb.coral.core.eventbus.RemoteApplicationEventImpl;
import org.yyb.coral.core.eventbus.provider.ILocalSubPubProvider;
import org.yyb.coral.core.eventbus.provider.IRemoteSubPubProvider;

/**
 * 默认的时间发布PIS实现，发布事件入口，决定本地或远程发布事件
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:02:29
 */
@SpiMeta(name = "default")
public class DefaultEventPublishableDefaultImpl implements IEventPublishable {
    private static final Logger logger = LoggerFactory.getLogger(DefaultEventPublishableDefaultImpl.class);

    @Override
    public boolean publish(IApplicationEvent event) {
        if (event == null) {
            return false;
        }
        // 后续可根据配置的事件类型及本地远程发送来发送消息，即消息可以通过配置来本地或远程发送 TODO
        if (event instanceof LocalApplicationEventImpl
                || AppIdentificationEventEnum.LOCAL == AppIdentificationEventEnum.getInitType(event.getEventIdentification())) {
            CompomentInfo compomentInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_LOCAL.getName());
            if (compomentInfo != null) {
                ILocalSubPubProvider localSubPubProvider = ExtensionLoader.getExtensionLoader(ILocalSubPubProvider.class)
                        .getExtension(compomentInfo.getProviderName());
                if (localSubPubProvider != null) {
                    return localSubPubProvider.publishEvent(event);
                }
                logger.warn(BaseUtils.getLogText("Eventbus can not find ILocalSubPubProvider!"));
                return false;
            } else {
                logger.warn(BaseUtils.getLogText("Eventbus can not find ILocalSubPubProvider!"));
                return false;
            }
        }
        if (event instanceof RemoteApplicationEventImpl
                || AppIdentificationEventEnum.REMOTE == AppIdentificationEventEnum.getInitType(event.getEventIdentification())) {
            CompomentInfo compomentInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_REMOTE.getName());
            if (compomentInfo != null) {
                IRemoteSubPubProvider remoteSubPubProvider = ExtensionLoader.getExtensionLoader(IRemoteSubPubProvider.class)
                        .getExtension(compomentInfo.getProviderName());
                if (remoteSubPubProvider != null) {
                    return remoteSubPubProvider.publishEvent(event);
                }
                logger.warn(BaseUtils.getLogText("Eventbus can not find IRemoteSubPubProvider!"));
                return false;
            } else {
                logger.warn(BaseUtils.getLogText("Eventbus can not find IRemoteSubPubProvider!"));
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean publishLocal(IApplicationEvent event) {
        CompomentInfo compomentInfo = CoralApplication.getCompomentInfo(CoralCompomentsEnum.EVENTBUS_LOCAL.getName());
        if (compomentInfo != null) {
            ILocalSubPubProvider localSubPubProvider = ExtensionLoader.getExtensionLoader(ILocalSubPubProvider.class)
                    .getExtension(compomentInfo.getProviderName());
            if (localSubPubProvider != null) {
                return localSubPubProvider.publishEvent(event);
            }
            logger.warn(BaseUtils.getLogText("Eventbus can not find ILocalSubPubProvider!"));
            return false;
        } else {
            logger.warn(BaseUtils.getLogText("Eventbus can not find ILocalSubPubProvider!"));
            return false;
        }
    }

}
