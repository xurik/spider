function item(id){
    $("#listId").val(id);
    $('#itemList').jqGrid('setGridParam',{
        url:'item/'+id+'/list.j',
        editurl:'item/'+id+'/save.j',
        datatype: "json"
    }).trigger('reloadGrid');
    $( "#tabs" ).tabs("select","tabs-4");

}

(function($){
    $(document).ready(function(){
        function formatterItem(cellvalue, options, rowObject){
            var result = '<button onclick="item('+rowObject.id+')">查看商品</button>';
            return result;
        }
        jQuery("#listList").jqGrid({
            mtype:'POST',
            datatype: "local",
            colModel:[
                {name:'id',index:'id', lable:"ID",width:55,editable:true},
                {name:'uuid',index:'uuid', label:"唯一标识",width:120},
                {name:'url',index:'url', label:"地址",width:150,editable:true},

                {name:'gmtCreate',index:"gmtCreate",label:'创建时间', width:100},
                {name:'gmtModified',index:"gmtModified",label:'修改时间', width:80, align:"right"},
                {name:'creator',index:"creator",label:'创建人', width:80, align:"right"},
                {name:'modified',index:"modified",label:'最后修改人', width:80,align:"right"},
                {name:'listEntityList',index:"id",label:'操作', width:80,align:"right",formatter:formatterItem}
            ],
            rowNum:30000,
            rowList:[30000],
            pager: '#listPager',
            jsonReader: {
                repeatitems : false,
                id: "id"
            },
            height:500,
            sortname: 'id',
            viewrecords: true
        });
        jQuery("#listList").jqGrid('navGrid','#listPager',{edit:true,add:true,del:true});
    });
})(jQuery);
