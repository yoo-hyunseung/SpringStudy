package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GoodsMapper {
	@Select("select no,goods_name as name, goods_poster as poster,goods_price as price,num "
			+ "from (select no,goods_name, goods_poster,goods_price,rownum as num "
			+ "from (select no,goods_name, goods_poster,goods_price  "
			+ "from goods_all order by no asc))"
			+ " where num between #{start} and #{end}")
	// 인라인 뷰 top-n : 포트폴리오 -> 인라인뷰를 이용한 페이징기법 사용 
	public List<GoodsVO> goodsListData(Map map);
	@Select("select ceil(count(*)/12.0) from goods_all")
	public int goodsTotalPage();
	// 상세보기 -> session -> (장바구니)
	@Update("Update goods_all set "
			+ "hit=hit+1 "
			+ "where no=#{no}")
	public void hitIncrement(int no);
	
	@Select("select no,goods_name as name, goods_sub as sub, goods_price as price, "
			+ "goods_discount as discount, goods_first_price as first_price,"
			+ "goods_delivery as delivery, goods_poster as poster, hit,account "
			+ "from goods_all "
			+ "where no=#{no}")
	public GoodsVO goodsDetailData(int no);
	
}
