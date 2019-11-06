layui.define(['element','form','jquery','layer','upload','table'],function (exports) {
    var element = layui.element
        ,form = layui.form
        ,table = layui.table
        ,$=layui.jquery
        ,layer = layui.layer
        ,upload = layui.upload;

    table.render({
        elem:"#links",
        url: "/foot/all"
        ,method: "GET"
        ,cellMinWidth: '80'
        ,done: function (res,curr,count) {
            layer.photos({
                photos: '.link-picture'
                ,anim: 1  //指定弹出图片的类型
            });
        }
        ,cols: [[
            {field:'id',width:80, title: 'id',fixed:'left'}
            ,{field:'linkName', width:150, title: '链接名称'}
            ,{field:'linkUrl', title: '链接Url', width: 200}
            ,{field:'linkPicture',title:'链接图片',width:150,templet:function (d) {
                    return `<div class="link-picture" style="cursor: pointer;">
                        <img src= ` + d.linkPicture + ` style="width: 150px;height: 50px;"/>
                        </div>`;
                }}
            ,{field:'createTime',title:'创建时间',width:120}
            ,{field:'remark',width:180, title: '备注'}
            ,{title: '操作',toolbar:"#table-link-change",fixed:'right'}
        ]]
        ,page: false
    });

    table.render({
        elem:"#footNav",
        url: "/footNav/all"
        ,method: "GET"
        ,cellMinWidth: '80'
        ,cols: [[
            {field:'id',width:80, title: 'id',fixed:'left'}
            ,{field:'name', width:150, title: '导航名称'}
            ,{field:'url', title: '导航url', width: 200}
            ,{field:'remark',width:180, title: '备注'}
            ,{title: '操作',toolbar:"#table-foot-change",fixed:'right'}
        ]]
        ,page: false
    });

    table.on('tool(footNavs)',function (obj) {
        var data = obj.data
            , event = obj.event;
        if (event === 'edit') {
            layer.open({
                type: 2
                , title: '修改底部导航'
                , shade: [0.3]
                , content: '/footNav/updateFootNav?id='+data.id
                , area: ['400px', '400px']
                , offset: 'auto'
            });
        } else if (event === 'del') {
            layer.confirm('确认删除这条数据吗?', {btn: ['确认', '取消']}, function (index) {
                $.ajax({
                    url: '/footNav/del?id='+data.id,
                    type: 'DELETE',
                    data: {'id': data.id},
                    dataType: 'JSON',
                    success: function (data) {
                        layer.msg('删除成功');
                        table.reload('footNav');
                    }
                    , error: function () {
                        layer.msg('删除失败');
                    }
                });
            });
        }
    });

    table.on('tool(demo)',function (obj) {
        var data = obj.data
            , event = obj.event;
        if (event === 'edit') {
            layer.open({
                type: 2
                , title: '修改友情链接'
                , shade: [0.3]
                , content: '/foot/upL?id='+data.id
                , area: ['400px', '400px']
                , offset: 'auto'
            });
        } else if (event === 'del') {
            layer.confirm('确认删除这条数据吗?', {btn: ['确认', '取消']}, function (index) {
                $.ajax({
                    url: '/foot/del?id='+data.id,
                    type: 'DELETE',
                    data: {'id': data.id},
                    dataType: 'JSON',
                    success: function (data) {
                        layer.msg('删除成功');
                        table.reload('links');
                    }
                    , error: function () {
                        layer.msg('删除失败');
                    }
                });
            });
        }
    });

    form.on('submit(upL)',function (data) {
        //JSON数据
        var js = {id:$("#id").val(),linkName:$("#name").val(),linkUrl:$("#url").val(),linkPicture:$("#show")[0].src,remark:$("#remark").val()};
        $.ajax({
            url: '/foot/updateL',
            type: 'PUT',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(js),
            success: function (data) {
                //先获取子页面索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭子页面索引
                window.parent.layer.close(index);
                //刷新父页面的表格
                parent.layui.table.reload('links');
            },error:function () {
                layer.alert("更新失败");
                //先获取子页面索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭子页面索引
                window.parent.layer.close(index);
                //刷新父页面的表格
                parent.layui.table.reload('links');
            }
        });
        console.log(data);
        return false;
    });

    form.on('submit(upFoot)',function (data) {
        //JSON数据
        var js = {id:$("#id").val(),name:$("#name").val(),url:$("#url").val(),remark:$("#remark").val()};
        $.ajax({
            url: '/footNav/update',
            type: 'PUT',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(js),
            success: function (data) {
                //先获取子页面索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭子页面索引
                window.parent.layer.close(index);
                //刷新父页面的表格
                parent.layui.table.reload('footNav');
            },error:function () {
                layer.alert("更新失败");
                parent.window.location.reload();
            }
        });
        console.log(data);
        return false;
    });

    form.on('submit(updateC)',function (data) {
        //JSON数据
        var js = {id:$("#cid").val(),name:$("#name").val(),icp:$("#icp").val()};
        $.ajax({
            url: '/foot/updateC',
            type: 'PUT',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(js),
            success: function (data) {
                layer.alert(data.msg);
            },error:function () {
                layer.alert("更新失败");
            }
        });
        return false;
    });

    form.on('submit(saveLink)',function (data) {
        $.ajax({
            url: '/foot/addLink',
            type: 'post',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:4000},function () {
                    layer.alert("更新失败");
                    //先获取子页面索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭子页面索引
                    window.parent.layer.close(index);
                    //刷新父页面的表格
                    parent.layui.table.reload('links');
                });
            },error: function () {
                layer.alert("添加失败");
            }
        });
        return false;
    });

    form.on('submit(saveFoot)',function (data) {
        $.ajax({
            url: '/footNav/add',
            type: 'post',
            dataType: 'JSON',
            contentType: 'application/JSON',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.msg(data.msg,{icon:1,time:4000},function () {
                    //先获取子页面索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭子页面索引
                    window.parent.layer.close(index);
                    //刷新父页面的表格
                    parent.layui.table.reload('footNav');
                });
            },error: function () {
                layer.alert("添加失败");
            }
        });
        return false;
    });

    $("#addL").on('click',function () {
        layer.open({
            type: 2
            , title: '添加子导航'
            , shade: [0.3]
            , content: '/foot/addL'
            , area: ['400px', '400px']
            , offset: 'auto'
        });
    });

    $("#addF").on('click',function () {
        layer.open({
            type: 2
            , title: '添加子导航'
            , shade: [0.3]
            , content: '/footNav/addFootNav'
            , area: ['400px', '400px']
            , offset: 'auto'
        });
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


    exports('foot',{});
});
