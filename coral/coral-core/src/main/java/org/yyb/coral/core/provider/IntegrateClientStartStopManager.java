package org.yyb.coral.core.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.CollectionUtils;
import org.yyb.coral.core.IStartStop;

/**
 * 三方集成客户端注册初始化BEAN顺序，并依次执行,销毁逆序
 * 
 * @author: yybg
 * @date: 2017年10月20日 上午10:42:48
 */
public class IntegrateClientStartStopManager {

    private static final Logger logger = LoggerFactory.getLogger(IntegrateClientStartStopManager.class);

    private static IntegrateClientStartStopManager INSTANCE = new IntegrateClientStartStopManager();

    private List<IStartStop> businessInits = new CopyOnWriteArrayList<IStartStop>();

    private IntegrateClientStartStopManager() {
    }

    public static IntegrateClientStartStopManager instance() {
        return INSTANCE;
    }

    /**
     * 注册初始化顺序bean
     * 
     * @param orderInit
     */
    public void registerBean(IStartStop orderInit) {
        if (orderInit != null) {
            businessInits.add(orderInit);
        }
    }

    /**
     * 顺序执行bean的初始化方法，根据顺序,一般三方客户端集成不需要初始化，调用时及时初始化
     */
    public void initRegisteredBeans() {
        if (!CollectionUtils.isEmpty(businessInits)) {
            Collections.sort(businessInits, new OrderedInitComparator());

            for (IStartStop orderedInit : businessInits) {
                long l1 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "ThirdPartyClientStartStopManager init className=【%s】,name=【%s】,order=【%s】  is begin!**************************",
                                orderedInit.getClass().getName(),
                                orderedInit.getName(),
                                orderedInit.getOrder()));
                try {
                    orderedInit.start();
                } catch (Exception e) {
                    logger.error(
                            BaseUtils.getLogText(
                                    "ThirdPartyClientStartStopManager init is error! className=【%s】,name=【%s】,order=【%s】 ",
                                    orderedInit.getClass().getName(),
                                    orderedInit.getName(),
                                    orderedInit.getOrder()),
                            e);
                }
                long l2 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "ThirdPartyClientStartStopManager init className=【%s】,name=【%s】,order=【%s】   is over,cost=【%s】ms!**************************",
                                orderedInit.getClass().getName(),
                                orderedInit.getName(),
                                orderedInit.getOrder(),
                                (l2 - l1)));
            }
        }
    }

    /**
     * 顺序执行bean的初始化方法，根据顺序.三方客户端集成初始化后需要关闭
     */
    public void destroyRegisteredBeans() {
        if (!CollectionUtils.isEmpty(businessInits)) {
            Collections.sort(businessInits, new OrderedDestroyComparator());

            for (IStartStop orderedInit : businessInits) {
                long l1 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "ThirdPartyClientStartStopManager destroy className=【%s】,name=【%s】,order=【%s】  is begin!**************************",
                                orderedInit.getClass().getName(),
                                orderedInit.getName(),
                                orderedInit.getOrder()));
                try {
                    orderedInit.stop();
                } catch (Exception e) {
                    logger.error(
                            BaseUtils.getLogText(
                                    "ThirdPartyClientStartStopManager destroy is error! className=【%s】,name=【%s】,order=【%s】 ",
                                    orderedInit.getClass().getName(),
                                    orderedInit.getName(),
                                    orderedInit.getOrder()),
                            e);
                }
                long l2 = System.currentTimeMillis();
                logger.info(
                        BaseUtils.getLogText(
                                "ThirdPartyClientStartStopManager destroy className=【%s】,name=【%s】,order=【%s】   is over,cost=【%s】ms!**************************",
                                orderedInit.getClass().getName(),
                                orderedInit.getName(),
                                orderedInit.getOrder(),
                                (l2 - l1)));
            }
        }
    }

    /**
     * 顺序排序比较
     * 
     * @author: yybg
     * @date: 2017年10月20日 上午10:40:54
     */
    private static class OrderedInitComparator implements Comparator<IStartStop> {

        @Override
        public int compare(IStartStop o1, IStartStop o2) {
            return o1.getOrder() - o2.getOrder();
        }
    }

    /**
     * 逆序排序比较
     * 
     * @author: yybg
     * @date: 2017年10月20日 上午10:41:09
     */
    private static class OrderedDestroyComparator implements Comparator<IStartStop> {

        @Override
        public int compare(IStartStop o1, IStartStop o2) {
            return o2.getOrder() - o1.getOrder();
        }
    }

}
