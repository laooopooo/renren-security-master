$(function () {
    $("#jqGrid").jqGrid({
        url: '../class/list',
        datatype: "json",
        colModel: [			
			{ label: 'classId', name: 'classId', index: 'class_id', width: 50, key: true },
			{ label: '班级别名', name: 'className', index: 'class_name', width: 80 }, 			
			{ label: '备注', name: 'remarks', index: 'remarks', width: 80 }, 			
			{ label: '班级-类别，小班，晚辅,一对一', name: 'classType', index: 'class_type', width: 80 }, 			
			{ label: '', name: 'lastUpdate', index: 'last_update', width: 80 }, 			
			{ label: '', name: 'teacherId', index: 'teacher_id', width: 80 }			
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
		class: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.class = {};
		},
		update: function (event) {
			var classId = getSelectedRow();
			if(classId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(classId)
		},
		saveOrUpdate: function (event) {
			var url = vm.class.classId == null ? "../class/save" : "../class/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.class),
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
			var classIds = getSelectedRows();
			if(classIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../class/delete",
				    data: JSON.stringify(classIds),
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
		getInfo: function(classId){
			$.get("../class/info/"+classId, function(r){
                vm.class = r.class;
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