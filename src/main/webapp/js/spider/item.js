(function($){
    $(document).ready(function(){
        jQuery("#itemList").jqGrid({
            mtype:'POST',
            datatype: "local",
            colModel:[
                {name:'id',index:'id', lable:"ID",width:55,editable:true},
                {name:'uuid',index:'uuid', label:"唯一标识",width:120},
                {name:'url',index:'url', label:"地址",width:150,editable:true},

                {name:'gmtCreate',index:"gmtCreate",label:'创建时间', width:100},
                {name:'gmtModified',index:"gmtModified",label:'修改时间', width:80, align:"right"},
                {name:'creator',index:"creator",label:'创建人', width:80, align:"right"},
                {name:'modified',index:"modified",label:'最后修改人', width:80,align:"right"}
            ],
            rowNum:30000,
            rowList:[30000],
            pager: '#itemPager',
            jsonReader: {
                repeatitems : false,
                id: "id"
            },
            height:500,
            sortname: 'id',
            viewrecords: true
        });
        jQuery("#itemList").jqGrid('navGrid','#itemPager',{edit:true,add:true,del:true});
    });
})(jQuery);
