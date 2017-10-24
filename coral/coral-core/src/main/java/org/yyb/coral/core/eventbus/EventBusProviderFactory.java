package org.yyb.coral.core.eventbus;

import org.yyb.coral.Constants;
import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.core.eventbus.publisher.IEventPublishable;

/**
 * redis客户端集成获取工厂
 * 
 * @author: yybg
 * @date: 2017年10月12日 下午5:08:20
 *
 */
public class EventBusProviderFactory {
	private EventBusProviderFactory() {
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public static IEventPublishable getEventBusProvider() {
		IEventPublishable eventPublishable = ExtensionLoader.getExtensionLoader(IEventPublishable.class)
				.getExtension(Constants.CORAL_DEFAULT);
		return eventPublishable;
	}
}
