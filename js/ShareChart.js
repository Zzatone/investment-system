var his1=sessionStorage.getItem("D1");
var his2=sessionStorage.getItem("D2");
var his3=sessionStorage.getItem("D3");
var his4=sessionStorage.getItem("D4");
var his5=sessionStorage.getItem("D5");
var Chart=echarts.init(document,getElemenById(''))
window.onload=function () {
    option={
        title:{
            text:'近五日收盘值'
        },
        tooltip:{},
        legend:{
            data:["日收"]
        },
        xAxis:{
            data:["D5","D1","D3","D2","D1"]
        },
        yAxis:{},
        series:[{
            name:'日收',
            type:'line',
            data:[his5,his4,his3,his2,his1]
        }]
    };

}