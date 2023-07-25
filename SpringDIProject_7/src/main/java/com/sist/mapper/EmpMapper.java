package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface EmpMapper {
	/*
	 *  <select id="empListData" resultType="EmpVO" parameterType="int">
	 *  	sql ~~
	 *  </select>
	 *  
	 *  1. id -> method 명 -> 메소드명은 중복이없다.
	 *  2. resultType => 리턴형
	 *  3. parameterType => 매개변수
	 *  
	 *  public List<EmpVO> empListData(int empno)
	 */
	
	@Select("Select empno,ename,job,to_char(hiredate,'yyyy-mm-dd') as dbday,"
			+ "to_char(sal,'$999,999') as dbsal, deptno,comm "
			+ "from emp")
	public List<EmpVO> empListData();
	
	@Select("select empno,ename,job,to_char(hiredate,'yyyy-mm-dd') as dbday,"
			+ "to_char(sal,'$999,999') as dbsal,nvl(comm,0) as comm,deptno"
			+ " from emp "
			+ "where empno=#{empno}")
	public EmpVO empDetailData(int empno);
	
	@Select("Select empno,ename,job,to_char(hiredate,'yyyy-mm-dd') as dbday,"
			+ "to_char(sal,'$999,999') as dbsal, deptno,comm "
			+ "from emp where ename like '%'||#{ename}||'%'")
	public List<EmpVO> empFindData(String ename);
}
