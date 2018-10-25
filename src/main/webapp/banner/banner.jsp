<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">

    $(function () {
        $('#dg').edatagrid({
            url:'${pageContext.request.contextPath}/banner/bannerALl',
            updateUrl:"${pageContext.request.contextPath}/banner/update",
            fit: true,
            fitColumns: true,
            pagination: true,
            singleSelect:true,
            columns: [[
                {field: 'name', title: '名字', width: 100},
                {
                    field: 'status', title: '状态(/N)', width: 100,
                    formatter: function (value, row, index) {
                        // alert(row.status)
                        if (row.status == 0) {
                            return "Y";
                        } else {
                            return "N";
                        }
                    }, editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }

                },
                {
                    field: 'createDate', title: '上传时间', width: 100, align: 'right',
                },
                {field: 'description', title: '描述', width: 100, align: 'right'}
            ]],
            toolbar: [{
                iconCls: 'icon-add',
                text: "添加",
                handler: function () {
                   // alert('添加按钮')
                    $("#addIn").dialog("open");

                }
            }, '-', {
                iconCls: 'icon-edit',
                text: "修改",
                handler: function () {
                    var row = $("#dg").edatagrid("getSelected");
                    if (row == null) {
                        $.messager.alert('修改警告','请选择一行要修改的数据！','info');
                    } else {
                        var index = $("#dg").edatagrid("getRowIndex", row);
                        $("#dg").edatagrid("editRow", index);
                    }
                }
            }, '-', {
                iconCls: 'icon-remove',
                text: "删除",
                handler: function () {
                    var row = $("#dg").edatagrid("getSelected");
                    if(row==null){
                        $.messager.alert('删除警告','请选择一行要删除的数据！','info');
                    }else{

                        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                            if (r){
                                var row1 = $("#dg").edatagrid("getSelected");
                                $.ajax({
                                    url:"${pageContext.request.contextPath}/banner/delete?id="+row1.id,
                                    dateType:"json",
                                    success:function (data) {
                                        // alert(data)
                                        if(data){
                                            $.messager.show({
                                                title:'删除成功',
                                                msg:'消息将在5秒后关闭。',
                                                timeout:5000,
                                                showType:'slide'
                                            });

                                            $('#dg').datagrid('load');
                                        }else{
                                            $.messager.show({
                                                title:'删除失败',
                                                msg:'消息将在5秒后关闭。',
                                                timeout:5000,
                                                showType:'slide'
                                            });
                                        }
                                    }
                                })
                            }
                        });


                    }

                }
            }, '-', {
                iconCls: 'icon-save',
                text: "保存",
                handler: function () {
                  // alert("aaaaaaaaaaaaaaa")
                     $("#dg").edatagrid("saveRow")
                }
            }],

            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.url + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.description + '</p>' +
                    '<p>createDate: ' + rowData.createDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

        $("#addIn").dialog({
            title:"添加用户",
            closed:true,
            buttons:"#insert_but",
            width: 500,
            height: 250,
            modal:true,
        });

    });
    function insert_sub(){

        $("#onForm").form("submit",{
           url:"${pageContext.request.contextPath}/banner/insert",
            success:function(data){
                 //alert(data);
                if(data=="true"){
                    $.messager.show({
                        title:'添加消息',
                        msg:"添加成功",
                        timeout:5000,
                    });
                }else{
                    $.messager.show({
                        title:'添加消息',
                        msg:"添加失败",
                        timeout:5000,
                    });
                }
                $("#addIn").dialog("close");//关闭窗口
                $("#dg").datagrid("load");//刷新窗口
            }
        });
    }
</script>

<%--展示数据--%>
<table id="dg"></table>


<div id="addIn">
    <form method="POST"  enctype="multipart/form-data" id="onForm">
       名字 <input type="text" name="name" /><br/><br/>
       状态 <input type="radio" name="status" value="0" />显示
        <input type="radio" name="status" value="1"/>不显示<br/><br/>
        <input type="file" name="fileName" /><br/><br/>
        描述<input type="text" name="description" />
    </form>
</div>

<div id="insert_but">
    <a class="easyui-linkbutton" onclick="insert_sub()">提交</a>
    <a class="easyui-linkbutton" onclick="insert_quxiao()">取消</a>
</div>




