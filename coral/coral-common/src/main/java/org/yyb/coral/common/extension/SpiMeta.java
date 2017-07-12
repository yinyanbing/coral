package org.yyb.coral.common.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展的其它源数据
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午4:18:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface SpiMeta {

	String name() default "";
}
