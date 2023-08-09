<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-3" v-for="vo in goods_list">
		    <div class="thumbnail">
		      <a :href="'detail.do?no='+vo.no">
		        <img :src="vo.poster" :title="vo.name" style="width:100%">
		        <div class="caption">
		          <p style="font-size:9px">{{vo.price}}</p>
		        </div>
		      </a>
		  </div>
		  </div>
	</div>
	<div style="height: 10px"></div>
	<div class="row">
	<!-- 페이지가 나눠지는 장소 -->
		<div class="text-center">
			<ul class="pagination">
			  <li v-if="startPage>1"><a href="#" @click="prev()">&lt;</a></li>
			  <!-- 
			  	range => [1,2,3,4,5] [6,7,8,9,10]
			  	v-for="i in 10"
			   -->
			  <li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#">{{i}}</a></li>
			  <li v-if="endPage<totalpage"><a href="#" @click="next()">&gt;</a></li>
			</ul>
		</div>
	</div>
	<h3>최근 방문 상품</h3>
	<hr>
	<div class="row">
	
	</div>
</div>
<script>
new Vue({
	el:'.container-fluid',
	data:{
		goods_list:[],
		curpage:1,
		totalpage:0,
		startPage:0,
		endPage:0,
		goods_cookie:[],
		
	},
	// 그냥 있어도 자동 호출
	mounted:function(){ // -> window.onload , java -> 생성자
		this.send()
	},
	// 사용자 정의 함수는 반드시 -> 반드시 호출해야한다.
	methods:{
		// 서버와 통신하는 함수
		send:function(){
			let _this=this // Vue 의 this;
			// axios.get() => @GetMapping , axios.post() -> @PostMapping
			// 412 - get/post에러 
			axios.get("http://localhost/web/goods/list_vue.do",{
				params:{
					page:this.curpage // ***key -> page  controller 와 반드시 일치해야한다.
				}
			}).then(function(response){
				// function안으로 들어와서 this가 없다 미리 위에서 this를 저장해놨다.
				console.log(response.data)
				_this.goods_list=response.data;
				_this.curpage = response.data[0].curpage;
				_this.totalpage = response.data[0].totalpage;
				_this.startPage = response.data[0].startPage;
				_this.endPage = response.data[0].endPage;
				
			})
		},
		range:function(start,end){
			let arr=[]
			let length = end-start
			for(let i=0 ; i <= length;i++){
				arr[i]=start;
				start++;
			}
			return arr;
		},
		prev:function(){
			this.curpage=this.startPage-1
			this.send()
		},
		next:function(){
			this.curpage=this.endPage+1;
			this.send()
		}
	}
})
</script>
</body>
</html>