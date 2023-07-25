package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpVO {
//	mybatis	 -> 컬럼명이 중요하다. as 사용
	private int empno,sal,deptno,comm;
	private String ename,job, dbday,dbsal;
	private Date hiredate;
}
