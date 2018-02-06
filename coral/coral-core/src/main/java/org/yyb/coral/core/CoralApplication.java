package org.yyb.coral.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.common.exception.CoralRuntimeException;
import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.CollectionUtils;

/**
 * CORAL启动初始化及设置
 * 
 * @author: yybg
 * @date: 2017年7月14日 下午4:57:50
 */
public class CoralApplication {
    private static final Logger logger = LoggerFactory.getLogger(CoralApplication.class);

    private static CoralConfig config = null;

    public static synchronized void start(CoralConfig configin) {
        config = configin;
        if (configin == null) {
            config = new CoralConfig();
        }
        // 初始化OCRAL所有组件
        List<IStartStop> startStops = ExtensionLoader.getExtensionLoader(IStartStop.class).getExtensions(null);
        if (!CollectionUtils.isEmpty(startStops)) {
            // 按照顺序初始化
            Collections.sort(startStops, new OrderedAscComparator());
            for (IStartStop startStop : startStops) {
                long l1 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "OrderedInitManager init className=【%s】,name=【%s】,order=【%s】,startInfo=【%s】  is begin!**************************",
                                startStop.getClass().getName(),
                                startStop.getName(),
                                startStop.getOrder(),
                                startStop.getStartInfo()));
                try {
                    startStop.start();
                } catch (Exception e) {
                    logger.error(
                            BaseUtils.getLogText(
                                    "OrderedInitManager init is error! className=【%s】,name=【%s】,order=【%s】,startInfo=【s%】**************************",
                                    startStop.getClass().getName(),
                                    startStop.getName(),
                                    startStop.getOrder(),
                                    startStop.getStartInfo()),
                            e);
                }
                long l2 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "OrderedInitManager init className=【%s】,name=【%s】,order=【%s】,startInfo=【%s】  is over,cost=【%s】ms!**************************",
                                startStop.getClass().getName(),
                                startStop.getName(),
                                startStop.getOrder(),
                                startStop.getStartInfo(),
                                (l2 - l1)));
            }
        }
    }

    public static synchronized void stop() {
        List<IStartStop> startStops = ExtensionLoader.getExtensionLoader(IStartStop.class).getExtensions(null);
        if (!CollectionUtils.isEmpty(startStops)) {
            // 按照逆序停止
            Collections.sort(startStops, new OrderedDescComparator());
            for (IStartStop startStop : startStops) {
                long l1 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "OrderedInitManager init className=【%s】,name=【%s】,order=【%s】,stopInfo=【%s】  is begin!**************************",
                                startStop.getClass().getName(),
                                startStop.getName(),
                                startStop.getOrder(),
                                startStop.getStopInfo()));
                try {
                    startStop.stop();
                } catch (Exception e) {
                    logger.error(
                            BaseUtils.getLogText(
                                    "OrderedInitManager init is error! className=【%s】,name=【%s】**************************",
                                    startStop.getClass().getName(),
                                    startStop.getName(),
                                    startStop.getOrder(),
                                    startStop.getStopInfo()),
                            e);
                }
                long l2 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "OrderedInitManager init className=【%s】,name=【%s】,order=【%s】,stopInfo=【%s】  is over,cost=【%s】ms!**************************",
                                startStop.getClass().getName(),
                                startStop.getName(),
                                startStop.getOrder(),
                                startStop.getStopInfo(),
                                (l2 - l1)));
            }
        }
    }

    /**
     * 获取组件配置信息
     * 
     * @param name
     * @return
     */
    public static CompomentInfo getCompomentInfo(String name) {
        if (config == null) {
            throw new CoralRuntimeException("Coral application must start and init config!");
        }
        return config.getCompoment(name);
    }

    private static class OrderedAscComparator implements Comparator<IOrdered> {

        @Override
        public int compare(IOrdered o1, IOrdered o2) {
            return o1.getOrder() - o2.getOrder();
        }

    }

    private static class OrderedDescComparator implements Comparator<IOrdered> {

        @Override
        public int compare(IOrdered o1, IOrdered o2) {
            return o2.getOrder() - o1.getOrder();
        }

    }

    private CoralApplication() {
    }
}
