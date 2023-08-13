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

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
	margin-top:50px;
}
.row{
	margin:0px auto;
	width: 960px;
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
	<music v-bind:musicdata="music_list">
	 <!-- template 내용  component-->  
	</music>
		<b-modal ref="my-modal" hide-footer :title="music_detail.title" v-if="isShow" id="modal-lg" size="lg" >
			<div class="text-center">
				<iframe :src="'http://youtube.com/embed/'+music_detail.key" style="width: 600px; height: 500px"></iframe>
			</div>
		</b-modal>
	</div>
</div>
<script>
// Component => Vue 통신
let eventBus = new Vue()
Vue.component('music',{     // 속성값으로 보낸다.
	props:['musicdata'],
	template:'<table class="table">'
	+'<thead>'
	+'<tr class="success">'
	+'<th class="text-center">순위</th>'
	+'<th class="text-center"></th>'
	+'<th class="text-center">곡명</th>'
	+'<th class="text-center">가수명</th>'
	+'<th class="text-center">앨범명</th>'
	+'<th class="text-center"></th>'
	+'</tr>'
	+'</thead>'
	+'<tbody>'
	+'<tr v-for="vo in musicdata">'
	+'<td class="text-center">{{vo.mno}}</td>'
	+'<td class="text-center">'
	+'<img :src="vo.poster" style="height:30px;width:30px">'
	+'</td>'
	+'<td">{{vo.title}}</td>'
	+'<td>{{vo.album}}</td>'
	+'<td>{{vo.singer}}</td>'
	+'<td class="text-center">'
	+'<b-button id="show-btn" variant="outline-success" @click="showMovie(vo.mno,true)">video</b-button>'
	+'</td>'
	+'</tr>'
	+'</tbody>'
	+"</table>",
	methods:{
		showMovie:function(value,bool){
			eventBus.$emit('showMovieEvent',value,bool)
		}
	}
})
new Vue({
	el:'.container',
	data:{
		music_list:[],
		music_title:'',
		music_detail:{},
		isShow:false,
	},
	mounted:function(){
		this.musicGetData(1);
	},
	updated:function(){
		let _this=this
		eventBus.$on('showMovieEvent',function(value,bool){
			_this.isShow=bool;
			axios.get("http://localhost/web/music/detail_vue.do",{
				params:{
					mno:value
				}
			}).then(response=>{
				console.log(response.data)
				_this.music_detail=response.data;
				_this.musicShow()
			})
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
		},
		musicShow:function(){
			this.$refs['my-modal'].show()
		}
	}
})
</script>
</body>
</html>