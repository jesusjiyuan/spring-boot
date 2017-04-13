/**
 * Created by Mario.Hu on 23/02/2017.
 */
$(function () {
    $.ajax({
        url: "/information/log",
        type: "POST",
        dataType: "html",
        data: {"time": new Date().getTime()},
        success: function (data) {
            $("#information_left").html(data);
        },
        error: function () {
            alert("连接失败，请联系管理员");
        }
    });
});
$(document).ready(function () {
    $("#fwq").click(function () {
        $("li").removeClass("active");
        $("#fwq").addClass("active");
        $.ajax({
            url: "/information/log",
            type: "POST",
            dataType: "html",
            data: {"time": new Date().getTime()},
            success: function (data) {
                $("#information_left").html(data);
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#person").click(function () {
        $("li").removeClass("active");
        $("#person").addClass("active");
        $.ajax({
            url: "/information/person",
            type: "POST",
            dataType: "html",
            data: {"time": new Date().getTime()},
            success: function (data) {
                $("#information_left").html(data);
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#send").click(function () {
        $("li").removeClass("active");
        $("#send").addClass("active");
        $.ajax({
            url: "/information/send",
            type: "POST",
            dataType: "html",
            data: {"time": new Date().getTime()},
            success: function (data) {
                $("#information_left").html(data);
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    })
    $("#upload").click(function () {
        $("li").removeClass("active");
        $("#upload").addClass("active");
        $.ajax({
            url: "/information/upload",
            type: "POST",
            dataType: "html",
            data: {"time": new Date().getTime()},
            success: function (data) {
                $("#information_left").html(data);
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    })
});