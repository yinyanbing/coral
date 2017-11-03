package org.yyb.coral.core.eventbus.listener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.ICoralName;
import org.yyb.coral.core.eventbus.IApplicationEvent;

/**
 * 抽象事件接受监听,具体业务监听继承此抽象类,之后可通过spring配置、spring组件注解或直接new对象即可注册本监听
 * 
 * @ClassName: AbstractEventListener
 * @author: yybg
 * @date: 2016年12月20日 下午1:14:24
 * @param <E>
 */
public abstract class AbstractEventListener<E extends IApplicationEvent> implements IEventListener<E>, ICoralName {

    private static final Logger logger = LoggerFactory.getLogger(AbstractEventListener.class);

    /**
     * 监听器名字
     */
    private String name = null;

    public AbstractEventListener(String name) {
        this.name = name;
        LocalListenerManager.regesterEventListener(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void onAppEvent(IApplicationEvent event) {
        // 如果是发送指定应用的事件，只有当前应用和目标应用相同才能执行
        if (StringUtils.isNotBlank(event.getToApplicationId()) && !Constants.SP_APPLICATIONID.equals(event.getToApplicationId())) {
            return;
        }
        boolean hasError = false;
        try {
            onAppEventInternal(event);
        } catch (Exception e) {
            hasError = true;
            logger.error(BaseUtils.getLogText("Eventbus subscribe listener execcute error!"), e);
        } finally {
            reportRemoteEventStatus(event, hasError);
        }
    }

    /**
     * 报告当前事件状态到redis，为后续查看事件在各个应用的执行结果提供数据依据
     * 
     * @param event
     * @param hasError
     */
    private void reportRemoteEventStatus(final IApplicationEvent event, final boolean hasError) {
    }

    /**
     * 处理事件内部实现，已经过滤不需要处理的一般事件
     * 
     * @param event
     *            具体事件
     */
    protected abstract void onAppEventInternal(IApplicationEvent event);

}
