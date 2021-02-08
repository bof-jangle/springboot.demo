package xyz.jangle.springboot.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * 方面编程
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月8日 上午11:39:01
 * 
 */
// 1、这个类称之为切面
@Aspect
@Component
public class MyAopDemo {

	// 2、此处定义切入点（接入点的集合）
	@Pointcut("execution (public * xyz.jangle.springboot.demo.service..*(..))")
	public void servicePointcut() {
	}

	// 3、此处定义通知
	@Around("servicePointcut()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object proceed = null;
		try {
			proceed = joinPoint.proceed();
			
			System.out.println("around after 1111");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proceed;
	}

}
