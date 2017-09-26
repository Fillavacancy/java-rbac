<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../head/head.jsp"%>
<link href="${ctx }/assets/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css">
<title>${title }</title>
</head>
<body>
	<div id="wrapper">
		<!-- 侧边导航和banner -->
		<%@include file="../head/nav.jsp"%>
		<div id="page-wrapper">
			<div class="panel-body">
				<div class="form-group">
					<div class="col-sm-10">
						<input id="keyword" type="search" class="form-control"
							maxlength="50" placeholder="秒杀风暴" />
					</div>
					<div class="col-sm-10" id="suggestDiv"></div>
					<button id="search" type="button" class="btn btn-success">查询</button>
				</div>
			</div>
			<div class="panel-heading" id="FruitLists"></div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${ctx }/assets/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctx }/assets/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script>
var ctx = $("#ctx").val().trim();
$(document).ready(function() {
	// 分配角色的显示和隐藏
	$("#btnAllocatePermission").click(function() {
		$("#menuDiv").toggle("fast");
	});
	
	// ztree需要的
	function disabledNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), disabled = e.data.disabled, nodes = zTree
				.getSelectedNodes(), inheritParent = false, inheritChildren = false;
		if (nodes.length == 0) {
			alert("请先选择一个节点");
		}
		if (disabled) {
			inheritParent = $("#py").attr("checked");
			inheritChildren = $("#sy").attr("checked");
		} else {
			inheritParent = $("#pn").attr("checked");
			inheritChildren = $("#sn").attr("checked");
		}

		for (var i = 0, l = nodes.length; i < l; i++) {
			zTree.setChkDisabled(nodes[i], disabled,
					inheritParent, inheritChildren);
		}
	}
	// ztree需要的
	var setting = {
			check: {
				enable: true,
				chkDisabledInherit: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
	};
	
	var zNodes;
	
	function initZtree(){
		var data;
		$.ajax({
			url : ctx + '/admin/user/roleZtree',
			type : 'get',
			async : false,
			success : function(result) {
				result = $.parseJSON(result);
				zNodes =  result.data;
			}
		});
	}
	//点击查询
	$("#search").click(function() {
		search();
	})
	//文本框回车
	$("#keyword").keydown(function(e) {  
           if (e.keyCode == 13) {  
        	   search();
           }  
      });  
	
	var bol = true;
	$("#keyword").focus(function(){
		var keyword = $('#keyword').val().trim();
        if (keyword == "") {
			if(bol){
				$.ajax({
	        		url : ctx + '/admin/commodity/searchLog',
	        		type : 'post',
	        		success : function(result) {
	        			$("#suggestDiv").empty();
	        			result = $.parseJSON(result);
	        			if(result.code == "0"){
	        				if(result.data.length > 0){
	        					$("#suggestDiv").append("<ul id='suggest'></ul>");
	        					for (var i = 0; i <= result.data.length - 1; i++) {
	        						if(result.data[i] != ""){
		                                $("#suggestDiv ul").append("<li onmouseover='mouseover()'>"
		                       		  	+ result.data[i].slContent
		                       		  	+ "</li>");
	        						}
	                            }	 
	        				}
	        			}
	        		}
	        	});
				bol = false;
			}
        }
	});
	
	$("#keyword").blur(function(){
		if(!bol){
			$("#suggestDiv").empty();
			bol = true;
		}
	});
	
	//根据输入的值在solr中自动建议查询
	$('#keyword').bind('input', function () {//给文本框绑定input事件
		var keyword = $('#keyword').val().trim();
        if (keyword != "") {
        	$.ajax({
        		url : ctx + '/admin/commodity/suggestSearch',
        		type : 'post',
        		data : {
        			'keyword' : keyword
        		},
        		success : function(result) {
        			$("#suggestDiv").empty();
        			$("#FruitLists").empty();
        			result = $.parseJSON(result);
        			if(result.code == "0"){
        				if(result.data.length > 0){
        					$("#suggestDiv").append("<ul id='suggest'></ul>");
        					for (var i = 0; i <= result.data.length - 1; i++) {
        						if(result.data[i] != ""){
	                                $("#suggestDiv ul").append("<li onmouseover='mouseover()'>"
	                       		  	+ result.data[i]
	                       		  	+ "</li>");
        						}
                            }	 
        				}
        			}
        		}
        	});
        }
        else $("#suggestDiv").empty();
    });
});

var keyword;
var pageNo;
var price;
var brandId;
var statu = "com:asc";

function mouseover(){
	 $("#suggestDiv ul li").css("background", "#00FFFF");  
	// alert($("#suggestDiv ul li").index());//弹出每个li的的位置
}


