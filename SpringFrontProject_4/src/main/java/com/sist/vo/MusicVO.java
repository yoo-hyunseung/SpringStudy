package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MusicVO {
	/*
	 MNO     int
	 CNO     int
	 TITLE      VARCHAR(500)
	 SINGER      VARCHAR(500)
	 ALBUM    VARCHAR(500)
	 POSTER       VARCHAR(260)
	 IDCREMENT    int
	 STATE  VARCHAR(20)
	KEY  VARCHAR(30)
	*/
	private int mno,cno,idcrement;
	private String title,singer,album,poster,state,key;
}
