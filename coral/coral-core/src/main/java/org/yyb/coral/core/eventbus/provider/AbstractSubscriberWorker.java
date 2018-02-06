package org.yyb.coral.core.eventbus.provider;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.eventbus.EventBusProviderFactory;
import org.yyb.coral.core.eventbus.IApplicationEvent;

/**
 * 订阅线程WORKER抽象类
 * 
 * @author: yybg
 * @date: 2017年10月16日 下午4:28:29
 */
public abstract class AbstractSubscriberWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSubscriberWorker.class);

    /**
     * 订阅失败后休眠20秒
     */
    private static final long SLEEPTIME = 10000;

    /**
     * 订阅实现名称
     */
    private String name = null;

    /**
     * 订阅是否循环，如redis订阅会当前线程等待，ROCKETMQ则不需要
     */
    private boolean isSubLoop = false;

    /**
     * 重复订阅次数
     */
    private static final AtomicInteger RESUBCOUNT = new AtomicInteger(0);

    public AbstractSubscriberWorker(String name, boolean isSubLoop) {
        this.name = name;
        this.isSubLoop = isSubLoop;
    }

    @Override
    public void run() {
        logger.info(BaseUtils.getLogText("Eventbus subscribe 【%s】 topic 【%s】 is begining!", name, ISubPubProvider.PUBSUB_CHANNEL));
        if (isSubLoop) {
            while (true) {
                try {
                    RESUBCOUNT.incrementAndGet();
                    // 当前线程会等待
                    runInternal();
                } catch (Exception e) {
                    logger.error(BaseUtils.getLogText("Eventbus subscribe 【%s】 topic 【%s】 error!", name, Constants.CORAL_EVENTBUS_CHANNEL), e);
                }
                try {
                    // 休眠
                    Thread.sleep(SLEEPTIME);
                } catch (InterruptedException e) {
                    logger.error(BaseUtils.getLogText("Eventbus subscribe sleep error!"), e);
                }
                logger.error(
                        BaseUtils.getLogText(
                                "Eventbus subscribe 【%s】 topic 【%s】 is fail!Has retry【%s】counts!",
                                name,
                                Constants.CORAL_EVENTBUS_CHANNEL,
                                RESUBCOUNT.intValue()));
            }
        } else {
            runInternal();
        }
    }

    /**
     * 接受消息后处理
     * 
     * @param appEvent
     */
    protected void onMessage(IApplicationEvent appEvent) {
        // 可控制接受指定事件类型
        EventBusProviderFactory.getEventBusProvider().publishLocal(appEvent);
    }

    /**
     * 具体如何订阅指定主题
     */
    protected abstract void runInternal();

}
