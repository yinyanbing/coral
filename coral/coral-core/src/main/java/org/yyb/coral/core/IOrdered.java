package org.yyb.coral.core;

/**
 * 支持排序
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:47:56
 */
public interface IOrdered {
    /**
     * Useful constant for the highest precedence value.
     * 
     * @see java.lang.Integer#MIN_VALUE
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * Useful constant for the lowest precedence value.
     * 
     * @see java.lang.Integer#MAX_VALUE
     */
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    /**
     * Get the order value of this object.
     * <p>
     * Higher values are interpreted as lower priority. As a consequence, the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>
     * Same order values will result in arbitrary sort positions for the affected objects.
     * 
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    int getOrder();
}
