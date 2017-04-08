/**
 * Created by Mario.Hu on 23/02/2017.
 */
$(function () {
    setInterval("getTime();", 1000); //每隔一秒执行一次
})
//取得系统当前时间
function getTime() {
    var myDate = new Date();
    var year = myDate.toLocaleString();
    $("#showDate").html(year); //将值赋给div
}
$.ajax({
    url: "view/one",
    type: "POST",
    dataType: "html",
    data: {"time": new Date().getTime()},
    success: function (data) {
        $("#showView").html(data);
    },
    error: function () {
        alert("连接失败，请联系管理员");
    }
});