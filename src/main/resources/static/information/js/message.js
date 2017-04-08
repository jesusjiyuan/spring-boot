/**
 * Created by Mario.Hu on 24/02/2017.
 */
$(document).ready(function () {
    $("button").click(function () {
        $.ajax({
            url: "/judement/send",
            type: "POST",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({
                message: $("input").val()
            })
        });
        $("input").val("");
    })
});