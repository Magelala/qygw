// jQuery代码

//当滚动条的位置处于距顶部280像素以下时，跳转链接出现，否则消失
$(function () {
    $(window).scroll(function(){
        if ($(window).scrollTop() >= 280) {
            $(".back-top").fadeIn(1000);
        }else{
            $(".back-top").fadeOut(1000);
        }
    });
    //当点击跳转链接后，回到页面顶部位置
    $(".back-top").click(function(){
        $('body,html').animate({scrollTop:0},1000);
        return false;
    });
});