package org.yyb.coral.chain;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yyb.coral.chain.chains.ChainProxyMessageProcessor;

public class ProcessorTest {
    @Test
    public void test01() {
        ClassPathXmlApplicationContext applicationContext = null;
        try {
            applicationContext = new ClassPathXmlApplicationContext("classpath*:coral-chain*.xml");
            ChainProxyMessageProcessor proxyMessageProcessor = (ChainProxyMessageProcessor) applicationContext.getBean("testProcessorManager");
            if (proxyMessageProcessor != null) {
                MessageContext context = new MessageContext();
                context.setContent("startData--");
                proxyMessageProcessor.process(context);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (applicationContext != null) {
                applicationContext.close();
            }
        }

    }
}
