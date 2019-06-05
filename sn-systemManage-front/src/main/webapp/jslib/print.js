//关于打印的 js
// @Troy 2014-09-05 13:01:07
function responsePrint(){
		
	    var content = $("#report_content").html() ;
		
		if(content.trim() == ""){
			$.messager.show({
				title:'提示',
				msg:'没有数据可打印',
				timeout:5000,
				showType:'slide'
			});
			return ;
		}
	
		 var mode = 'popup' ;
		 var close = 'false' ;
		 var popHt = '750' ;
		 var popWd = '1000' ;
		 var retainAttr = ['id', 'class', ''] ;
		 var extraHead = "安徽省万事通金卡通科技信息服务有限公司" ; 
		 
		 var options = { mode : mode, popClose : close, popHt:popHt, popWd:popWd, retainAttr:retainAttr, 
				 		 extraHead:extraHead}; 
		 
		 $("#report_content").addClass("printDiv").printArea(options) ;
         $("#report_content").removeClass("printDiv") ;
	}

// 打印 div 中的内容
function printDiv(area){
	
		var content = area.html() ;
		
		if(content.trim() == ""){
			$.messager.show({
				title:'提示',
				msg:'没有数据可打印',
				timeout:5000,
				showType:'slide'
			});
		}	
	
	var mode = 'popup' ;
	 var close = 'false' ;
	 var popHt = '750' ;
	 var popWd = '1000' ;
	 var retainAttr = ['id', 'class', ''] ;
	 var extraHead = "" ; 
	 
	 var options = { mode : mode, popClose : close, popHt:popHt, popWd:popWd, retainAttr:retainAttr, 
			 		 extraHead:extraHead}; 
	 
	 area.printArea(options) ;
}