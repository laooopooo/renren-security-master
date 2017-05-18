$(function () {
    $("#jqGrid").jqGrid({
        url: '../classroom/list',
        datatype: "json",
        colModel: [			
			{ label: 'classroomId', name: 'classroomId', index: 'classroom_id', width: 50, key: true, hidden:true },
			{ label: '教室名称', name: 'roomName', index: 'room_name', width: 80 }, 			
			{ label: '教室容量', name: 'roomCapacity', index: 'room_capacity', width: 80 }	
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
		classroom: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.classroom = {};
		},
		update: function (event) {
			var classroomId = getSelectedRow();
			if(classroomId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(classroomId)
		},
		saveOrUpdate: function (event) {
			var url = vm.classroom.classroomId == null ? "../classroom/save" : "../classroom/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.classroom),
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
			var classroomIds = getSelectedRows();
			if(classroomIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../classroom/delete",
				    data: JSON.stringify(classroomIds),
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
		getInfo: function(classroomId){
			$.get("../classroom/info/"+classroomId, function(r){
                vm.classroom = r.classroom;
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