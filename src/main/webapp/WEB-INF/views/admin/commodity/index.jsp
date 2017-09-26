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
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">${title }</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- 删除提示模态框 -->
			<div id="deleteModal" class="modal fade" role="dialog"
				aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="gridSystemModalLabel">提示信息</h4>
						</div>
						<div class="modal-body">
							<div class="container-fluid">数据删除后不可回复，确定删除？</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button id="btnDelete" type="button" class="btn btn-primary">确定删除</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- 添加，修改用户的模态框 -->
			<div class="modal fade" id="addEditModal" tabindex="-1" role="dialog"
				aria-labelledby="addEditModalTitle" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="addEditModalTitle"></h4>
						</div>
						<div class="modal-body">
							<div class="form-horizontal">
								<input id="comId" type="hidden">
								<div class="form-group">
									<label class="col-sm-2 control-label">名称</label>
									<div class="col-sm-10">
										<input id="comName" type="text" class="form-control"
											placeholder="请输入名称">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">标题</label>
									<div class="col-sm-10">
										<input id="comTitle" type="text" class="form-control"
											placeholder="请输入标题">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">内容</label>
									<div class="col-sm-10">
										<input id="comContent" type="text" class="form-control"
											placeholder="请输入内容">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">价格</label>
									<div class="col-sm-10">
										<input id="comPrice" type="number" class="form-control"
											placeholder="请输入价格">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">数量</label>
									<div class="col-sm-10">
										<input id="comNumber" type="number" class="form-control"
											placeholder="请输入数量">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">类型</label>
									<div class="col-sm-10">
										<input id="comType" type="text" class="form-control"
											placeholder="请输入类型">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">品牌</label>
									<div class="col-sm-10">
										<select id="comBrand" class="form-control">
											<c:forEach items="${brandList }" var="brand"
												varStatus="status">
												<option value="${brand.brId}">${brand.brName}
											</c:forEach>
										</select>
										<!-- <input id="comBrand" type="text" class="form-control"
											placeholder="请输入品牌"> -->
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">图片</label>
									<div class="col-sm-10">
										<input id="file" name="file" type="file" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态</label>
									<div class="col-sm-10">
										<input id="comState" type="checkbox" class="form-control"
											placeholder="请选择状态">
									</div>
								</div>
								<div class="form-group" id="menuDiv" style="display: none">
									<div class="col-sm-10 col-sm-offset-2">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button id="btnSubmit" type="button" class="btn btn-primary">确定</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<button id="addUser" type="button" class="btn btn-success">添加商品</button>
							<button id="emptyGoods" type="button" class="btn btn-success">清空商品</button>
							<button id="reconstructedGoods" type="button"
								class="btn btn-success">重构商品</button>
							<button id="csdnBlog" type="button" class="btn btn-success">网络爬虫</button>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>名称</th>
											<th>标题</th>
											<th>价格</th>
											<th>数量</th>
											<th>类型</th>
											<th>销量</th>
											<th>内容</th>
											<th>品牌</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${comList }" var="com" varStatus="status">
											<tr>
												<td>${com.comName }</td>
												<td>${com.comTitle }</td>
												<td>${com.comPrice }</td>
												<td>${com.comNumber }</td>
												<td>${com.comType }</td>
												<td>${com.comSalesvolume }</td>
												<td>${com.comContent }</td>
												<td><input id="brId" type="hidden"
													value="${com.comBrand.brId }">
													${com.comBrand.brName }</td>
												<td><input type="checkbox" id="kb-ch" name="pids"
													value="${com.comState}"
													<c:if test="${com.comState == 1}">
															checked="checked"
														</c:if> />
												</td>
												<td>
													<button type="button" accesskey="${com.comId }"
														class="btn btn-outline btn-primary btn-xs">编辑</button>
													<button type="button" accesskey="${com.comId }"
														class="btn btn-outline btn-danger btn-xs">删除</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
