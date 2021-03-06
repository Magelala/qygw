layui.define(['element','table','form','upload','layedit','jquery','layer'],function (exports) {
    var element = layui.element,
        table = layui.table,
        upload = layui.upload,
        form = layui.form,
        layedit = layui.layedit,
        $ = layui.jquery,
        layer = layui.layer;

    //富文本框事件
    layedit.set({
        uploadImage: { //富文本框图片上传
            url: '/upload/native' //接口url
            ,type: 'POST' //默认post
        }
    });
    layedit.build("demo"); //建立编辑器
    layedit.build("demo1",{
        height:100
    }); //建立编辑器
    form.verify({
        context: function(value){
            layedit.sync(index);
        }
    });

    var index = layedit.build("demo"); //建立编辑器

    form.verify({
        context: function(value){
            layedit.sync(index);
        }
    });



    //数据表格
    layui.use('table', function(){
        //第一个实例
        table.render({
            elem: '#articleShow'
            ,url: '/article/show' //数据接口
            ,method: 'GET'
            ,cellMinWidth: '80'
            ,cols: [[ //表头
                {field: 'id', title: 'ID', align:'center',width:80}
                ,{field: 'title', title: '文章标题'}
                ,{field: 'name',title:'文章类别',width:200}
                ,{field: 'createByDate', title: '创建时间',align:'center',width:120}
                ,{title:'操作',fixed: 'right', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            ,page:{
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                ,curr: 1 //设定初始在第 1 页
                ,limit: 5 //一页显示多少条
                ,limits: [5,6,7,8,9,10,15,20]
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: '尾页' //不显示尾页
            }
        });
    });



//监听工具条
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        json=JSON.stringify(data);
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        if(layEvent === 'del'){ //删除
            var delIndex = top.layer.confirm('真的删除行么'+ data.id + "的信息吗?", function(delIndex){
                $.ajax({
                    url: '/article/delete/'+data.id,
                    type: "post",

                    success: function () {
                        {
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            top.layer.close(delIndex);
                            console.log(delIndex);
                            layer.msg("删除成功")
                        }
                    }
                })
                layer.close(delIndex); //向服务端发送删除指令

            });
        } else if(layEvent==='edit'){//编辑文章

           layer.open({
                type:2  //类型2位弹出内置框
                , title: '修改文章'
                , shade: 0
                , content: '/article/edit?id='+data.id
                , area: ['500px', '750px']
                , offset: 'auto'
            })

        }
        else if (layEvent==='editCategory'){
            layer.open({
                type:2  //类型2位弹出内置框
                , title: '修改类别名称'
                , shade:  0
                , content: '/article/editCategory?id='+data.id
                , area: ['400px', '400px']
                , offset: 'auto'
            })
        }
        else if (layEvent==='delCategory'){
            { //删除分类
                var delIndex = top.layer.confirm('真的删除行么'+ data.id + "的信息吗?", function(delIndex){
                    $.ajax({
                        url: '/article/deleteCategory/'+data.id,
                        type: "post",


                         success: function () {
                             {
                                 obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                 top.layer.close(delIndex);
                                 console.log(delIndex);
                                 layer.msg("删除成功")
                             }
                         },
                         error: function () {
                             top.layer.close(delIndex);
                             console.log(delIndex);
                             layer.msg("删除失败")
                         }
                     })
                     layer.close(delIndex); //向服务端发送删除指令

                });
            }
        }


    });
    console.log($('#select').val());
    //更新
  /*  form.on('submit(update)',function (data) {
        //JSON数据
        js = {id:$("#id").val(),
            title:$("#title").val(),
            author:$("#author").val(),
            summary:$("#summary").val(),
            content: $("#context").val()};
        $.ajax({
            url: '/article/update',
            type: 'PUT',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(js),
            success: function (data) {
                layer.alert(data.msg);
                parent.window.location.reload();
            },error:function () {
                layer.alert("更新失败");
                parent.window.location.reload();
            }
        });
        console.log(data);
        return false;
    }); */
    //更新
    form.on('submit(update)',function (data) {

        // // JSON数据
        // js = {id:$("#id").val(),
        //     title:$("#title").val(),
        //     type:$("#type").val(),
        //     author:$("#author").val(),
        //     summary:$("#summary").val(),
        //     context: $("#context").val(),
        //     keywords: $("#keywords").val(),
        //     picture:$("#show").src,
        // }
        // console.log($("#show").attr('src'))
        $.ajax({
            url: '/article/update',
            type: 'PUT',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg('更新成功',{icon:6},function () {
                    parent.window.location.reload();
                });
            },error:function () {
                layer.msg('更新失败',function () {
                    var index1 = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index1)
                });
            }
        });
        console.log(data);
        return false;
    });

    //添加按钮绑定事件
    $('.addClass').click(function () {
        // console.log(1);
        var index = layer.open({
            type: 1,
            shade:0,
            title: '添加分类',
            area: ['500px', '300px'],
            content: $('#addClass'),
            success: function (layero, index) {
                // 关闭弹框后清空输入框

                form.val("frm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    "name": '',
                    'description':'',
                });
                form.on('submit(formDemo2)', function (data) {
                    $.ajax({
                        url: ' /article/addCategory ',
                        data: JSON.stringify(data.field),
                        type: 'post',
                        contentType: 'application/json',
                        success: function (data) {
                            layer.msg('添加成功', { icon: 6 },function () {
                                window.location.reload();
                            });
                            layer.close(index);
                        },error: function () {
                            layer.msg('添加失败', { icon: 6 });
                            layer.close(index);
                        }


                    })
                    return false;
                });
            }
        });
    })
    //更新分类
    form.on('submit(categoryUpdate)',function (data) {
        //JSON数据
        js = {id:$("#id").val(),
            name:$("#name").val(),
            description:$("#description").val(),
            };
        $.ajax({
            url: '/article/updates',
            type: 'PUT',
            async:false,
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(js),
            success: function (data) {
                layer.msg('更新成功',{icon:6},function () {
                    var index1 = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index1)
                    parent.window.location.reload();

                });

            },error:function () {
                layer.msg('更新失败',function () {
                    var index1 = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index1)
                });
            }
        });
        console.log(data);
        return false;
    });
    //为写文章中添加验证规则
    form.verify({
        title: function (value, item) {
            value = value.trim();
            if (value.length < 0) {
                return "请输入标题";
            }
        }
    });

    form.verify({
        //书写自己的验证规则
        author: function (value, item) {
            value = value.trim();
            if (value.length < 0) {
                return "请输入作者";
            }
        }
    });

    //富文本框验证，数据填充
    form.verify({
        //书写自己的验证规则
        keywords: function (value, item) {
            value = value.trim();
            if (value.length < 0) {
                return "请输入关键字";
            }
        }
    });



    //写文章中的提交事件
    form.on('submit(formDemo)',function (data) {
        $.ajax({
            url: '/article/add',
            type: 'post',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:2000},function () {
                    window.location.reload();
                });
            },error: function () {
                layer.alert("添加失败");
            }
        });
        return false;
    });

    //写文章中的文件上传
    var uploadInst = upload.render({
        elem: '#test1' //绑定元素,上传按钮的id
        , url: '/upload/native' //上传接口
        , method: 'POST'
        , accept: 'images'
        , size: 150
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#show').attr('src', result); //图片链接
            });
        }
        , done: function (res) {//上传完毕回调
            layer.close(layer.msg('上传成功！'));
        }
        , error: function () {//请求异常回调
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });



    var active =  {

            searchTitle: function () {
            var title = $('#title');
            table.reload('articleShow',{
                page: {
                    curr: 1
                }
                ,url: '/article/finds'
                ,where:{
                    title: title.val()
                }
                ,method: 'GET'
            });
        }
    };

    /*绑定搜索的点击事件*/
    $('.search-article .layui-btn').on('click',function(){
        var type =  $(this).data('type');
        active[type] ? active[type].call(this) : '';
        $('#title').val('');
    });


    exports('article',{});
});