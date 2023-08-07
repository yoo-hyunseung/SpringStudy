package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FoodDAO {
	
	@Autowired
	private FoodMapper mapper;
	
//	@Select("select cno,title,poster from food_category where cno asc")
	public List<CategoryVO> foodCategoryData(){
		return mapper.foodCategoryData();
	}
	
	/*@Select("select fno,name,poster,rownum "
			+ "from (select fno,name,poster "
			+ "from food_house order by fno asc) "
			+ "where rownum<=7")*/
	public List<FoodVO> foodTop7(){
		return mapper.foodTop7();
	}
	
	/*@Select("select no,title,poster, rownum "
			+ "from (select no,title,poster "
			+ "from seoul_location order by no asc) "
			+ "where rownum<=7")*/
	public List<SeoulVO> seoulTop7(){
		return mapper.seoulTop7();
	}
}
