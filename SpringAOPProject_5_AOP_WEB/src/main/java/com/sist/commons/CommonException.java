package com.sist.commons;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// Spring -> 반복 코딩(x)

// 모든 컨크롤어 예외처리 = 전역
@ControllerAdvice // 컨트롤러의 에러를 한번에 처리
public class CommonException {
//	공통으로 쓰는 예외처리 -> 공통 try~catch
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("======= RuntimeException ======");
		ex.printStackTrace();
		System.out.println("===============================");
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("======= SQLException ======");
		ex.printStackTrace();
		System.out.println("===============================");
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("======= RuntimeException ======");
		ex.printStackTrace();
		System.out.println("===============================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("======= Exception ======");
		ex.printStackTrace();
		System.out.println("===============================");
	}
}
