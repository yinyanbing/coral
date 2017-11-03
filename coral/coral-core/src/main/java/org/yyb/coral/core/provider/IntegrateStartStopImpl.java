package org.yyb.coral.core.provider;

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
 * 三方集成启动停止实现，主要用于销毁三方客户端池等
 * 
 * @author: yybg
 * @date: 2017年10月17日 下午3:23:32
 */
@SpiMeta(name = "integrate")
@Activation(key = "integrate")
public class IntegrateStartStopImpl implements IStartStop {
    private static final Logger logger = LoggerFactory.getLogger(IntegrateStartStopImpl.class);

    /**
     * 三方集成启动停止名
     */
    private static final String INTEGRATE_NAME = "integrate";

    @Override
    public String getStartInfo() {
        return INTEGRATE_NAME;
    }

    @Override
    public String getStopInfo() {
        return INTEGRATE_NAME;
    }

    @Override
    public String getName() {
        return INTEGRATE_NAME;
    }

    @Override
    public void start() {
        IntegrateClientStartStopManager.instance().initRegisteredBeans();
    }

    @Override
    public void stop() {
        IntegrateClientStartStopManager.instance().destroyRegisteredBeans();
    }

    @Override
    public int getOrder() {
        return CoralCoreConstans.ORDERED_INTEGRATE;
    }

}
