package org.yyb.coral.chain.t1;

import org.yyb.coral.chain.MessageContext;
import org.yyb.coral.chain.basic.UnsettledMessageProcessor;

public class Test01Processor extends UnsettledMessageProcessor {
    private String conParam;

    private String fieParam;

    public Test01Processor(String conParam) {
        super();
        this.conParam = conParam;
    }

    public String getFieParam() {
        return fieParam;
    }

    public void setFieParam(String fieParam) {
        this.fieParam = fieParam;
    }

    @Override
    protected boolean doCanProcess(MessageContext context) throws Exception {
        Object obj = context.getContent();
        if (obj != null) {
            String objStr = obj.toString();
            System.out.println("in data:" + objStr);
            objStr = objStr + "-->Test01Process【" + conParam + "-" + fieParam + "】";
            context.setContent(objStr);
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Test01Processor start");
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("Test01Processor stop");
    }

}
