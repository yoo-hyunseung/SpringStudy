package com.sist.dao;

import com.sist.mapper.*;
import com.sist.vo.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.EmpMapper;

@Repository("dao")
public class EmpDAO{
//	getBean() 대신 쓰는거
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	
	public EmpVO empDetailData(int empno) {
		return mapper.empDetailData(empno);
	}
	
	public List<EmpVO> empFindData(String ename){
		return mapper.empFindData(ename);
	}
}
