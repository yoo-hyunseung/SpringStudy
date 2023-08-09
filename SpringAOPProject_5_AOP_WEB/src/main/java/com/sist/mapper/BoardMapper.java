
package com.sist.mapper;
import java.util.*;


import org.apache.ibatis.annotations.*;

import com.sist.vo.*;
public interface BoardMapper {
	// 목록
	@Select("select no,subject,name,to_char(regdate,'yyyy-mm-dd')as dbday,hit,group_tab,num "
			+ "from (select no,subject,name,regdate,hit,group_tab,rownum as num "
			+ "from (select no,subject,name,regdate,hit,group_tab "
			+ "from springreplyboard order by group_id desc, group_step asc)) "
			+ "where num between #{start} and #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("select ceil(count(*)/10.0) from springreplyBoard")
	public int boardTotalPage();
	
	// 추가
	@Insert("Insert into springreplyboard(no,name,subject,content,pwd,group_id) "
			+ "values(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(select NVL(max(group_id)+1,1) from springreplyboard))")
	public void boardInsert(BoardVO vo);

	// 상세보기
	@Update("update springreplyboard set hit=hit+1 where no=#{no}")
	public void hitIncrement(int no);
	@Select("select no,subject,name,to_char(regdate,'yyyy-mm-dd')as dbday,hit "
			+ "from springreplyboard "
			+ "where no=#{no}")
	public BoardVO boardDetailData(int no);
	
	// 답변 트랜잭션 (스프링 : AOP)
	@Select("select group_id,group_step,group_tab from springreplyboard where no=#{no}")
	public BoardVO boardParentInfoData(int no);
	@Update("update springreplyboard set "
			+ "group_step=group_step+1 "
			+ "where group_id=#{group_id} and group_step>#{group_step}")
	public void boardGroupStepIncrement(BoardVO vo);
	
	@Insert("Insert into springreplyboard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
			+ "values(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "#{group_id},#{group_step},#{group_tab},#{root})")
	public void boardReplyInsert(BoardVO vo);
	
	@Update("update springreplyboard set depth=depth+1 "
			+ "where no=#{no}")
	public void boardDepthIncrement(int no);
	// 수정 
	@Select("select pwd from springreplyboard where no=#{no}")
	public String boardGetPassword(int no);
	@Update("update springreplyboard set name=#{name},subject=#{subject},content=#{content}"
			+ " where no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	// 삭제 트랜잭션 (스프링 : AOP)
	@Select("select root,depth from springreplyBoard "
			+ "where no=#{no}")
	public BoardVO boardInfoData(int no);
	@Update("update springreplyboard set "
			+ "subject='rhksflwkrk tkrwpgks rptlanfdlqslek.',content='rhksflwkrk tkrwpgks rptlanfdlqslek.'"
			+ "where no=#{no}")
	public void boardSubjectUpdate(int no);
	
	@Delete("delete from springreplyboard where no=#{no}")
	public void boardDelete(int no);
	@Update("Update springreplyboard set depth=depth-1 where no=#{no}")
	public void boardDepthDecrement(int no);
	
	// 다중검색(동적쿼리)
	@Select({
		"<script>"
		+ "select no,subject,name,to_char(regdate,'yyyy-mm-dd') as dbday,hit "
		+ "from springreplyboard where "
		+ "<trim prefixOverrides=\"OR\">"
		+ "<foreach collection=\"fsArr\" items=\"fd\">"
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
	})
	public List<BoardVO> boardFindData(Map map);
	/*
	 * name like or subject like or content like
	 */
}
