package com.sist.mapper;
import com.sist.vo.*;

import java.util.*;
import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	@Select({"<script>"
			+ "select cno,title,subject,poster "
			+ "from food_category "
			+ "where "
			+ "<if test='cno==1'>"
			+ "cno between 1 and 12"
			+ "</if>"
			+ "<if test='cno==2'>"
			+ "cno between 13 and 18"
			+ "</if>"
			+ "<if test='cno==3'>"
			+ "cno between 19 and 30"
			+ "</if>"
			+ " order by cno asc"
			+ "</script>"
	})
	public List<CategoryVO> foodCategoryListData(Map map);
	
	@Select("select fno,name,poster,type,phone,address "
			+ "from food_house "
			+ "where cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
}
