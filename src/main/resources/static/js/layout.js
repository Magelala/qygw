$(document).ready(function () {
   //文章tab切换
    $(".nav-tab-ul li").click(function () {
        var _index =$(this).index();
        $("#tab-main>div").eq(_index).show().siblings().hide();
        $(this).addClass("current").siblings().removeClass("current");
    })
});

//test for git