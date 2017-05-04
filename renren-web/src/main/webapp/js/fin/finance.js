$(function () {
    $("#jqGrid").jqGrid({
        url: '../finance/list',
        datatype: "json",
        colModel: [			
			{ label: 'financeId', name: 'financeId', index: 'finance_id', width: 50, key: true },
			{ label: '收支', name: 'payOrIncome', index: 'pay_or_income', width: 80 }, 			
			{ label: '财务类型', name: 'finType', index: 'fin_type', width: 80 }, 			
			{ label: '日期', name: 'finDate', index: 'fin_date', width: 80 }, 			
			{ label: '收支金额', name: 'finAmount', index: 'fin_amount', width: 80 }, 			
			{ label: '备注', name: 'remarks', index: 'remarks', width: 80 }, 			
			{ label: '季度', name: 'finQuarter', index: 'fin_quarter', width: 80 },
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
		finance: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.finance = {};
		},
		update: function (event) {
			var financeId = getSelectedRow();
			if(financeId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(financeId)
		},
		saveOrUpdate: function (event) {
			var url = vm.finance.financeId == null ? "../finance/save" : "../finance/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.finance),
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
			var financeIds = getSelectedRows();
			if(financeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../finance/delete",
				    data: JSON.stringify(financeIds),
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
		getInfo: function(financeId){
			$.get("../finance/info/"+financeId, function(r){
                vm.finance = r.finance;
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