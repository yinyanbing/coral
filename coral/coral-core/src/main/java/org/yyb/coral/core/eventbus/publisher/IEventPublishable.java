package org.yyb.coral.core.eventbus.publisher;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;
import org.yyb.coral.core.eventbus.IApplicationEvent;

/**
 * 事件发布
 * 
 * @ClassName: EventPublishable
 * @author: yybg
 * @date: 2016年12月20日 上午9:57:27
 */
@Spi(scope = Scope.SINGLETON)
public interface IEventPublishable {
    /**
     * 事件发布，根据事件标识，remote OR local
     * 
     * @param event
     * @return
     */
    boolean publish(IApplicationEvent event);

    /**
     * 发布本地事件
     * 
     * @param event
     * @return
     */
    boolean publishLocal(IApplicationEvent event);
}
