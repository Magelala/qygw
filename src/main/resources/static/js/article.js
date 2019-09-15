var vm = new Vue({
    el: '#content',
    router:new VueRouter({ 
        routes:[ 
            {path:'/',redirect:'/articleList'},
            {path:'/articleList',
            	component:{
            		template: '#articleList',
            		data:function(){
				    	return{
				    		flag:false
				    	}
			    	},
                    // data:{
            		//   list: [//存放所有品牌列表的数组
                    //       {
                    //           title:'哈哈',
                    //           author: 'wind',
                    //           categoryPercentage:'后端',
                    //           label:'vue',
                    //
                    //       }
                    //   ]
                    // },
                    // created(){
            		//   this.getAllList()
                    // },
			    	methods:{
			    		selectAll:function(){
			    			this.flag = !this.flag;
			    		},
			    		getAllList(){//获取所有文章列表
			    		    this.$http.get('/articleList/show').then()

                        }
			    	}
            	}
        	},
            {path:'/writeArticle',
            	component:{
            		template:'#writeArticle',
            		created(){
            			layui.use('form', function(){
							var form = layui.form;
							form.on('submit(formDemo)', function(data){
							layer.msg(JSON.stringify(data.field));
							return false;
							});
						});
						layui.use('upload', function(){
							var upload = layui.upload; 
							var uploadInst = upload.render({
							    elem: '#test1' //绑定元素
							    ,url: '/article/writeArticle/upload' //上传接口
							    ,done: function(res){
							      //上传完毕回调
							    }
							    ,error: function(){
							      //请求异常回调
							    }
							});
						});
						layui.use('layedit', function(){
							var layedit = layui.layedit;
							layedit.build('demo'); //建立编辑器
						});
            		}
            	}
            }
        ]
    })
})

		 function add(){
	var Title = $("input[name='Title']").val();
	var	Context = $("textarea[name='Context']").val();
	var Summary = $("textarea[name='Summary']").val();
	var Keywords = $("input[name='Keywords']").val();
	var	author = $("input[name='author']").val();
	var cover = $("button[name='cover']").val();

	// console.log(Context);
	$.ajax({
		url: '/article/writeArticle/add',
		type: "post",
		dataType:JSON,
		data:{"Title":Title,"Summary":Summary,"Keywords":Keywords,"author":author},
		success:function () {
			window.alert("添加成功")
		}
		}
	)


	}
	
