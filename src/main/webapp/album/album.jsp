<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {
        $('#ablum_query').treegrid({
            url: '${pageContext.request.contextPath}/album/queryAll',
            idField: 'id',
            treeField: 'name',
            columns: [[
                {title: '名字', field: 'name', width: 180},
                {field: 'url', title: '下载路径', width: 60, align: 'right'},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'duration', title: '章节时长', width: 80}
            ]],
            fit: true,
            fitColumns: true,
            toolbar: [{
                text: "专辑详情",
                iconCls: 'icon-edit',
                handler: function () {

                    // alert('专辑详情')
                    var a = $('#ablum_query').treegrid("getSelected");
                    if (a != null) {
                        if (a.id.length == 36) {
                            $.messager.alert('提示消息', '请选择一级目录！', 'info');

                        } else {
                            //console.log(a)
                            $('#ablum_particular').dialog('open');
                            $("#ablum_name1").html("标题：" + a.name);
                            $("#ablum_coverImg1").prop("src", "${pageContext.request.contextPath}/img/" + a.coverImg);
                            $("#ablum_count1").html("音频有" + a.count);
                            $("#ablum_score1").html("评分：" + a.score);
                            $("#ablum_author1").html("作者：" + a.author);
                            $("#ablum_broadCast1").html("播音：" + a.broadCast);
                            $("#ablum_brief1").html(a.brief);
                        }
                    } else {
                        $.messager.alert('提示消息', '请选择一级目录！', 'info');
                    }


                }
            }, '-', {
                text: "添加专辑",
                iconCls: 'icon-help',
                handler: function () {
                    $('#ablum_add').dialog('open');
                }
            }, '-', {
                text: "添加章节",
                iconCls: 'icon-help',
                handler: function () {
                    var a = $('#ablum_query').treegrid("getSelected");
                    if (a != null) {
                        if (a.id.length == 36) {
                            $.messager.alert('提示消息', '请选择一级目录！', 'info');
                        }else{
                            $('#chapter_add').dialog('open');
                           //console.log(a.id)
                            $("#name2").val(a.id);
                            $("#adminCount").val(a.count);
                            //console.log(a.id)
                        }

                    } else {

                        $.messager.alert('提示消息', '请选择一级目录！', 'info');
                    }
                }
            }, '-', {
                text: "下载音频",
                iconCls: 'icon-help',
                handler: function () {
                    var a = $('#ablum_query').treegrid("getSelected");
                    if (a != null) {
                        if (a.id.length == 36) {
                            window.location.href="${pageContext.request.contextPath}/chapter/downloadChapter?url="+a.url;

                        }else{

                            $.messager.alert('提示消息', '请选择二级目录！', 'info');
                        }

                    } else {
                        $.messager.alert('提示消息', '请选择二级目录！', 'info');
                    }
                }

            }
            ],
            onDblClickRow:function(row){

                $("#audioCha").prop("src","${pageContext.request.contextPath}/"+row.url);
               var f= $("#audioCha").prop("src");
                console.log(f);
                $("#chapter_Audio").dialog("open")
            }

        });

    })

    function formAlbum() {
        $('#ablum_form').form('submit', {
            url: "${pageContext.request.contextPath}/album/insert",
            type:'post' ,
            success: function(){
                // $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $('#ablum_add').dialog('close');
                $('#ablum_query').treegrid('reload');
            }
        });

    };

    function formChapter() {
        $('#chapter_addForm').form('submit', {
            url:"${pageContext.request.contextPath}/chapter/insert",
            type:'post' ,
            success: function(){
               // $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $('#chapter_add').dialog('close');
                $('#ablum_query').treegrid('reload');
            }

        });

    };
</script>


<table id="ablum_query"></table>

<div id="ablum_particular" class="easyui-dialog" title="详细信息" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true, closed:true,buttons:[{
				text:'关闭',
				handler:function(){
                    $('#ablum_particular').dialog('close');
				}
			}]">
    <div>
        <p id="ablum_name1"></p>
        <p id="ablum_coverImg"></p><
        <img src="" id="ablum_coverImg1">
        <p id="ablum_count1"></p>
        <p id="ablum_score1"></p>
        <p id="ablum_author1"></p>
        <p id="ablum_broadCast1"></p>
        <p style="align-content: center">简介</p>
        <p id="ablum_brief1"></p>
    </div>
</div>


<div id="ablum_add" class="easyui-dialog" title="添加专题" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true, closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    formAlbum();

				}
			},{
			text:'关闭',
				handler:function(){
                    $('#ablum_add').dialog('close');
				}
			}]">


    <form id="ablum_form" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">专题的名字:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
        </div>
        <div>
            <label for="author">专题的作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="validType:true"/>
        </div>
        <div>
            <label for="broadCast">播音:</label>
            <input id="broadCast" class="easyui-validatebox" type="text" name="broadCast" data-options="required:true"/>
        </div>
        <div>
            <label for="brief">描述:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-options="validType:true"/>
        </div>
        <div>
            封面图片 <input class="easyui-filebox" name="fileName" style="width:300px">
        </div>
    </form>
</div>

<div id="chapter_add" class="easyui-dialog" title="添加音频" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true, closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    formChapter();

				}
			},{
			text:'关闭',
				handler:function(){
                    $('#chapter_add').dialog('close');
				}
			}]">


    <form id="chapter_addForm" method="post" enctype="multipart/form-data">
        <div>

            <input id="name2" class="easyui-validatebox" type="hidden" name="adminFa.id" />
            <input id="adminCount" class="easyui-validatebox" type="hidden" name="adminFa.count" />
        </div>
        <div>
            <label for="name1">专题的名字:</label>
            <input id="name1" class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
        </div>

        <div>
            封面图片 <input class="easyui-filebox" name="fileName" style="width:300px">
        </div>
    </form>
</div>


<div id="chapter_Audio" class="easyui-dialog" title="添加音频" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true, closed:true,buttons:[{
				text:'保存',
				handler:function(){


				}
			},{
			text:'关闭',
				handler:function(){
                    $('#chapter_Audio').dialog('close');
				}
			}]">

    <audio src="" id="audioCha" controls="controls" autoplay="autoplay"></audio>

</div>





