package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
	private int no, hit,group_id,group_step,group_tab,root,dept;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
