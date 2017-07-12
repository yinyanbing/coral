package org.yyb.coral.common.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SPI扩展注解，接口必须存在，如果通过扩展获取时
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午4:18:17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Spi {

	Scope scope() default Scope.PROTOTYPE;

}
