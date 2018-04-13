package org.yyb.coral.chain;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 平台统一的启动及关闭监听
 * 
 * @author yybg
 */
public class StartProcessListener implements ApplicationListener<ApplicationContextEvent> {
    private static final Logger logger = LoggerFactory.getLogger(StartProcessListener.class);

    public void onApplicationEvent(ApplicationContextEvent contextEvent) {
        String contextName = contextEvent.getApplicationContext().getDisplayName();
        if (contextEvent instanceof ContextRefreshedEvent) {
            if (logger.isInfoEnabled()) {
                logger.info("bean初始化完毕，开始启动流程：" + contextName);
            }
            Map<String, IProcessorEvent> processors = contextEvent.getApplicationContext().getBeansOfType(IProcessorEvent.class);
            for (Map.Entry<String, IProcessorEvent> entry : processors.entrySet()) {
                if (logger.isInfoEnabled()) {
                    logger.info("启动:" + contextName + "." + entry.getKey());
                }
                try {
                    entry.getValue().start();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (logger.isDebugEnabled()) {
                        logger.error("启动:" + contextName + "." + entry.getKey() + "失败。");
                        logger.error(e.getMessage());
                    }
                }
            }
        } else if (contextEvent instanceof ContextClosedEvent) {
            if (logger.isInfoEnabled()) {
                logger.info("停止流程：" + contextEvent.getApplicationContext().getDisplayName());
            }
            Map<String, IProcessorEvent> processors = contextEvent.getApplicationContext().getBeansOfType(IProcessorEvent.class);
            for (Map.Entry<String, IProcessorEvent> entry : processors.entrySet()) {
                if (logger.isInfoEnabled()) {
                    logger.info("停止:" + contextName + "." + entry.getKey());
                }
                try {
                    entry.getValue().stop();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (logger.isDebugEnabled()) {
                        logger.error("停止:" + contextName + "." + entry.getKey() + "失败。");
                        logger.error(e.getMessage());
                    }
                }
            }
        }
    }
}