$(function () {
        $("#tree").tree({
            url : '/allright-git/json/treeData.json',
            lines : true,
            onClick : function (node) {
                if (node.attributes) {
                    openTab(node.text, node.attributes.url);
                }
            }
        });
        $("#tree1").tree({
            url : '/allright-git/json/systemtreedate.json',
            lines : false,
            onClick : function (node) {
                if (node.attributes) {
                    openTab(node.text, node.attributes.url);
                }
            }
        });
        function openTab(title, url) {
            if ($("#tabs").tabs('exists', title)) {  
                $('#tabs').tabs('select', title);
            } else {
                $('#tabs').tabs('add', {
                    title : title,
                    closable : true,
                    content : createTabContent(url)
                });
            }
        }
     
        function createTabContent(url){
            return '<iframe style="width:100%;height:98%;" scrolling="auto" frameborder="0" src="' + url + '"></iframe>';
        }
 
        $("#tabs").tabs({
            onContextMenu : function (e, title) {
                e.preventDefault();
                $('#tabsMenu').menu('show', {
                    left : e.pageX,
                    top : e.pageY
                }).data("tabTitle", title);
            }
        });
         
        $("#tabsMenu").menu({
                onClick : function (item) {
                CloseTab(this, item.name);
            }
        });
         
        function CloseTab(curTab, itemName) {
            var curTabTitle = $(curTab).data("tabTitle");
            var allTabs = $("#tabs").tabs("tabs");
            var allTabsTitle = [];
            if (itemName === "close") {
                $("#tabs").tabs("close", curTabTitle);
                return;
            }       
            $.each(allTabs, function () {
                var optTab = $(this).panel("options");
                if (optTab.closable && optTab.title != curTabTitle && itemName === "closeother") {
                    allTabsTitle.push(optTab.title);
                } else if (optTab.closable && itemName === "closeall") {
                    allTabsTitle.push(optTab.title);
                }
            }); 
       
            for (var i = 0; i < allTabsTitle.length; i++) {
                $("#tabs").tabs("close", allTabsTitle[i]);
            }
        }
        
        changeTheme=function (themeName) {
            var $easyuiTheme = $('#easyuiTheme');
            var url = $easyuiTheme.attr('href');
            var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
            $easyuiTheme.attr('href', href);
            var $iframe = $('iframe');
            if ($iframe.length > 0) {
                for (var i = 0; i < $iframe.length; i++) {
                    var ifr = $iframe[i];
                    $(ifr).contents().find('#easyuiTheme').attr('href', href);
                }
            }
        };  
});