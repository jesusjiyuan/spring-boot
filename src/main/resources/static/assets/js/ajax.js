/**
 * Created by Mario.Hu on 23/02/2017.
 */
$(document).ready(function(){
    $("#l").click(function(){
        if($("#number").val().length <= 5 || $("#password").val().length <= 5){
            $("#welcome").html("密码错误");
            $("#hint").hide();
        } else {
            $("#welcome").html("登录中...");
            $.ajax({
                url:"judement/login",
                type:"POST",
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                data:JSON.stringify({
                    number:$("#number").val(),
                    password:$("#password").val()
                }),
                success: function (data) {
                    var a = data.message;
                    if(a=="true"){
                        window.location.href='/index/main'
                    } else {
                        $("#welcome").html("密码错误");
                        $("#hint").hide();
                    }
                },
                error: function () {
                    alert("连接失败，请联系管理员");
                }
            });}
    });
})