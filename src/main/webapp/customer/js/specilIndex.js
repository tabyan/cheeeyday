/**
 * Created by tabyan on 16-8-24.
 */
$(function() {
    $(".del").click(function(){
        var index = parseInt(this.getAttribute("data")) + 1;
        var objectid = document.getElementById("table").rows[index].cells[0].innerText;
        $('#delModal').modal('show');
        $("#delBtn").attr("data", objectid);
    });
    $(".edit").click(function(){
        var index = parseInt(this.getAttribute("data")) + 1;
        var objectid = document.getElementById("table").rows[index].cells[0].innerText;
        var name = document.getElementById("table").rows[index].cells[1].innerText;
        var beginTime = document.getElementById("table").rows[index].cells[2].innerText;
        var endTime = document.getElementById("table").rows[index].cells[3].innerText;
        $('#objectid').val(objectid);
        $('#name').val(name);
        $('#beginTime').val(beginTime);
        $('#endTime').val(endTime);
        $('#editSpecilCheeryDay').modal('show');
        $('.modal-title').html("编辑节假日");
        $("#beginTime").datetimepicker({
            format: 'yyyy-mm-dd',
            language:'zh-CN',
            autoclose: true,
            todayBtn: true,
            startView: 'year',
            minView:'month',
            maxView:'month'
        });
        $("#endTime").datetimepicker({
            format: 'yyyy-mm-dd',
            language:'zh-CN',
            autoclose: true,
            todayBtn: true,
            startView: 'year',
            minView:'month',
            maxView:'month'
        });
    });

    $(".add").click(function(){
        $('#objectid').val(0);
        $('#name').val("");
        $('#beginTime').val("");
        $('#endTime').val("");
        $('#editSpecilCheeryDay').modal('show');
        $('.modal-title').html("新增节假日");
        $("#beginTime").datetimepicker({
            format: 'yyyy-mm-dd',
            language:'zh-CN',
            autoclose: true,
            todayBtn: true,
            startView: 'year',
            minView:'month',
            maxView:'month'
        });
        $("#endTime").datetimepicker({
            format: 'yyyy-mm-dd',
            language:'zh-CN',
            autoclose: true,
            todayBtn: true,
            startView: 'year',
            minView:'month',
            maxView:'month'
        });
    });

    $("#delBtn").click(function () {
        var objectid = $("#delBtn").attr("data");
        $.ajax({
            url:"/specil/delete/"+objectid+".html",
            success:function (data) {
                location.reload();
                $('#delModal').modal('');
            }
        })
    })
    $("#addSpecilCheeryDay").click(function () {
        var objectid = $('#objectid').val();
        var beginTime = $('#beginTime').val();
        var endTime = $('#endTime').val();
        var name = $('#name').val();
        var data = {"objectid":objectid,"beginTime":beginTime,"name":name,"endTime":endTime}
        $.ajax({
            type: "post",
            url: "/specil/update.html",
            data: JSON.stringify(data),
            dataType:"json",
            contentType: "application/json",
            async:false,
            success: function(result) {
                if (result.success) {
                    console.log(result)
                    $('.bottom-right').notify({
                        message: {
                            text: result.msg,
                            type:"success"
                        }
                    }).show();
                    location.reload();
                }else {
                    $('.bottom-right').notify({
                        message: {
                            text: result.msg,
                            type:"success"
                        }
                    }).show();
                }
            }
        });
    });
});