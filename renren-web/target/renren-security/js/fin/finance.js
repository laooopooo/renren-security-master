$(function () {
    $("#jqGrid").jqGrid({
        url: '../finance/list',
        datatype: "json",
        colModel: [			
			{ label: '收支编号', name: 'financeId', index: 'finance_id', width: 50, key: true },
			{ label: '财务类型', name: 'finType', index: 'fin_type', width: 80 },
			{ label: '收支金额', name: 'finAmount', index: 'fin_amount', width: 80 }, 		
			{ label: '季度', name: 'finQuarterName', index: 'fin_quarter', width: 80 },
			{ label: '日期', name: 'finDate', index: 'fin_date', width: 80 }, 		
			{ label: '收支', name: 'payOrIncome', index: 'pay_or_income', width: 80 ,
				formatter: function(value, options, row){
					if(value===1){
						return '<span class="label label-success">收入</span>';
					}else if(value===0){
						return '<span class="label label-danger">支出</span>';
					}else{
						return '<span class="label label-warning">数据错误</span>';
					}
				}}, 
			{ label: '备注', name: 'remarks', index: 'remarks', width: 80 } 			
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
		finance: {},
		payInDatas:[
			{text:'支出',value:'0'},
			{text:'收入',value:'1'}
		],
		finQuarterDatas:[
			{text:'春季',value:'1'},
			{text:'暑假',value:'2'},
			{text:'秋季',value:'3'},
			{text:'寒假',value:'4'}
		]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.finance = {};
			//初始化参数，为添加新的数据提供默认选项
			vm.finance.payOrIncome=0;
			vm.finance.finType='工资';
			vm.finance.finQuarter=1;
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