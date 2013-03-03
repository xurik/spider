<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/jquery-ui/redmond/jquery-ui-1.9.2.custom.min.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/jqgrid/ui.jqgrid.css" type="text/css" media="screen" />
    <script type="text/javascript" src="js/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript" src="js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="js/spider/index.js"></script>
    <script type="text/javascript" src="js/spider/site.js"></script>
    <script type="text/javascript" src="js/spider/search.js"></script>
    <script type="text/javascript" src="js/spider/list.js"></script>
    <script type="text/javascript" src="js/spider/item.js"></script>
    <title>demo</title>
</head>
<body style="height: 100%;">
<div id="tabs"  style="height: 620px;">
    <ul>
        <li><a href="#tabs-1">站点</a></li>
        <li><a href="#tabs-2">搜索</a></li>
        <li><a href="#tabs-3">列表</a></li>
        <li><a href="#tabs-4">商品</a></li>
    </ul>
    <div id="tabs-1">
        <div>
            <button>爬取页面</button>
            <button>生成数据</button>
        </div>
        <div>
            <form id="siteQueryForm">
            </form>
        </div>
        <div>
            <table id="siteList"></table>
            <div id="sitePager"></div>
        </div>
    </div>
    <div id="tabs-2" style="">
        <div>
            <form>
                <input id="siteId" type="hidden" name="siteId" />
            </form>
        </div>
        <div>
            <table id="searchList"></table>
            <div id="searchPager"></div>
        </div>
    </div>
    <div id="tabs-3" style="">
        <div>
            <form>
                <input id="searchId" type="hidden" name="searchId" />
            </form>
        </div>
        <div>
            <table id="listList"></table>
            <div id="listPager"></div>
        </div>
    </div>
    <div id="tabs-4" style="">
        <div>
            <form>
                <input id="listId" type="hidden" name="listId" />
            </form>
        </div>
        <div>
            <table id="itemList"></table>
            <div id="itemPager"></div>
        </div>
    </div>
</div>
</body>
</html>
