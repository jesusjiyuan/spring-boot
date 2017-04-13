/**
 * Created by Mario.Hu on 09/04/2017.
 */
$(document).ready(function()
{
    $("#login").hide();
    jQuery.fn.extend({
        center:function(width,height)
        {
            return $(this).css("left", ($(window).width()-width)/2+$(window).scrollLeft()).
            css("top", ($(window).height()-height)/2+$(window).scrollTop()).
            css("width",width).
            css("height",height);
        }
    });

    $("#edit").click(function ()
    {
        $("#login").show().center(350,250);//展现登陆框
    });

    $("#close").click(function ()
    {
        $("#login").hide();
    });

    $("#queren").click(function ()
    {
        $.ajax({
            url: "/judement/edit.json",
            type: "POST",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({
                shebei: $("#shebei").val(),
                banben: $("#banben").val()
            }),
            success: function (data) {
                if(data.message =="true"){
                    alert("成功提交修改到后台，感谢您的参与，稍后官方会赠与您一台兰博基尼，请注意查收")
                    window.location.href='/'
                } else {
                    alert("后台较忙")
                }
            }
        });
    });
});

