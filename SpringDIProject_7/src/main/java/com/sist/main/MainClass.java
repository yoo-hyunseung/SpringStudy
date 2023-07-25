package com.sist.main;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

// <bean id="mainClass" class="com.sist.main.MainClass"/>
// id 가 있어야 찾는다. id가 없는 경우에는 클래스명이 id가 된다. 첫자만 소문자로 된다.
// EmpDAO => empDAO , 아이디를 설정하려면 어노테이션 뒤에 -> ("mc")
@Component("mc")
public class MainClass {

	@Autowired
	private EmpDAO dao;
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MainClass mc = (MainClass)app.getBean("mc");
		
		List<EmpVO> list = mc.dao.empListData();
		
		for(EmpVO vo : list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()
			+" "+vo.getDbday()+" "+vo.getDbsal());
		}
		System.out.println("===============================");
		Scanner sc = new Scanner(System.in);
		System.out.print("sabun : ");
		int empno = sc.nextInt();
		EmpVO vo = mc.dao.empDetailData(empno);
		System.out.println("======== sawon info ========");
		System.out.println("empno : "+vo.getEmpno());
		System.out.println("ename : "+vo.getEname());
		System.out.println("job : "+vo.getJob());
		System.out.println("dbday : "+vo.getDbday());
		System.out.println("dbsal : "+vo.getDbsal());
		System.out.println("comm : "+vo.getComm());
		System.out.println("======== sawon info ========");
		
		System.out.println("========= sawon find =======");
		System.out.print("input ename : ");
		String ename = sc.next();
		list = mc.dao.empFindData(ename);
		for(EmpVO evo : list) {
			System.out.println(evo.getEmpno()+" "+evo.getEname()+" "+evo.getJob()
			+" "+evo.getDbday()+" "+evo.getDbsal());
		}
		
	}
	


}
