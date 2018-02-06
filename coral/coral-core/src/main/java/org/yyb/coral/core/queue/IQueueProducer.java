package org.yyb.coral.core.queue;

/**
 * 队列生成者
 * 
 * @author yybg
 */
public interface IQueueProducer {

    public void produce(QueueModel queueModel, Object obj);
}
