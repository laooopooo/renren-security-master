$(function () {
    $("#jqGrid").jqGrid({
        url: '../classtime/list',
        datatype: "json",
        colModel: [			
			{ label: 'classtimeId', name: 'classtimeId', index: 'classtime_id', width: 50, key: true ,hidden:true},
			{ label: '课序', name: 'classOrder', index: 'class_order', width: 80 }, 			
			{ label: '开始时间', name: 'startTime', index: 'start_time', width: 80 }, 			
			{ label: '结束时间', name: 'endTime', index: 'end_time', width: 80 }	
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
		classtime: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.classtime = {};
		},
		update: function (event) {
			var classtimeId = getSelectedRow();
			if(classtimeId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(classtimeId)
		},
		saveOrUpdate: function (event) {
			var url = vm.classtime.classtimeId == null ? "../classtime/save" : "../classtime/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.classtime),
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
			var classtimeIds = getSelectedRows();
			if(classtimeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../classtime/delete",
				    data: JSON.stringify(classtimeIds),
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
		getInfo: function(classtimeId){
			$.get("../classtime/info/"+classtimeId, function(r){
                vm.classtime = r.classtime;
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