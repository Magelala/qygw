layui.define(['element','table','form','layer','upload'],function (exports) {
    var layer = layui.layer,
        element = layui.element,
        table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        upload = layui.upload;

    table.render({
        elem: '#profile'
        ,toolbar: '#ptoolbar'
        ,url:'/companyProfile/all'
        ,method: 'get'
        ,cellMinWidth: '80'
        ,done: function (res,curr,count) {
            layer.photos({
                photos: '.layui-photos-demo'
                ,anim: 1  //指定弹出图片的类型
            });
        }
        ,cols: [[
            {type: 'checkbox',checkbox:true,fixed:'left'}
            ,{field:'id', width:80, title: 'ID', sort: true,fixed:'left'}
            ,{field: 'name',width:120,title:'公司名称',fixed:'left'}
            , {field: 'imgUrl', width: 120, title: '图片地址',templet:function (d) {
                    return `<div class="layui-photos-demo" style="cursor: pointer;">
                        <img class="layui-photos-demo" src= ` + d.imgUrl + ` style="width: 50px;height: 50px;"/>
                        </div>`;
                }}
            ,{field:'createTime',width: 120,title: '创建时间', sort: true}
            ,{field:'title', width: 150, title: '标题'}
            ,{field:'message',minWidth:200, title: '正文'}
            ,{field:'sort',title:'排序',width:80}
            ,{title: '操作',toolbar:"#table-carousel-change",width:100,fixed:'right'}
        ]]
        ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            ,curr: 1 //设定初始在第 1 页
            ,limit: 5 //一页显示多少条
            ,limits: [5,6,7,8,9,10,15,20]
            ,groups: 1 //只显示 1 个连续页码
            ,first: false //不显示首页
            ,last: '尾页' //不显示尾页
        }
    });

    /*为复选框添加点击事件,checkbox(lay-filter)*/
    table.on('checkbox(demo)', function(obj){
        console.log(obj);
    });

    var active = {
        search: function () {
            var search = $('#psearch');
            table.reload('profile',{   /*重载id为carousel的表格*/
                page: {
                    curr: 1  /*从第一页开始找*/
                },
                url: '/companyProfile/find/'+search.val(),
                where:{
                    id: search.val()    /*id为需要查询的实体主键,后端需要返回该参数*/
                }
                ,method: 'POST'
            });
        }
        ,searchTitle: function () {
            var title = $('#ptitle');
            table.reload('profile',{
                page: {
                    curr: 1
                }
                ,url: '/companyProfile/finds'
                ,where:{
                    title: title.val()
                }
                ,method: 'GET'
            });
        }
    };

    /*绑定搜索的点击事件*/
    $('.search-profile .layui-btn').on('click',function(){
        var type =  $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    /*监听表格上工具栏事件,demo为lay-filter设置的值*/
    table.on('toolbar(pdemo)',function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data[0]
            ,event = obj.event;
        if (event === 'add'){
            layer.open({
                type: 2
                ,title: '添加广告'
                ,shade: [0.3]
                ,content: '/companyProfile/add'
                ,area: ['400px','400px']
                ,offset:'auto'
            });
        }else if (event === 'del'){
            if (checkStatus.data.length === 0){
                //没有选择数据
                return layer.msg("请选择需要删除的数据");
            }else if(checkStatus.data.length === 1){
                //单个删除
                layer.confirm('确定删除吗？',{btn: ['确认','取消']},function (index) {
                    $.ajax({
                        url: '/companyProfile/delete?id=' + data.id,
                        type: 'DELETE',
                        data: {id: data.id},
                        dataType: 'JSON',
                        success: function (data) {
                            layer.msg("删除成功");
                            table.reload('profile');
                        },
                        error: function () {
                            alert("错误，请联系后台管理员");
                        }
                    });
                });
            }else{
                //批量删除
                var ids = "",db="";
                for (var i = 0;i<checkStatus.data.length;i++){
                    if (i===0){
                        ids = "?ids="+checkStatus.data[i].id;
                    }else{
                        ids += "&ids="+checkStatus.data[i].id;
                    }
                    db += checkStatus.data[i].id +",";
                }
                layer.confirm('确认删除'+checkStatus.data.length+'条数据吗?',{btn:['确认','取消']},function (index) {
                    $.ajax({
                        url: '/companyProfile/deletes'+ids,
                        type: 'DELETE',
                        data: {ids:db},
                        dataType: 'JSON',
                        success:function (data) {
                            layer.msg('删除成功');
                            table.reload('profile');
                        }
                        ,error: function () {
                            layer.msg('删除失败');
                        }
                    });
                });
            }
        }else if(event === 'edit'){
            if (checkStatus.data.length === 0){
                return layer.msg("请输入数据");
            }else if (checkStatus.data.length > 1){
                return layer.msg("只能修改一行");
            }
            layer.open({
                type: 2
                , title: '修改公司简介'
                , shade: [0.3]
                , content: '/companyProfile/edit?id='+data.id
                , area: ['400px', '400px']
                , offset: 'auto'
            });
        }
    });

    /*监听表格中的工具条,demo为lay-filter设置的值*/
    table.on('tool(pdemo)',function (obj) {
        var data = obj.data
            ,event = obj.event;
        var prev = $(this).parent().parent().parent();
        if (event === 'up'){
            //判断最顶部,直接返回
            if ($(prev).prev().html() == null){
                layer.alert("已经达到最顶部了");
                return;
            }
        }else if (event === 'down'){
            //判断最底部,直接返回
            if ($(prev).next().html() == null){
                layer.alert("已经到达最底部了");
                return;
            }
        }
        //上移或者下移
        $.ajax({
            url: '/companyProfile/move?currSort='+data.sort+'&id='+data.id+'&operate='+event,
            type: 'POST',
            data: {id:data.id,currSort:data.sort},
            dataType: 'JSON',
            success: function (data) {
                table.reload('profile');
            },error: function () {
                layer.msg("移动失败");
            }
        });
    });

    //------------------------------添加功能js--------------------------------------------

    //添加验证规则
    form.verify({
        title: function (value,item) {
            value =  value.trim();
            if (value.length<0){
                return "请输入简介标题";
            }
        }
    });
    form.on('submit(save)',function (data) {
        $.ajax({
            url: '/companyProfile/add',
            type: 'post',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:2000},function () {
                    layer.msg(data.msg,{icon:1,time:4000},function () {
                        //先获取子页面索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭子页面索引
                        window.parent.layer.close(index);
                        //刷新父页面的表格
                        parent.layui.table.reload('profiles');
                    });
                });
            },error: function () {
                layer.alert("添加失败");
            }
        });
        return false;
    });

    //执行上传
    var uploadInst = upload.render({
        elem: '#upload' //绑定元素,上传按钮的id
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

    //---------------------------更新-------------------------------------
    form.on('submit(update)',function (data) {
        js = {id:$("#id").val(),title:$("#title").val(),imgUrl:$("#show")[0].src,name:$("#name").val(),message:$("#message").val(),sort:$("#sort").val()};
        $.ajax({
            url: '/companyProfile/edit',
            type: 'PUT',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(js),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:4000},function () {
                    layer.alert("添加成功");
                    //先获取子页面索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭子页面索引
                    window.parent.layer.close(index);
                    //刷新父页面的表格
                    parent.layui.table.reload('profiles');
                });
            },error:function () {
                layer.alert("更新失败");
            }
        });
        return false;
    });

    exports('companyProfile',{});
});