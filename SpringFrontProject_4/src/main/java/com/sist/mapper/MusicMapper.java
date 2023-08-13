package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface MusicMapper {
	@Select("select * from genie_music "
			+ "where cno=#{cno} "
			+ "order by mno asc")
	public List<MusicVO> musicListData(int cno);
	
	@Select("select * from genie_music "
			+ "where mno=#{mno}")
	public MusicVO musicDetailData(int mno);
}
