package org.yyb.coral.core.businessinit;

import org.yyb.coral.common.extension.Activation;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.core.CoralCoreConstans;
import org.yyb.coral.core.IStartStop;

/**
 * 事件总线启动停止实现，主要用于启动监听线程及销毁监听等
 * 
 * @author: yybg
 * @date: 2017年10月17日 下午3:23:32
 */
@SpiMeta(name = "businessInit")
@Activation(key = "businessInit")
public class BusinessInitStartStopImpl implements IStartStop {
    private static final String BUSINESSINIT_NAME = "businessInit";

    @Override
    public String getStartInfo() {
        return BUSINESSINIT_NAME;
    }

    @Override
    public String getStopInfo() {
        return BUSINESSINIT_NAME;
    }

    @Override
    public String getName() {
        return BUSINESSINIT_NAME;
    }

    @Override
    public void start() {
        BusinessInitManager.instance().initRegisteredBeans();
    }

    @Override
    public void stop() {
        BusinessInitManager.instance().destroyRegisteredBeans();
    }

    @Override
    public int getOrder() {
        return CoralCoreConstans.ORDERED_BUSINESS_INIT;
    }

}
