layui.define(['element','form','jquery','layer', 'laypage', 'laytpl','table','layedit','upload'],function (exports) {

    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    var element = layui.element,
        table = layui.table,
        upload = layui.upload,
        form = layui.form,
        laypage = layui.laypage,
        laytpl = layui.laytpl,
        layedit = layui.layedit,
        $ = layui.jquery,
        layer = layui.layer;




        function getData() {
            $.get('/user/show', data => {
                //执行一个laypage实例
                laypage.render({
                    elem: 'pager' //注意，这里的 test1 是 ID，不用加 # 号
                    , count: data.count, //数据总数，从服务端得到
                    limit: 10,
                    jump: function (obj, first) {
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数
                        console.log(data.count);


                        //第三步：渲染模版

                        $.get('/user/show', {
                            pageIndex: obj.curr,
                            pageSize: obj.limit
                        }, data => {
                            console.log(data.data);

                            console.log(data.data[1].rrole.name);
                            /* for(var i = 0; i<data.data.length;i++){

                             }*/
                            laytpl($('#trTemplate').html()).render(data.data, function (html) {
                                $('#dataContainer').html(html)
                            });

                        })
                    }
                });
            })
        }
        getData();
        // 模糊搜索
        $('.search').on('click',function () {
            var js ={
                userName:$('#userName').val(),
                role:$('#role').val()
            }


            $.ajax({
                url: '/user/search',
                data: JSON.stringify(js),
                type: 'post',
                contentType: 'application/json',
                success: data => {
                    if (data.count>0) {

                        laytpl($('#trTemplate').html()).render(data.data, function (html) {
                            $('#dataContainer').html(html)
                        });


                    } else {
                        layer.msg('搜索失败', { icon: 6 });

                        layer.close(index);
                    }

                },


            })



        });

        //事件委托
        //删除用户
        $('#dataContainer').on('click', '.delete', function () {
            var that = this;
            layer.confirm('确定要删除吗?', { icon: 3, title: '系统提示' }, function (index) {
                //do something
                console.log($(that).attr('data-id'))
                var ids=  $(that).attr('data-id');
                $.ajax({
                    url: '/user/deletes?ids='+ids,
                    type: 'post',
                    contentType: 'application/json',
                    success: data => {
                        if (data.count>0) {
                            layer.msg('删除成功', { icon: 6 });
                            getData();
                        } else {
                            layer.msg('删除失败', { icon: 6 });
                        }
                        layer.close(index);
                    }
                })
            });
        })
        //添加用户
        $('.add').click(function () {
            // console.log(1);
            var index = layer.open({
                type: 1,
                title: '添加用户',
                area: ['500px', '500px'],
                content: $('.addUser'),
                success: function (layero, index) {

                    form.on('submit(formDemo)', function (data) {


                        console.log(data.field);

                        $.ajax({
                            url: '/user/add',
                            data: JSON.stringify(data.field),
                            type: 'post',
                            contentType: 'application/json',
                            success: data => {
                                if (data.count>0) {
                                    layer.msg('添加成功', { icon: 6 });
                                    layer.close(index);
                                    getData();
                                } else {
                                    layer.msg('添加失败', { icon: 6 });

                                    layer.close(index);
                                }

                            },


                        })
                        return false;
                    });
                }
            });
        })
        //修改用户信息
        $('#dataContainer').on('click', '.update', function () {
            $.get('/user/findOne', { id: $(this).attr('data-id') }, data => {
                console.log(data);

                laytpl($('#infoTemplate').html()).render(data.data, function (html) {
                    $('.updateUser').html(html)
                });
                $('.keep').click(function () {
                    console.log('aaa');
                    form.on('submit(formDemo1)', function (data) {
                        console.log(data.field);
                        $.ajax({
                            url: '/user/update',
                            data: JSON.stringify(data.field),
                            type: 'PUT',
                            contentType: 'application/json',
                            success: data => {
                                if (data.count >0) {
                                    layer.msg('更新成功', { icon: 6 });
                                    layer.close(index);
                                    getData();
                                } else {
                                    layer.msg('更新失败', { icon: 6 });
                                    layer.close(index);
                                }

                            },


                        })
                        return false;
                    })

                })

            })
            var index = layer.open({
                type: 1,
                title: '用户信息',
                area: ['500px', '500px'],
                content: $('.updateUser'),
            });
            $('.save').click(function () {
                console.log(1);

            })

        });


    exports('user',{});

});