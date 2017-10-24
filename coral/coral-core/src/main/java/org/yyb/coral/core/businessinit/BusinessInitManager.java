package org.yyb.coral.core.businessinit;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.CollectionUtils;

/**
 * 注册初始化BEAN顺序，并依次执行,销毁逆序
 * 
 * @author: yybg
 * @date: 2017年10月20日 上午10:42:48
 *
 */
public class BusinessInitManager {

	private static final Logger logger = LoggerFactory.getLogger(BusinessInitManager.class);

	private static BusinessInitManager INSTANCE = new BusinessInitManager();

	private List<IBusinessInit> businessInits = new CopyOnWriteArrayList<IBusinessInit>();

	private BusinessInitManager() {
	}

	public static BusinessInitManager instance() {
		return INSTANCE;
	}

	/**
	 * 注册初始化顺序bean
	 * 
	 * @param orderInit
	 */
	public void registerBean(IBusinessInit orderInit) {
		if (orderInit != null) {
			businessInits.add(orderInit);
		}
	}

	/**
	 * 顺序执行bean的初始化方法，根据顺序
	 */
	public void initRegisteredBeans() {
		if (!CollectionUtils.isEmpty(businessInits)) {
			Collections.sort(businessInits, new OrderedInitComparator());

			for (IBusinessInit orderedInit : businessInits) {
				long l1 = System.currentTimeMillis();
				logger.info(BaseUtils.getLogText(
						"BusinessInitManager init className=【%s】,name=【%s】,order=【%s】  is begin!**************************",
						orderedInit.getClass().getName(), orderedInit.getName(), orderedInit.getOrder()));
				try {
					orderedInit.start();
				} catch (Exception e) {
					logger.error(BaseUtils.getLogText(
							"BusinessInitManager init is error! className=【%s】,name=【%s】,order=【%s】 ",
							orderedInit.getClass().getName(), orderedInit.getName(), orderedInit.getOrder()), e);
				}
				long l2 = System.currentTimeMillis();
				logger.info(BaseUtils.getLogText(
						"BusinessInitManager init className=【%s】,name=【%s】,order=【%s】   is over,cost=【%s】ms!**************************",
						orderedInit.getClass().getName(), orderedInit.getName(), orderedInit.getOrder(), (l2 - l1)));
			}
		}
	}

	/**
	 * 顺序执行bean的初始化方法，根据顺序
	 */
	public void destroyRegisteredBeans() {
		if (!CollectionUtils.isEmpty(businessInits)) {
			Collections.sort(businessInits, new OrderedDestroyComparator());

			for (IBusinessInit orderedInit : businessInits) {
				long l1 = System.currentTimeMillis();
				logger.info(BaseUtils.getLogText(
						"BusinessInitManager destroy className=【%s】,name=【%s】,order=【%s】  is begin!**************************",
						orderedInit.getClass().getName(), orderedInit.getName(), orderedInit.getOrder()));
				try {
					orderedInit.stop();
				} catch (Exception e) {
					logger.error(BaseUtils.getLogText(
							"BusinessInitManager destroy is error! className=【%s】,name=【%s】,order=【%s】 ",
							orderedInit.getClass().getName(), orderedInit.getName(), orderedInit.getOrder()), e);
				}
				long l2 = System.currentTimeMillis();
				logger.info(BaseUtils.getLogText(
						"BusinessInitManager destroy className=【%s】,name=【%s】,order=【%s】   is over,cost=【%s】ms!**************************",
						orderedInit.getClass().getName(), orderedInit.getName(), orderedInit.getOrder(), (l2 - l1)));
			}
		}
	}

	/**
	 * 顺序排序比较
	 * 
	 * @author: yybg
	 * @date: 2017年10月20日 上午10:40:54
	 *
	 */
	private static class OrderedInitComparator implements Comparator<IBusinessInit> {

		@Override
		public int compare(IBusinessInit o1, IBusinessInit o2) {
			return o1.getOrder() - o2.getOrder();
		}
	}

	/**
	 * 逆序排序比较
	 * 
	 * @author: yybg
	 * @date: 2017年10月20日 上午10:41:09
	 *
	 */
	private static class OrderedDestroyComparator implements Comparator<IBusinessInit> {

		@Override
		public int compare(IBusinessInit o1, IBusinessInit o2) {
			return o2.getOrder() - o1.getOrder();
		}
	}

}
