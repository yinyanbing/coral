package org.yyb.coral.common.extension;

import java.util.Comparator;

/**
 * Priority comparator
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午4:17:26
 * @param <T>
 */
public class ActivationComparator<T> implements Comparator<T> {

	/**
	 * sequence 大的排在后面,如果没有设置sequence的排到最前面
	 */
	@Override
	public int compare(T o1, T o2) {
		Activation p1 = o1.getClass().getAnnotation(Activation.class);
		Activation p2 = o2.getClass().getAnnotation(Activation.class);
		if (p1 == null) {
			return 1;
		} else if (p2 == null) {
			return -1;
		} else {
			return p1.sequence() - p2.sequence();
		}
	}

}
