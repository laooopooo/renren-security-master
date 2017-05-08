$(function () {
    $("#jqGrid").jqGrid({
        url: '../finance/list',
        datatype: "json",
        colModel: [			
			{ label: '收支编号', name: 'financeId', index: 'finance_id', width: 50, key: true ,hidden:true},
			{ label: '财务类型', name: 'finType', index: 'fin_type', width: 80 },
			{ label: '收支金额', name: 'finAmount', index: 'fin_amount', width: 80 },
			{ label: '年份', name: 'finYear', index: 'fin_year', width: 80 },		
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
		payInDatas:[{
			name:'收入',
			value:'1',
			finTypes:[
				{ name: '晚辅学费' },
				{ name: '一对一学费' },
				{ name: '小班学费' },
				{ name: '其他' },
			]
		},
		{
			name:'支出',
			value:'0',
			finTypes:[
				{ name: '工资' },
				{ name: '书籍、办公用品' },
				{ name: '装修费、修理' },
				{ name: '业务招待' },
				{ name: '出差、餐费' },
				{ name: '广告宣传' },
				{ name: '福利奖金' },
				{ name: '保险' },
				{ name: '水电物管' },
				{ name: '租赁' },
				{ name: '交通运输' },
				{ name: '其他' },
			]
		}
		],
		finQuarterDatas:[
			{name:'春季',value:'1'},
			{name:'暑假',value:'2'},
			{name:'秋季',value:'3'},
			{name:'寒假',value:'4'}
		],
		years:[],
		q:{
			year:null,
			quarter:null,
			payOrIncome:null,
			typeOrRemarks:null
		}
	},
	computed: {
	    finTypes: function() {
	    	var finTypes = [], payOrIncome = this.$data.finance.payOrIncome;
	    	debugger;
	    	 this.$data.payInDatas.forEach(function(d) {
		        if(d.value == payOrIncome)
		          finTypes = d.finTypes;
		      });
	    	 return finTypes;
	    }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			//初始化参数，为添加新的数据提供默认选项
			vm.finance = {
				payOrIncome:0,
				finType:'',
				finYear:'2017',
				finQuarter:'1',
				finAmount:null,
				finDate:'',
				remarks:''
			};
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
				postData:{
					'payOrIncome': vm.q.payOrIncome,
					'year':vm.q.year,
					'quarter':vm.q.quarter,
					'typeOrRemarks':vm.q.typeOrRemarks
				}, 
                page:page
            }).trigger("reloadGrid");
		}

	}
});

window.onload=function(){ 
//设置年份的选择 
	var myDate= new Date(); 
	var startYear=myDate.getFullYear()-5;//起始年份 
	var endYear=myDate.getFullYear()+3;//结束年份 
	for(var i=0;i<9;i++){
		//debugger;
		var tempobj=new Object();
		tempobj.name=startYear+i;
		tempobj.value=startYear+i
		vm._data.years.push(tempobj);
	}
} 
