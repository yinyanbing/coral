package org.yyb.coral.core.queue;

/**
 * 队列消费者，当消息到达时给指定消费者处理
 * 
 * @author yybg
 */
public interface IQueueConsumer {
    /**
     * 获取消费的队列名
     * 
     * @return
     */
    public boolean canConsume(QueueModel queueModel);

    /**
     * 消费队列数据
     * 
     * @param obj
     */
    public void consume(Object obj);
}
