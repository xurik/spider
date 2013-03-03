function search(id){
    $("#siteId").val(id);
    $('#searchList').jqGrid('setGridParam',{
        url:'search/'+id+'/list.j',
        editurl:'search/'+id+'/save.j',
        datatype: "json"
    }).trigger('reloadGrid');
    $( "#tabs" ).tabs("select","tabs-2");

}
function build(id){
    $("#b_"+id).attr("disabled","disabled");
    $.post('spider/site/'+id+'.j',function(){
        alert("处理完毕");
        $("#b_"+id).removeAttr("disabled");
    });
}
(function($){
    $(document).ready(function(){

        function formatterSearch(cellvalue, options, rowObject){
            var result = '<button onclick="search('+rowObject.id+')">查看搜索</button>';
            result += '<button onclick="build('+rowObject.id+')" id=b_'+rowObject.id+'>爬取</button>';
            return result;
        }
        jQuery("#siteList").jqGrid({
            url:'site/list.j',
            mtype:'POST',
            datatype: "json",
            colModel:[
                {name:'id',index:'id', lable:"ID",width:55,editable:true},
                {name:'uuid',index:'uuid', label:"唯一标识",width:90},
                {name:'name',index:'name', label:"中文名",width:90,editable:true},
                {name:'domain',index:'domain', label:"站点",width:90,editable:true},
                {name:'status',index:'status', label:"状态",width:90,editable:true},
                {name:'rate',index:'rate', label:"访问间隔",width:90,editable:true},
                {name:'random',index:'random', label:"随机数",width:90,editable:true},

                {name:'gmtCreate',index:"gmtCreate",label:'创建时间', width:100},
                {name:'gmtModified',index:"gmtModified",label:'修改时间', width:80, align:"right"},
                {name:'creator',index:"creator",label:'创建人', width:80, align:"right"},
                {name:'modified',index:"modified",label:'最后修改人', width:80,align:"right"},
                {name:'searchEntityList',index:"id",label:'操作', width:150,align:"right",formatter:formatterSearch}

            ],
            rowNum:3000,
            rowList:[3000],
            height:500,
            pager: '#sitePager',
            jsonReader: {
                repeatitems : false,
                id: "id"
            },
            sortname: 'id',
            viewrecords: true,
            editurl:"site/save.j"
        });
        jQuery("#siteList").jqGrid('navGrid','#sitePager',{edit:true,add:true,del:true});
        $("#spider").click(function(){
            $(this).attr("disabled","disabled");
            $.post('spider/all.j',function(){
                alert("处理完毕");
                $(this).removeAttr("disabled");
            });
        });

        $("#import").click(function(){
            $(this).attr("disabled","disabled");
            $.post('import/all.j',function(){
                alert("处理完毕");
                $(this).removeAttr("disabled");
            });
        })
    });
})(jQuery);
