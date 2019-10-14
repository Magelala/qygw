layui.define(['element','table','form','upload','layedit','jquery','layer'],function (exports) {
    var element = layui.element,
        table = layui.table,
        upload = layui.upload,
        form = layui.form,
        layedit = layui.layedit,
        $ = layui.jquery,
        layer = layui.layer;

    layedit.build("demo"); //建立编辑器

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
                ,{field: 'author',title: '作者',width:80}
                ,{field: 'summary', title: '摘要'}
                ,{field: 'classify',title: '分类',width:80}
                ,{field: 'status',title:'状态',width:80}
                ,{field: 'createByDate', title: '创建时间',align:'center',width:120}
                ,{field: 'views',title: '访问量',width:100}
                ,{field: 'sort',title:'排序',width: 80}
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

    exports('article',{});
});