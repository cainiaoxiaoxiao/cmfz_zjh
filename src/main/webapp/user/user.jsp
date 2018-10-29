<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#user_dg').datagrid({
            url: '${pageContext.request.contextPath}/user/queryAll',
            columns: [[
                {field: 'id', title: '编号', width: 100},
                {
                    field: 'headPic', title: '头像', width: 100, formatter: function (value, row, index) {

                    return "<img src='${pageContext.request.contextPath}/img/" + row.headPic + "' width='25px' height='25px'>";
                }
                },
                {field: 'dhamaName', title: '法号', width: 100, align: 'right'},
                {field: 'name', title: '名字', width: 100, align: 'right'},
                {field: 'sex', title: '性别', width: 100, align: 'right'},
                {field: 'province', title: '省份', width: 100, align: 'right'},
                {field: 'city', title: '城市', width: 100, align: 'right'},
                {field: 'sign', title: '签名', width: 100, align: 'right'},
                {field: 'phoneNum', title: '手机号', width: 100, align: 'right'},
                {field: 'regDate', title: '注册时间', width: 100, align: 'right'},
            ]],
            fit: true,
            fitColumns: true,
            toolbar: [{
                iconCls: 'icon-edit',
                text: "全部导出",
                handler: function () {
                    window.location.href = "${pageContext.request.contextPath}/user/exportUser";
                }
            }, '-', {
                iconCls: 'icon-help',
                text: "全部导入",
                handler: function () {

                    $("#user_import").dialog("open");
                }
            }, '-', {
                iconCls: 'icon-help',
                text: "自定义导出",
                handler: function () {
                    $("#user_dd").dialog("open");
                }
            }]

        });

        //设置自定义提交按钮
        $("#btn").linkbutton({
            onClick: function () {
                var titles = $("#user_cc").combotree("getText")
                var fileds = $("#user_cc").combotree("getValues")

                var a="";
                $.each(fileds, function (index, filed) {
                    if (index==fileds.length-1){
                        a+=filed;
                    }else {
                        a+=filed+",";
                    }
                })
                $("#user_ff").form('submit',{
                    url:"${pageContext.request.contextPath}/user/customerExport",
                    queryParams:{
                        titles:titles,
                        fileds:a
                    }
                })



            }
        })
    });
    //上传exs文件
    function importUser() {
        $('#user_form').form('submit', {
            url: "${pageContext.request.contextPath}/user/importUser",
            success:function(){
                $('#user_import').dialog('close')
                $('#user_dg').datagrid('reload');
            }
        });
    }
    //上传exs文件================END=======================

</script>


<%--展示用户--%>
<table id="user_dg"></table>

<div id="user_import" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    importUser();
				}
			},{
				text:'关闭',
				handler:function(){$('#user_import').dialog('close');}
			}]">
    <form id="user_form" method="post" enctype="multipart/form-data" >
        请上传你的exs文件：<input class="easyui-filebox" style="width:300px" name="excel">
    </form>
</div>

<%--自定义导出--%>
<div id="user_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="text-align: center">
        <select id="user_cc" class="easyui-combotree" style="width:200px;" data-options="required:true,checkbox:true,multiple:true,onlyLeafCheck:true,
data:[{
		text: '请选择',
		state: 'closed',
		children: [{
		    id:'id',
			text: '编号'
		},{
		id:'dhamaName',
			text: '法号'
		},{
		id:'name',
			text: '名字'
		},{
		id:'sex',
			text: '性别'
		},{
		id:'province',
			text: '省份'
		},{
		id:'city',
			text: '城市'
		},{
		id:'phoneNum',
			text: '手机号'
		},{
		id:'regDate',
			text: '注册日期'
		}]
	}]"></select>
        <form id="user_ff" method="post">

            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a>

        </form>
    </div>

</div>