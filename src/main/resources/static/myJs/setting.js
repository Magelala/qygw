//引入element,文件上传upload组件
layui.define(['element','form','jquery'],function (exports) {
    var element = layui.element,
        $ = layui.jquery,
        form = layui.form;

    form.render();


    // 修改个人资料
    $('.update').on('click',function () {
        var js ={
            id:$('#id').val(),
            userName:$('#userName').val(),
            password:$('#password').val(),
            name:$('#name').val(),
            tel:$('#tel').val(),
            email:$('#email').val(),
            role:$('#role').val(),
            picture:$('#picture').val(),
            context:$('#context').val()
        }

        $.ajax({
            url: '/setting/update',
            data: JSON.stringify(js),
            type: 'PUT',
            contentType: 'application/json',
            success: data => {
                if (data.count>0) {
                    layer.msg('更新成功', { icon: 6 });
                    layer.close(index);
                } else {
                    layer.msg('更新失败', { icon: 6 });
                    layer.close(index);
                }
            }
        });




    });

    // 修改密码
    $('#updatePwd').on('click',function () {
        var password = $("#LAY_password").val();
        var oldPassword = $('#oldPassword').val();
        var repassword = $('#repassword').val();
        console.log(password);
        console.log(oldPassword);
        console.log(repassword);

        // 参数不能为空
        if(password!=null&&oldPassword !=null&&repassword!=null&& password!=''&&oldPassword !=''&&repassword!='' ){

            // 校验输入的新密码和确认密码是否相符合
            if(password === repassword){

                // 将新旧密码传到后台
                $.ajax({
                    url: '/setting/updatePassword?password=' + password + '&oldPassword=' + oldPassword,
                    type: 'POST',
                    contentType: 'application/json',
                    success: data => {
                        if (data.count > 0) {
                            layer.msg('修改成功,请使用新密码重新登录', {icon: 6});
                            parent.window.location.reload();
                        } else {
                            layer.msg(data.msg, {icon: 6})

                        }

                    }
                });

            }else{
                //不符合，提示用户

                layer.msg('确认密码不正确', { icon: 6 });
            }
        }else {
            layer.alert("密码不能为空");
        }

    });

    exports('setting',{});



});
