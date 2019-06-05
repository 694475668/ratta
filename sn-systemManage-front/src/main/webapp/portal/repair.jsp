<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<div class="easyui-layout" data-options="fit : true,border : false" style="border:solid 0px red;">
 <div data-options="region:'center',border:false" style="fit:true;border:solid 0px blue;">
     <table class="easyui-datagrid" data-options="fit:true,rownumbers:true,stripted:true,idField : 'id',singleSelect:true,collapsible:true,url:'${pageContext.request.contextPath}/liquidationController/tablespaceInfo',method:'get'">
		 <thead>
			<tr>
				<th data-options="field:'tablespacename',width:120,align:'center',halign:'center'">表空间名称</th>  
				<th data-options="field:'totalsize',width:100,align:'center',halign:'center'">总大小</th>  
				<th data-options="field:'freesize',width:100,align:'center',halign:'center'">可用大小</th>  
				<th data-options="field:'usedsize',width:100,align:'center',halign:'center'">已使用</th>
				<th data-options="field:'usedpercent',width:100,align:'center',halign:'center'">使用百分比</th>
			</tr>
		</thead>
     </table>
 </div>
</div>
