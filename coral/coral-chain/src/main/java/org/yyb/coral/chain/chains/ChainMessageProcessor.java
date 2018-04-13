package org.yyb.coral.chain.chains;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.yyb.coral.chain.BasicMessageProcessor;
import org.yyb.coral.chain.IForwardNameAwareProcessor;
import org.yyb.coral.chain.IMessageProcessor;
import org.yyb.coral.chain.MessageContext;

/**
 * 链式消息处理抽象类,可通过spring配置,设置当前的beanName;
 * 
 * <pre>
 * 可理解为定义的某一个流程链模版，主流程链，每一个MessageProcessor为一个处理节点,通过spring的配置组织流程
 * 偏管理、定义、模版
 * </pre>
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午4:10:20
 */
public abstract class ChainMessageProcessor extends BasicMessageProcessor implements IForwardNameAwareProcessor, BeanNameAware {
    private String beanName;

    // 主流程链的默认第一个处理节点名字,可理解为启动节点
    private String defaultName;

    public ChainMessageProcessor(String defaultName) {
        this.defaultName = defaultName;
    }

    /**
     * 获取处理器，根据名字
     * 
     * @param name
     * @return
     * @throws Exception
     */
    protected abstract IMessageProcessor doGetProcessor(String name) throws Exception;

    /**
     * 链式调用开始，以配置的默认链式开头开始调用
     */
    public final String process(MessageContext context) throws Exception {
        // 由默认节点名称开始执行流程链
        return doProcess(defaultName, context);
    }

    /**
     * process重载方法, 增加name参数的传入.
     * 
     * @param name
     * @param context
     * @return
     * @throws Exception
     *             缪云海, 2013.05.13
     */
    public final String process(String name, MessageContext context) throws Exception {
        return doProcess(name, context);
    }

    /**
     * 最主要方法，定义了链的调用顺序、方式
     * 
     * @param name
     * @param context
     * @return
     * @throws Exception
     */
    protected final String doProcess(String name, MessageContext context) throws Exception {
        while (StringUtils.isNotBlank(name)) {
            System.out.println("&&& STEPPING begin &&& " + name);
            IMessageProcessor processor = doGetProcessor(name);
            System.out.println("&&& STEPPING end   &&& " + name);
            name = processor.process(context);
        }
        return getForwardName();
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
