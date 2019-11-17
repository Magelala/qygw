layui.define(['element','layer','form','jquery','table','laytpl'],function (exports) {
    var element =layui.element,
        table = layui.table,
        laytpl = layui.laytpl,
        form = layui.form,
        $ = layui.jquery,
        layer = layui.layer;




    var getDate =  $.get('/role/show',data=>{


        date = data.data;

        //监听提交
        form.on('submit(adformDemo)', function (data) {

            var str = data.field;
            var da = str.name;
            var art = str.name3_;
            var  adv = str.name4_5_
            var user = str.name7_8_9_;
            var page = str.name6_11_12_;

            var menuIds ='';
            if(da !=='' && da !==undefined){
                menuIds +=da;
            }
            if(art !=='' && art !==undefined){

                menuIds +=art;
            }
            if(adv !=='' && adv !==undefined){
                menuIds +=adv;

            }

            if(user !=='' && user !==undefined){
                menuIds +=user;
            }

            if(page !=='' && page!==undefined){
                menuIds +=page;
            }


            console.log("================================");
           var adId= date[2].id;
           var uId= date[1].id;

            if(menuIds ==''){
                layer.msg('权限不能为空', { icon: 6 });
                return false;
            }

           $.ajax({
                url: '/menu/addmenuRole?roleId='+adId+"&menuIds="+menuIds,
                type: 'post',
                success:data => {
                    if (data.count>0) {
                        layer.msg('更新成功', { icon: 6 });

                        parent.window.location.reload();
                    } else {
                        layer.msg('更新失败', { icon: 6 });

                        layer.close(index);
                    }

                },
            });


            return false;
        });

        // 监听提交
        form.on('submit(edformDemo)', function (data) {

            var str = data.field;
            var da = str.name;
            var art = str.name3_;
            var  adv = str.name4_5_
            var user = str.name7_8_9_;
            var page = str.name6_11_12_;

            var menuIds ='';
            if(da !=='' && da !==undefined){
                menuIds +=da;
            }
            if(art !=='' && art !==undefined){

                menuIds +=art;
            }
            if(adv !=='' && adv !==undefined){
                menuIds +=adv;

            }

            if(user !=='' && user !==undefined){
                menuIds +=user;
            }

            if(page !=='' && page!==undefined){
                menuIds +=page;
            }

            console.log(menuIds);

            console.log("================================");
            var uId= date[1].id;

            if(menuIds ==''){
                layer.msg('权限不能为空', { icon: 6 });
                return false;
            }

            $.ajax({
                url: '/menu/addmenuRole?roleId='+uId+"&menuIds="+menuIds,
                type: 'post',
                success: data => {
                    if (data.count>0) {
                        layer.msg('更新成功', { icon: 6 });
                        parent.window.location.reload();
                    } else {
                        layer.msg('更新失败', { icon: 6 });

                        layer.close(index);
                    }

                },
            });


            return false;
        });


    });










        exports('menu',{});

});