</body>
<script type="text/javascript"
	src="${ctx }/assets/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctx }/assets/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script>
	$(document).ready(function() {
		var ctx = $("#ctx").val().trim();
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
		
		// 添加修改的模态框
		var addEditModal = $("#addEditModal");
		// 点击添加用户按钮
		var isShowPassword;
		$("#addUser").click(function() {
			isShowPassword=true;
			$("#password").parent().parent().show();
			$("#addEditModalTitle").text("添加商品");
			$("#btnSubmit").text("确定添加");
			$("#comName").val("");
			$("#comTitle").val("");
			$("#comContent").val("");
			$("#comPrice").val("");
			$("#comNumber").val("");
			$("#comType").val("");
			$("#comBrand").val("");
			$("#comImgurl").val("");
			$("#comBrand").val("1");
			// 让zNodes有值
			initZtree();
			console.info(zNodes);
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#disabledTrue").bind("click", {disabled: true}, disabledNode);
			$("#disabledFalse").bind("click", {disabled: false}, disabledNode);
			// 重新分配
			var treeObj= $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.checkAllNodes(false);
			// 显示模态框
			addEditModal.modal({
				show : true,
			});
		})
		//清空商品
		$("#emptyGoods").click(function() {
			goods("empty");
		})
		//重构商品
		$("#reconstructedGoods").click(function() {
			goods("reconstructed");
		})
		//网络爬虫
		$("#csdnBlog").click(function() {
			 $.post(ctx + '/admin/csdnblog/crawlerrun',null,function(result){
				 ShowSuccess(result);
			 });
		})
		
		function goods(type){
			$.ajax({
				url : ctx + '/admin/commodity/' + type,
				type : 'post',
				success : function(result) {
					result = $.parseJSON(result);
					ShowSuccess(result.data);
				}
			});
		}
		
		var comId;
		var comName;
		var comTitle;
		var comContent;
		var comPrice;
		var comNumber;
		var comType;
		var comBrand;	
		var comState;	
		var imgUrl;
		
		// 编辑按钮
		$("button.btn-primary.btn-xs").click(function(){
			console.info("编辑");
			isShowPassword=false;
			var comId = $(this).attr("accesskey");
			console.info("comId:"+comId);
			$("#comId").val(comId);
			var tr = $(this).parent().parent();
			$(tr).each(function() {
				var td = $(this).children();
				comName = $.trim(td.eq(0).text());
				$("#comName").val(comName);
				comTitle = $.trim(td.eq(1).text());
				$("#comTitle").val(comTitle);
				comPrice = $.trim(td.eq(2).text());
				$("#comPrice").val(comPrice);
				comNumber = $.trim(td.eq(3).text());
				$("#comNumber").val(comNumber);
				comType = $.trim(td.eq(4).text());
				$("#comType").val(comType);
				comContent = $.trim(td.eq(6).text());
				$("#comContent").val(comContent);
				comBrand = $.trim(td.eq(7).find("#brId").val());
			    $("#comBrand").val(comBrand);
				var	comState = $.trim(td.eq(8).find("input").val());
				if(comState==1) comState = true;
				else comState = false;
				$("#comState").prop("checked",comState);
			});
			$("#password").parent().parent().hide();
			$("#addEditModalTitle").text("修改商品");
			$("#btnSubmit").text("确定修改");
			// 显示模态框
			addEditModal.modal({
				show : true,
			});
		})
		
		// 点击确定添加按钮
		$("#btnSubmit").click(function() {
			comId=$("#comId").val();
			comName=$("#comName").val().trim();
			comTitle=$("#comTitle").val().trim();
			comContent=$("#comContent").val().trim();
			comPrice=$("#comPrice").val().trim();
			comNumber=$("#comNumber").val().trim();
			comType=$("#comType").val().trim();
			comBrand=$("#comBrand").val().trim();
			if($('#comState').is(':checked')) comState = 1;
			else comState = 0;
			if (comName.length == 0) {
				$("#comName").parent().addClass("has-error");
				$("#comName").focus();
				setTimeout(function() {
					$("#comName").parent().removeClass("has-error");
				}, 1500);
				return;
			}
			if (comTitle.length == 0) {
				$("#comTitle").parent().addClass("has-error");
				$("#comTitle").focus();
				setTimeout(function() {
					$("#comTitle").parent().removeClass("has-error");
				}, 1500);
				return;
			}
			if (comPrice.length == 0) {
				$("#comPrice").parent().addClass("has-error");
				$("#comPrice").focus();
				setTimeout(function() {
					$("#comPrice").parent().removeClass("has-error");
				}, 1500);
				return;
			}
			if (comNumber.length == 0) {
				$("#comNumber").parent().addClass("has-error");
				$("#comNumber").focus();
				setTimeout(function() {
					$("#comNumber").parent().removeClass("has-error");
				}, 1500);
				return;
			}
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			if(formData!=null){
				upload("文件上传");
			}else{
				addOrUpdateUser(comId,comName,comTitle,comContent,comPrice,comNumber,comType,comBrand);
			}
		});
		
		function addOrUpdateUser(comId,comName,comTitle,comContent,comPrice,comNumber,comType,comBrand) {
			$.ajax({
				url : ctx + '/admin/commodity/add',
				type : 'post',
				data : {
					'comId':comId,
					'comName' : comName,
					'comTitle' : comTitle,
					'comContent' : comContent,
					'comPrice' : comPrice,
					'comNumber' : comNumber,
					'comType' : comType,
					'comImgurl' : imgUrl,
					'comState' : comState,
					'comBrand.brId' : comBrand
				},
				success : function(result) {
					result = $.parseJSON(result);
					if(result.code=="0"){
						$("#btnSubmit").attr("disabled",true);
						if(comId!=null && comId!=""){
							$("#btnSubmit").text("修改成功");
							ShowSuccess("修改成功");
						}else{
							$("#btnSubmit").text("添加成功");
							ShowSuccess("添加成功");
						}
						setTimeout(function() {
							window.location.reload();
						}, 500);
						
					}else{
						$("#btnSubmit").text(result.data);
						ShowFailure(result.data);
					}
				}
			});
		}
		function upload(name) {
			var formData = new FormData();
			formData.append('file', $('#file')[0].files[0]);
			formData.append('name', name);
			$.ajax({
				url : ctx + '/admin/test/upload',
				type : 'POST',
				cache : false,
				data : formData,
				processData : false,
				contentType : false,
				success : function(data) {
					console.info(data);
					result = $.parseJSON(data);
					if (result.code == "0") {
						//ShowFailure("上传成功");
						imgUrl = result.data;
						addOrUpdateUser(comId,comName,comTitle,comContent,comPrice,comNumber,comType,comBrand);
					} else if(result.code=="-4"){
						console.info("不支持的文件类型");
						ShowFailure("操作失败：" + result.data);
					} else {
						console.info(result.data);
						ShowFailure("操作失败：" + result.data);
					}
				},
				error : function(data) {
					console.info(data);
					ShowFailure("操作失败：" + data);
				}
			})
		}
		
		// 删除的提示模态框
		var deleteModal = $("#deleteModal");
		var deleteId;
		
		// 删除按钮
		$("button.btn-danger").click(function(){
			deleteModal.modal({
				show : true,
			});
			deleteId = $(this).attr("accesskey");
			
		})
		// 点击确定删除按钮
		$("#btnDelete").click(function() {
			deleteCom(deleteId);
			deleteModal.modal('hide');
		});
		
		function deleteCom(id){
			$.ajax({
				url : ctx + '/admin/commodity/delete/'+id,
				type : 'POST',
				success: function(data) {
					result = $.parseJSON(data);
					ShowSuccess("成功删除"+result.data+"条数据");
					setTimeout(function() {
						window.location.reload();
					}, 1000);
				},
				error: function(data) {
					ShowFailure("操作失败："+data);
				}
			})
		}
		
		// 表格居中
		$('td').attr("class", "text-center");
		$('th').attr("class", "text-center");
		// 分页插件
		$('#dataTables-example').DataTable({
			bSort : false,
			"sPaginationType" : "full_numbers",
			"oLanguage" : {
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "抱歉， 没有找到",
				"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty" : "没有数据",
				"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
				"sZeroRecords" : "没有检索到数据",
				"sSearch" : "搜索:",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "尾页"
				}
			}
		});
	});
</script>

</html>
