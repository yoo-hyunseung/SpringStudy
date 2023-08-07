package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	// 목록
//		@Select("select no,subject,name,to_char(regdate,'yyyy-mm-dd')as dbday,hit,num "
//				+ "from (select no,subject,name,regdate,hit,rownum as num "
//				+ "from (select no,subject,name,regdate,hit "
//				+ "from springreplyboard order by group_id desc, group_step asc)) "
//				+ "where num between #{start} and #{end}")
		public List<BoardVO> boardListData(Map map){
			return mapper.boardListData(map);
		}
		public int boardTotalPage() {
			return mapper.boardTotalPage();
		}
//		// 추가
//		@Insert("Insert into springreplyboard(no,name,subject,content,pwd,group_id) "
//				+ "values(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
//				+ "(select NVL(max(group_id)+1,1) from springreplyboard))")
		public void boardInsert(BoardVO vo) {
			mapper.boardInsert(vo);
		}

		// 상세보기
//		@Update("update springreplyboard set hit=hit+1 where no=#{no}")
//		public void htiIncrement(int no) {
//			
//		}
//		@Select("select no,subject,name,to_char(regdate,'yyyy-mm-dd')as dbday,hit, "
//				+ "from springreplyboard "
//				+ "where no=#{no}")
		public BoardVO boardDetailData(int no) {
			mapper.hitIncrement(no);
			return mapper.boardDetailData(no);
		}
		
		// 답변 트랜잭션 (스프링 : AOP)
//		@Select("select group_id,group_step,group_tab from springreplyboard where no=#{no}")
//		public BoardVO boardParentInfoData(int no);
//		@Update("update springreplyboard set "
//				+ "group_step=group_step+1 "
//				+ "where group_id=#{group_id} and group_step>#{group_step}")
//		public void boardGroupStepIncrement(BoardVO vo);
//		@Insert("Insert into springreplyboard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
//				+ "values(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
//		/		+ "#id{group_id},#{group_step},#{group_tab},#{root}")
		@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		public void boardReplyInsert(BoardVO vo,int root) {
			BoardVO pvo=mapper.boardParentInfoData(root);
			mapper.boardGroupStepIncrement(pvo);
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(root);
			mapper.boardReplyInsert(vo);
			mapper.boardDepthIncrement(root);
		}
		
		
//		@Update("update springreplyboard set depth=depth+1 "
//				+ "where no=#{no}")
//		public void boardDepthIncrement(int no);
		
		public BoardVO boardUpdateData(int no) {
			return mapper.boardDetailData(no);
		}
//		@Select("select pwd springreplyboard where no=#{no}")
//		public String boardGetPassword(int no);
//		@Update("update springreplyboard set name=#{name},subject=#{subject}, content=#{content}"
//				+ "where no=#{no}")
		public boolean boardUpdate(BoardVO vo) {
			boolean bCheck =false;
			String db_pwd = mapper.boardGetPassword(vo.getNo());
			if(db_pwd.equals(vo.getPwd())) {
				bCheck = true;
				mapper.boardUpdate(vo);
			}
			return bCheck;
		}
		
		
		/*@Select("select root,depth from springreplyBoard "
				+ "where no=#{no}")
		public BoardVO boardInfoData(int no);
		@Update("update springreplyboard set "
				+ "subject='rhksflwkrk tkrwpgks rptlanfdlqslek.',content='rhksflwkrk tkrwpgks rptlanfdlqslek.'"
				+ "where no=#{no}")
		public void boardSubjectUpdate(int no);
		
		@Delete("delete from springreplyboard where no=#{no}")
		public void boardDelete(int no);
		@Update("Update springreplyboard set depth=depth-1 where no=#{no}")
		public void boardDepthDecrement(int no);*/
		/*
		 *  1. 트랜잭션 
		 *      = 여러개의 SQL문장(DML=insert,update,delete)를 하나의 그룹으로 묶어서 처리하는 단위
		 *      = 물리적으로는 여러개의 SQL문장 수행 -> 논리적으로 하나의 작업으로 인식
		 *      = 일괄처리
		 *  2. TransactionManager를 등록 application-datasource.xml 
		 *  
		 *   
		 *  3. propagation = Propagation.REQUIRED (전파)
		 *                   Propagation.REQUIRED : 기본형 default
		 *                     트랜잭션을 사용중이면 다음에 재사용이 가능하게 만든다.
		 *                     시작할 때만 한번 생성
		 *                   public void delete(){
		 *                   
		 *                   }
		 *                     한번 사용하면 다음에 자동으로 셋팅
		 *                   Propagation.REQUIRED_NEW : 무조건 새롭게 생성
		 *                   Propagation.NAVER : 트랜잭션을 무조건 설정
		 */
		@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
		public boolean boardDelete(int no,String pwd) {
			boolean bCheck = false;
			String db_pwd=mapper.boardGetPassword(no);
			if(db_pwd.equals(pwd)) {
				bCheck = true;
				//삭제할 수 있는지 없는지 확인 -> depth
				BoardVO vo = mapper.boardInfoData(no);
				if(vo.getDept()==0) { 
					// 대 댓게시글이 없으면 바로 삭제
					mapper.boardDelete(no);
				} else {
					mapper.boardSubjectUpdate(no);
				}
				mapper.boardDepthDecrement(vo.getRoot());
			} else {
				bCheck = false;
			}
			return bCheck;
		}
		/*
		 * 	@Select({
		"<script>"
		+ "select no,subject,name,to_char(regdate,'yyyy-mm-dd') as dbday, hit "
		+ "where "
		+ "<trim prefixOverrides=\"OR\">"
		+ "<foreach collection=\"fsArr\" items=\"fd\""
		+ "<trim prefix=\"OR\">"
		+ "<choose>"
		+ "<when test=\"fd=='N'.toString()\">"
		+ "name like '%'||#{ss}||'%'"
		+ "</when>"
		+ "<when test=\"fd=='S'.toString()\">"
		+ "subject like '%'||#{ss}||'%'"
		+ "</when>"
		+ "<when test=\"fd=='C'.toString()\">"
		+ "content like '%'||#{ss}||'%'"
		+ "</when>"
		+ "</choose>"
		+ "</trim>"
		+ "</foreach>"
		+ "</trim>"
		+ "</script>"
	})*/
	public List<BoardVO> boardFindData(Map map){
		return mapper.boardFindData(map);
	}
		 
}
