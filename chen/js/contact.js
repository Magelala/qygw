//javaScript代码
var listLi = document.getElementsByClassName('choice-item');
for (var i = 0; i < listLi.length; i++) {
    (function(j){
        listLi[j].getElementsByTagName('p')[0].onclick = function(e){ 
            var p = listLi[j].getElementsByTagName('p')[0];
            var ul = listLi[j].getElementsByTagName('ul')[0];
            var ulLi = ul.getElementsByTagName('li');
            if (ul.style.display == "none") {
                //显示ul列表
                for (var m = 0; m < listLi.length; m++) {
                    listLi[m].getElementsByTagName('ul')[0].style.display = "none";
                }
                ul.style.display = "block";

                //点击ul列表中的li选择
                for (var n = 0; n < ulLi.length; n++) {
                    (function (i) {
                        ulLi[i].onclick = function () {
                            p.innerHTML = ulLi[i].innerHTML;
                            ul.style.display = "none";
                        }
                    })(n);
                }

                //点击页面其他地方时，隐藏ul列表
                document.onclick = function () {
                    ul.style.display = "none";
                }
                e.cancelBubble = true;//阻止冒泡
            }else{
                ul.style.display = "none";
            }
        }
    })(i);
}

