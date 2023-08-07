package com.sist.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Component, 
 * @Repository, 
 * @Service, 
 * @Controller, 
 * @RestController, 
 * @ControllerAdvice,
 * @Configuration
 * 
 *
 */
// javascript -> {}  List<FoodVO> -> [{}, {}, {}, {} ...] -> JSON
@RestController // 다른 언어와 연결 -> VueJS
@CrossOrigin("http://localhost:3000") // 포트 3000을 허용하겠다 cors
public class FoodRestController {

}
