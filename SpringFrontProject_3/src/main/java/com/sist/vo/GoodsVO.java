package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoodsVO {
	// 페이지 기법
	// 쿠키처리
	// 세션/
	// 로그인 => password 암호화/복호화
	// Front -> VueJS => watch / computed / component / filter
	// 회원가입 => 유효성 검
	
	
	private int no,discount,hit,account;
	private String name,sub,price,first_price,delivery,poster;
}
