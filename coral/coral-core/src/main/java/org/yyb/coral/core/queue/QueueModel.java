package org.yyb.coral.core.queue;

import java.io.Serializable;

import org.yyb.coral.common.utils.BaseUtils;

/**
 * 队列相关信息model
 * 
 * @author yybg
 */
public class QueueModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7214282973099943207L;

    public QueueModel(String group, String name, String queueType, int cosumeThreads) {
        super();
        this.group = group;
        this.name = name;
        if (cosumeThreads >= 1) {
            this.cosumeThreads = cosumeThreads;
        }
        this.queueType = QueueTypeEnum.getQueueTypeEnum(queueType).getCode();
    }

    private String id = BaseUtils.getUUIDText();

    /**
     * 分组，队列分组
     */
    private String group;

    /**
     * 队列名
     */
    private String name;

    /**
     * 消费线程数，默认1，表示此队列初始化一个线程消费
     */
    private int cosumeThreads = 1;

    /**
     * 队列类型，默认redis队列实现
     */
    private String queueType = QueueTypeEnum.REDIS.getCode();

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public int getCosumeThreads() {
        return cosumeThreads;
    }

    public String getQueueType() {
        return queueType;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "QueueModel [id=" + id + ", group=" + group + ", name=" + name + ", cosumeThreads=" + cosumeThreads + ", queueType=" + queueType + "]";
    }

}
