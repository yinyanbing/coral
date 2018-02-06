package org.yyb.coral.core.thread;

import java.util.Date;

/**
 * 统一的coral线程
 * 
 * @author: yybg
 * @date: 2017年10月23日 下午4:20:34
 */
public class CoralThread extends Thread {
    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 运行开始时间
     */
    private Date startDate;

    /**
     * 完成时间
     */
    private Date finishDate;

    public CoralThread(Runnable target, String name) {
        super(target, name);
        setCreateDate();
    }

    /**
     * 设置
     */
    public void setCreateDate() {
        this.createDate = new Date();
    }

    /**
     * 设置线程开始运行时间
     */
    public void setStartDate() {
        this.startDate = new Date();
    }

    /**
     * 设置完成时间
     */
    public void setFinishDate() {
        this.finishDate = new Date();
    }

    /**
     * 获取执行时间
     * 
     * @return
     */
    public long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public void run() {
        setStartDate();
        try {
            super.run();
        } finally {
            setFinishDate();
            // 主动报告？TODO
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getName());
        buffer.append(": ");
        // buffer.append(" Create Date: ");
        buffer.append(createDate);
        buffer.append(" : Running time: ");
        buffer.append(getExecutionTime());
        buffer.append(" Milliseconds.");
        return buffer.toString();
    }
}
