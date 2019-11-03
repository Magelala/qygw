layui.define(['element','upload','jquery','layer','form','table'],function (exports) {
    var element = layui.element,
        table = layui.table,
        layer = layui.layer,
        $ = layui.jquery,
        form = layui.form,
        upload = layui.upload;

    //父表格数据
    table.render({
        elem: '#navs'
        ,toolbar: '#toolbar'
        ,url:'/nav/all'
        ,method: 'get'
        ,cellMinWidth: '80'
        ,cols: [[
            {type: 'checkbox',checkbox:true,fixed:'left'}
            ,{field:'id',width:80, title: 'id',fixed:'left'}
            ,{field:'name', width:120, title: '栏目名称'}
            ,{field:'statues', width: 120, title: '是否显示',align:'center',templet: function (d) {
                    if (d.statues === true){
                        return `<button class="layui-btn layui-btn-normal layui-btn-xs">已显示</button>`;
                    }else{
                        return `<button class="layui-btn layui-btn-primary layui-btn-xs">未显示</button>`;
                    }
                }}
            ,{field:'url', title: '导航链接', width: 120}
            ,{field:'createTime',width: 120,title: '创建时间', sort: true}
            ,{field:'description',width:150, title: '描述'}
            ,{field:'sort',title:'排序',width:80,sort: true}
            ,{title: '操作',toolbar:"#table-nav-change",fixed:'right'}
        ]]
        ,page: false
    });

    //设置一个全局变量,该变量的作用的是获取后台返回id
    var pid = $("#subId").val();
    console.log(pid);

    //子表格数据
    table.render({
        id: 'subNav',
        elem: '#subNav'
        ,url: '/nav/allSub?pid='+pid
        ,method: 'get'
        ,cellMinWidth: '80'
        ,cols: [[
            {type: 'checkbox',checkbox:true,fixed:'left'}
            ,{field:'id',width:50, title: 'id',fixed:'left'}
            ,{field:'name', width:100, title: '子栏目名称'}
            ,{field:'statues', width: 80, title: '是否显示',align:'center',templet: function (d) {
                    if (d.statues === true){
                        return `<button class="layui-btn layui-btn-normal layui-btn-xs">已显示</button>`;
                    }else{
                        return `<button class="layui-btn layui-btn-primary layui-btn-xs">未显示</button>`;
                    }
                }}
            ,{field:'url', title: '链接', width: 120}
            ,{field: 'authorName',title: '操作人',width: 80}
            ,{field:'createTime',width: 120,title: '创建时间', sort: true}
            ,{field:'description',width:100, title: '描述'}
            ,{field:'sort',title:'排序',width:80,sort: true}
            ,{title: '操作',toolbar:"#table-subNav-change",fixed:'right'}
        ]]
        ,title: '走进安利'
        ,page: false
    });

    /*为复选框添加点击事件,checkbox(lay-filter)*/
    table.on('checkbox(demo)', function(obj){
        console.log(obj);
        d = obj;
    });

    table.on('toolbar(demo)',function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data[0]
            ,event = obj.event;
        if (event === 'add'){
            layer.open({
                type: 2
                ,title: '添加导航'
                ,shade: [0.3]
                ,content: '/nav/add'
                ,area: ['400px','400px']
                ,offset:'auto'
            });
        }else if(event === 'del'){
            console.log(data);
            if (checkStatus.data.length === 0){
                //没有选择数据
                return layer.msg("请选择需要删除的数据");
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
                console.log(ids);
                layer.confirm('确认删除'+checkStatus.data.length+'条数据吗?',{btn:['确认','取消']},function (index) {
                    $.ajax({
                        url: '/nav/deletes'+ids,
                        type: 'DELETE',
                        data: {ids:db},
                        dataType: 'JSON',
                        success:function (data) {
                            layer.msg('删除成功');
                            table.reload('navs');
                        }
                        ,error: function () {
                            layer.msg('删除失败');
                        }
                    });
                });
            }
        }else if(event === 'edit'){
            if (checkStatus.data.length === 0){
                return layer.msg("请选择需要修改的数据");
            }else if (checkStatus.data.length === 1){
                console.log(data.id);
                layer.open({
                    type: 2
                    , title: '修改导航'
                    , shade: [0.3]
                    , content: '/nav/edit?id='+data.id
                    , area: ['400px', '400px']
                    , offset: 'auto'
                });
            }else{
                return layer.msg("只能修改一行");
            }
        }
    });


    table.on('tool(demo)',function (obj) {
        var data = obj.data
            ,event = obj.event;
        console.log(data);
        var prev = $(this).parent().parent().parent();
        if (event === 'up' || event === 'down'){
            if ($(prev).prev().html() == null && event ==='up'){//判断最顶部,直接返回
                layer.alert("已经达到最顶部了");
                return;
            }else if($(prev).next().html() == null && event=== 'down'){//判断最底部,直接返回
                layer.alert("已经到达最底部了");
                return;
            }else{
                $.ajax({
                    url: '/nav/move?currSort='+data.sort+'&id='+data.id+'&operate='+event,
                    type: 'POST',
                    data: {'id':data.id,currSort:data.sort},
                    dataType: 'JSON',
                    success: function (data) {
                        table.reload('navs');
                    },error: function () {
                        layer.msg("移动失败");
                    }
                });
            }
        }else if (event === 'subNavs'){ //执行添加子栏目
            layer.open({
                type: 2
                , title: '子导航管理'
                , shade: [0.3]
                , content: '/nav/navSub?pid='+data.id
                , area: ['1000px', '500px']
                , offset: 'auto'
            });
        }
    });

    //----------------添加父导航ajax请求
    form.on('submit(save)',function (data) {
        if (data.field.statues === '1'){
            data.field.statues = true;
        }else{
            data.field.statues = false;
        }
        console.log(JSON.stringify(data.field));
        $.ajax({
            url: '/nav/add',
            type: 'post',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:4000},function () {
                    parent.window.location.reload();
                });
            },error: function () {
                layer.alert("添加失败");
            }
        });
        return false;
    });


    /*单选按钮切换选中*/
    form.on('radio(showbtn)', function (data) {
        $("input[name='statues']").removeAttr('checked');
        $("input[name='statues']").removeProp('checked');
        $("#show"+data.elem.value).attr("checked",'checked');
        $("#show"+data.elem.value).prop("checked",'checked');
    });

    //------------------编辑-----------------------
    form.on('submit(update)',function (data) {
        var res,val = $("input[name='statues']:checked").val();
        if(val==='1'){
            res = true;
        }else if(val === '0'){
            res = false;
        }
        console.log(val);
        //JSON数据
        js = {id:$("#id").val(),name:$("#name").val(),url:$("#url").val(),statues:res,description:$("#description").val(),sort:$("#sort").val()};
        $.ajax({
            url: '/nav/update',
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
    });

    //添加验证规则
    form.verify({
        name: function (value,item) {
            value =  value.trim();
            if (value.length<0){
                return "请输入导航名称";
            }
        }
    });


    //-------------------------子导航-----------------------
    table.on('tool(subDemo)',function (obj) {
        var data = obj.data
            ,event = obj.event;
        console.log(data);
        var prev = $(this).parent().parent().parent();
        if (event === 'up' || event === 'down'){
            if ($(prev).prev().html() == null && event ==='up'){//判断最顶部,直接返回
                layer.alert("已经达到最顶部了");
                return;
            }else if($(prev).next().html() == null && event=== 'down'){//判断最底部,直接返回
                layer.alert("已经到达最底部了");
                return;
            }else{
                $.ajax({
                    url: '/nav/moveSub?currSort='+data.sort+'&id='+data.id+'&pid='+data.pid+'&operate='+event,
                    type: 'POST',
                    data: {'id':data.id,'pid':data.pid,currSort:data.sort},
                    dataType: 'JSON',
                    success: function (data) {
                        table.reload('subNav');
                    },error: function () {
                        layer.msg("移动失败");
                    }
                });
            }
        }else if(event === 'edit'){
            layer.open({
                type: 2
                , title: '修改子导航'
                , shade: [0.3]
                , content: '/nav/editSub?id='+data.id+'&pid='+data.pid
                , area: ['400px', '400px']
                , offset: 'auto'
            });
        }else if(event === 'delete'){
            layer.confirm('确认删除这条数据吗?',{btn:['确认','取消']},function (index) {
                $.ajax({
                    url: '/nav/delete?id='+data.id,
                    type: 'DELETE',
                    data: {'id':data.id},
                    dataType: 'JSON',
                    success:function (data) {
                        layer.msg('删除成功');
                        table.reload('subNav');
                    }
                    ,error: function () {
                        layer.msg('删除失败');
                    }
                });
            });
        }
    });


    //子导航编辑
    form.on('submit(edit)',function (data) {
        var res,val = $("input[name='statues']:checked").val();
        if(val==='1'){
            res = true;
        }else if(val === '0'){
            res = false;
        }
        console.log(val);
        //JSON数据
        js = {id:$("#id").val(),name:$("#name").val(),url:$("#url").val(),statues:res,description:$("#description").val(),sort:$("#sort").val()};
        $.ajax({
            url: '/nav/update',
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
    });

    $('#subOperate .layui-btn').on('click',function () {
        layer.open({
            type: 2
            , title: '添加子导航'
            , shade: [0.3]
            , content: '/nav/addSub?pid='+$("#subId").val()
            , area: ['400px', '400px']
            , offset: 'auto'
        });
    });


    form.on('submit(saveSub)',function (data) {
        if (data.field.statues === '1'){
            data.field.statues = true;
        }else{
            data.field.statues = false;
        }
        console.log(JSON.stringify(data.field));
        $.ajax({
            url: '/nav/addSub?pid='+$('#pid').val(),
            type: 'post',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:4000},function () {
                    parent.window.location.reload();
                });
            },error: function () {
                layer.alert("添加失败");
            }
        });
        return false;
    });

    exports('nav',{});
});