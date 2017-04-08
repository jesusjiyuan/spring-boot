/**
 * Created by Mario.Hu on 23/02/2017.
 */
$(document).ready(function () {
    $('#people').on('click', 'a', function () {

    });
    $("#one").click(function () {
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
    });
    $("#two").click(function () {
        $.ajax({
            url: "view/two",
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
    });
    $("#four").click(function () {
        $.ajax({
            url: "view/four",
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
    });
    $("#five").click(function () {
        $.ajax({
            url: "/judement/depart",
            type: "POST",
            dataType: "html",
            data: {"time": new Date().getTime()},
            success: function () {
                window.location.href='/'
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#showman").click(function () {
        $.ajax({
            url: "/judement/counts",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data: {"time": new Date().getTime()},
            success: function (data) {
                $("#people").html("");
                for (var o in data) {
                    $("#people").append("<li><a href='#'>" + data[o] + "</a></li>");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#a").click(function () {
        $.ajax({
            url: "/judement/where",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                from:$("#a").html()
            }),
            success: function (data) {
                if(data[0]=="true"){
                    $.ajax({
                        url: "view/son/entity",
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
                } else {
                    alert("权限不足");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#b").click(function () {
        $.ajax({
            url: "/judement/where",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                from:$("#b").html()
            }),
            success: function (data) {
                if(data[0]=="true"){
                    alert(true);
                } else {
                    alert("权限不足");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#c").click(function () {
        $.ajax({
            url: "/judement/where",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                from:$("#c").html()
            }),
            success: function (data) {
                if(data[0]=="true"){
                    $.ajax({
                        url: "view/son/information",
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
                } else {
                    alert("权限不足");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#d").click(function () {
        $.ajax({
            url: "/judement/where",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                from:$("#d").html()
            }),
            success: function (data) {
                if(data[0]=="true"){
                    alert(true);
                } else {
                    alert("权限不足");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#e").click(function () {
        $.ajax({
            url: "/judement/where",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                from:$("#e").html()
            }),
            success: function (data) {
                if(data[0]=="true"){
                    alert(true);
                } else {
                    alert("权限不足");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
    $("#f").click(function () {
        $.ajax({
            url: "/judement/where",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                from:$("#f").html()
            }),
            success: function (data) {
                if(data[0]=="true"){
                    alert(true);
                } else {
                    alert("权限不足");
                }
            },
            error: function () {
                alert("连接失败，请联系管理员");
            }
        });
    });
});