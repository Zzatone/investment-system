var i=1;//表示当前图片所在位置
var time = setTimeout("showImg()", 2000);//启动时钟事件刷新时间 1000==1秒
function icon_left(){
    clearTimeout(time)//清楚时钟事件
    if(i<=1){
        i=4;
    }else{
        i=i-2;
    }
    console.info(i)
    showImg()
}
function icon_right(){
    clearTimeout(time)//清楚时钟事件
    showImg()
}
function showImg() { //让背景图片显示
    i++;
    if(i == 3) {
        i = 1;
    }
    document.getElementById("box").style.background = "url( images/banner_0" + i + ".jpg) no-repeat";
    document.getElementById("box").style.backgroundSize = "contain";
    //通过id获取标签并修改背景样式
    time = setTimeout("showImg()", 2000);//启动时钟事件刷新时间 1000==1秒
}



