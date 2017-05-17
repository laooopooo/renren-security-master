$(function () {
    $("#jqGrid").jqGrid({
        url: '../tenant/list',
        datatype: "json",
        colModel: [			
			{ label: 'tenantId', name: 'tenantId', index: 'tenant_id', width: 50, key: true,hidden:true },
			{ label: '机构名字', name: 'tenantName', index: 'tenant_name', width: 60 }, 	
			{ label: '租户电话', name: 'tenantPhone', index: 'tenant_phone', width: 60 }, 
			{ label: '省份证', name: 'tenantIdcard', index: 'tenant_idcard', width: 90 }, 		
			{ label: '租户姓名', name: 'trueName', index: 'true_name', width: 50 }, 			
			{ label: '机构地址', name: 'tenantAddress', index: 'tenant_address', width: 80 }, 
			{ label: '缴费金额', name: 'payMoney', index: 'pay_money', width: 50 },			
			{ label: '租用日期', name: 'hireDate', index: 'hire_date', width: 60 }, 
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 60 }, 			
			{ label: '最近更新', name: 'lastUpdate', index: 'last_update', width: 60 }, 		
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tenant: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tenant = {};
		},
		update: function (event) {
			var tenantId = getSelectedRow();
			if(tenantId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(tenantId)
		},
		saveOrUpdate: function (event) {
			debugger;
			
			var url = vm.tenant.tenantId == null ? "../tenant/save" : "../tenant/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tenant),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var tenantIds = getSelectedRows();
			if(tenantIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tenant/delete",
				    data: JSON.stringify(tenantIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(tenantId){
			$.get("../tenant/info/"+tenantId, function(r){
                vm.tenant = r.tenant;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});

$(function(){
    //jquery.validate
	$("#jsForm").validate({
		submitHandler: function() {
			//验证通过后 的js代码写在这里
			debugger;
			vm.saveOrUpdate();
		}
	});
	$().ready(function() {
	    $("#jsForm").validate();
	});
})