//查询
function search() {
	$("#suggestDiv").empty();
	$("#FruitLists").empty();
	keyword = $("#keyword").val().trim();
	pageNo = 1;
		if (keyword.length == 0) {
			$("#keyword").parent().addClass("has-error");
			$("#keyword").focus();
			setTimeout(function() {
				$("#keyword").parent().removeClass("has-error");
			}, 1500);
			return;
		}
	$.ajax({
		url : ctx + '/admin/commodity/search',
		type : 'post',
		data : {
			'pageNo':pageNo,
			'keyword' : keyword,
			'price' : price,
			'brandId' : brandId,
			'statu' : statu
		},
		success : function(result) {
			price = null;
			result = $.parseJSON(result);
			 if(result.code=="0"){
				 if(result.data.commodities != null && result.data.commodities.length > 0){
					 //品牌
					 if(result.data.brands != null && result.data.brands.length > 0){
					  	$("#FruitLists").append("<div class='sl-brands' id='canvaslist'><strong>品牌：</strong></div>");
					  	for (var i = 0; i <= eval(result.data.brands).length - 1; i++) {
	                         $("#FruitLists .sl-brands").append("<li><a href='javascript:;'><i></i>"
                    		 	+ eval(result.data.brands)[i].brName 
                   		  		+ "</a></li>");
	                     }
					 }
					 
					 //排序
					 $("#FruitLists").append("<div class='sl-sort' id='canvaslist'><strong>排序：</strong></div>");
					 $("#FruitLists .sl-sort").append("<li><a onclick='coms()'>综合排序</a></li>");
					 $("#FruitLists .sl-sort").append("<li><a onclick='saless()'>销量</a></li>");
					 $("#FruitLists .sl-sort").append("<li><a onclick='prices()'>价格</a></li>");
					 $("#FruitLists .sl-sort").append("<input class='inp' id='from' type='text'/>");
					 $("#FruitLists .sl-sort").append("<input class='inp' id='to' type='text'/>");
					 $("#FruitLists .sl-sort").append("<button type='button' onclick='determine()' class='btn btn-success'>确定</button>");
					 
					 //内容
				     $("#FruitLists").append("<div class='sl-commodities'></div>");
                     $("#FruitLists .sl-commodities").append("<ul></ul>");
                     for (var i = 0; i <= eval(result.data.commodities).length - 1; i++) {
                         $("#FruitLists .sl-commodities ul").append("<li>"
               		  		+ "<img src="+ ctx + eval(result.data.commodities)[i].comImgurl +"/> " 
               		  		+ eval(result.data.commodities)[i].comName 
               		  		+ "<br/>"
               		  		+ eval(result.data.commodities)[i].comTitle 
               		  		+ "<br/>"
               		  		+ eval(result.data.commodities)[i].comPrice 
               		  		+ "<br/>"
               		  		+ eval(result.data.commodities)[i].comSalesvolume 
               		  		+ "</li>");
                     }
				 }else{
					 //拼写检查提醒
					 $("#FruitLists").append("<div class='sl-spellcheck'></div>");
					 $("#FruitLists .sl-spellcheck").append("<ul></ul>");
					 for (var i = 0; i <= eval(result.data.spellcheck).length - 1; i++) {
                         $("#FruitLists .sl-spellcheck ul").append("<li>您是不是要找“"
               		  		+ eval(result.data.spellcheck)[i]
               		  		+ "”？</li>");
                     }
				 }
			}else{
				ShowFailure(result.data);
			} 
		}
	});
}

//根据输入的价格查询
function determine(){
	var from = $("#from").val().trim();
	var to = $("#to").val().trim();
	if(from.length == 0)
		return;
	if(to.length == 0)
		price = from;
	else 
		price = from + "-" + to;
	search();
}

var b = true;
function comStatu(val){
	if(!b){
		statu = val + ":desc";
		b = true;
		search();
	}else{
		statu = val + ":asc";
		b = false;
		search();
	}
}
function coms(){
	comStatu("com")
}
function saless(){
	comStatu("sales")
}
function prices(){
	comStatu("price");
}
</script>

<style type="text/css">
#canvaslist li {
	line-height: 40px;
	margin: 5px 4px;
	border: 1px solid silver;
	text-align: center;
	display: inline;
}

.inp {
	width: 40px;
	margin-right: 10px;
}

#suggest li {
	list-style-type: none;
}

#suggest {
	padding-left: 0px;
	border: 1px solid #00FFFF;
}
</style>
</html>
