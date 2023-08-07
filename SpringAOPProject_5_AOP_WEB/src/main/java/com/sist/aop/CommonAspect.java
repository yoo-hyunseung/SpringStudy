package com.sist.aop;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.RequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.vo.*;
import com.sist.service.*;

@Aspect
@Component // 메모리할당
public class CommonAspect {
	@Autowired
	private FoodService service;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerDataSend() {
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder
				.getRequestAttributes()).getRequest();
		List<SeoulVO> seoulList = service.seoulTop7();
		List<FoodVO> foodList = service.foodTop7();
		
		request.setAttribute("seoulList", seoulList);
		request.setAttribute("foodList", foodList);
	}
	
	@Around("execution(* com.sist.web.*Controller.*(..))")
	public Object logData(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		long start = System.currentTimeMillis();
		System.out.println(jp.getSignature().getName()+": 메소드에 진입");
		obj = jp.proceed();
		System.out.println(jp.getSignature().getName()+": 메소드 수행 종료");
		long end = System.currentTimeMillis();
		System.out.println("수행 시간 : "+(start+end));
		return obj;
	}
}
