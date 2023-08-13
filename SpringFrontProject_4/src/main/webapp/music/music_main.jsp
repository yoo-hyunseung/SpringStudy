<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
	margin-top:50px;
}
.row{
	margin:0px auto;
	width: 960px;
}
input[type="text"]{
	width: 200px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">{{music_title}}</h1>
		<div class="row">
			<div class="text-center">
				<b-button variant="outline-danger" @click="musicGetData(1)">TOP!))</b-button>
				<b-button variant="outline-warning" @click="musicGetData(2)">가요</b-button>
				<b-button variant="outline-info" @click="musicGetData(3)">pop</b-button>
				<b-button variant="outline-primary" @click="musicGetData(4)">ost</b-button>
				<b-button variant="outline-default" @click="musicGetData(5)">트롯</b-button>
				<b-button variant="outline-danger" @click="musicGetData(6)">재즈!))</b-button>
				<b-button variant="outline-danger" @click="musicGetData(7)">클래식!))</b-button>
			</div>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<input type="text" ref="fd" class="input-sm" size="20" placeholder="검색어입력" id="keyword">
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<table class="table" id="user-table">
				<thead>
				<tr class="danger">
					<th class="text-center">순위</th>
					<th class="text-center"></th>
					<th class="text-center">곡명</th>
					<th class="text-center">가수명</th>
					<th class="text-center">앨범</th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="vo in music_list">
					<td class="text-center">{{vo.mno}}</td>
					<td>
						<img alt="" :src="vo.poster" style="width: 30px;height: 30px">
					</td>
					<td>{{vo.title}}</td>
					<td>{{vo.singer}}</td>
					<td>{{vo.album}}</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
<script>
new Vue({
	el:'.container',
	data:{
		cno:1,
		music_list:[],
		music_detail:{},
		music_title:''
	},
	mounted:function(){
		this.musicGetData(1)
		/* if(page==null)page="1"; */
		console.log("mount!!!!!!!")
		// Jquery나 다른 라이브러리 연동
		// $(function(){======mounted======})
		$('#keyword').keyup(function(){
			console.log("keyup!!!!!!!!!")
			let k = $(this).val()
			$('#user-table > tbody > tr').hide()
			let temp = $('#user-table > tbody > tr > td:nth-child(5n+3):contains("'+k+'")')
			$(temp).parent().show();
		})
	},
	methods:{
		musicGetData:function(cno){
			axios.get("http://localhost/web/music/music_vue.do",{
				params:{
					cno:cno
				}
			}).then(response=>{
				console.log(response.data)
				this.music_list=response.data
				this.music_title=response.data[0].music_title
			})
		}
	}
	
})
</script>
</body>
</html>