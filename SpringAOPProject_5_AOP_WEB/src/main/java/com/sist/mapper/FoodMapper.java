package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface FoodMapper {
	@Select("select cno,title,poster from food_category order by cno asc")
	public List<CategoryVO> foodCategoryData();
	
	@Select("select fno,name,poster,rownum "
			+ "from (select fno,name,poster "
			+ "from food_house order by fno asc) "
			+ "where rownum<=7")
	public List<FoodVO> foodTop7();
	
	@Select("select no,title,poster, rownum "
			+ "from (select no,title,poster "
			+ "from seoul_location order by no asc) "
			+ "where rownum<=7")
	public List<SeoulVO> seoulTop7();
}
