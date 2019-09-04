var articleList = `
<div>
	<div class="list-header">
		<ul>
		    <li class="curr">
		    	<router-link to='/articleList'>文章</router-link>
		    </li>
		    <li>
		    	<router-link to='/writeArticle'>写文章</router-link>
		    </li>
		</ul>
		<div class="search">
		    <input type="text" name="title" required lay-verify="required" placeholder="" autocomplete="off" class="layui-input"> 
		    <button type="button" class="layui-btn">搜索文章</button>
		</div>
	</div>
	<div class="list-content">
	    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	        <ul class="layui-tab-title">
	            <li class="layui-this">全部(10)</li>
	            <li>我的(10)</li>
	            <li>已发布(10)</li>
	            <li>置顶(10)</li>
	            <li>草稿(10)</li>
	            <li>加密(10)</li>
	        </ul>
	        <div class="layui-tab-content">
	            <div class="layui-tab-item layui-show">
	                <table class="layui-table">
	                    <colgroup>
	                        <col width="150">
	                        <col width="200">
	                        <col>
	                    </colgroup>
	                    <thead>
	                        <tr>
	                            <th>
	                            	<input type="checkbox" :checked="flag" @change="selectAll"> 
	                            </th>
	                            <th>标题</th>
	                            <th>作者</th>
	                            <th>分类目录</th>
	                            <th>标签</th>
	                            <th>日期</th>
	                        </tr> 
	                    </thead>
	                    <tbody> 
	                        <tr>
	                            <td>
	                                <input type="checkbox" :checked="flag"/>
	                            </td>
	                            <td>哈哈哈哈哈哈</td>
	                            <td>冬瓜</td>
	                            <td>前端</td>
	                            <td>vue</td>
	                            <td>2019-08-27</td>
	                        </tr>

	                         <tr>
	                            <td>
	                               <input type="checkbox" :checked="flag"/>
	                            </td>
	                            <td>哈哈哈哈哈哈</td>
	                            <td>冬瓜</td>
	                            <td>前端</td>
	                            <td>vue</td>
	                            <td>2019-08-27</td>
	                        </tr>

	                         <tr>
	                            <td>
	                              <input type="checkbox" :checked="flag" />
	                            </td>
	                            <td>哈哈哈哈哈哈</td>
	                            <td>冬瓜</td>
	                            <td>前端</td>
	                            <td>vue</td>
	                            <td>2019-08-27</td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
	            <div class="layui-tab-item">内容2</div>
	            <div class="layui-tab-item">内容3</div>
	            <div class="layui-tab-item">内容4</div>
	            <div class="layui-tab-item">内容5</div>
	        </div>
	    </div>
	</div>
</div>
`;

var writeArticle = `
<div class="write-article">
    <form class="layui-form" action="">
        <div class="layui-form-item classify">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-block">
              <select name="classify" lay-verify="required">
                <option value=""></option>
                <option value="0">vue</option>
                <option value="1">php</option>
                <option value="2">java</option>
                <option value="3">hadoop</option>
                <option value="4">node</option>
              </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" >标题</label>
            <div class="layui-input-block">
              <input type="text" name="Title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item textarea-content">
            <label class="layui-form-label"  id="Context">正文</label>
            <div class="layui-input-block">
               <textarea id="demo" style="display: none;" placeholder="请输入内容" name="Context"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
        
            <label class="layui-form-label" >摘要</label>
            <div class="layui-input-block">
                <textarea name="Summary" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">封面</label>
            <div class="layui-input-block">
               <button type="button" class="layui-btn" id="test1">
                  <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">关键词</label>
            <div class="layui-input-block">
              <input type="text" name="Keywords" required  lay-verify="required" placeholder="请输入关键词" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">作者</label>
            <div class="layui-input-block">
              <input type="text" name="Author" required  lay-verify="required" placeholder="请输入作者" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
              <button class="layui-btn" name="send" lay-submit lay-filter="formDemo" onclick=>立即提交</button>
              <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

`;

var vm = new Vue({
    el: '#content',
    router:new VueRouter({ 
        routes:[ 
            {path:'/',redirect:'/articleList'},
            {path:'/articleList',
            	component:{
            		template: articleList,
            		data:function(){
				    	return{
				    		flag:false
				    	}
			    	},
			    	methods:{
			    		selectAll:function(){
			    			this.flag = !this.flag;
			    		}
			    	}
            	}
        	},
            {path:'/writeArticle',
            	component:{
            		template: writeArticle,
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
							    ,url: '/upload/' //上传接口
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
	$.ajax({
		url: '/article/writeArticle/add',
		type: "post",
		dataType:JSON,
		data:{"Title":Title,"Context":Context,"Summary":Summary,"Keywords":Keywords},
		success:function (data) {
			window.alert("添加成功")
		},
		error:function () {
			window.alert("添加失败");
		}
		}

	)

	}
