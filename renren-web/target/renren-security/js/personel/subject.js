$(function () {
    $("#jqGrid").jqGrid({
        url: '../subject/list',
        datatype: "json",
        colModel: [			
			{ label: '科目编号', name: 'subjectId', index: 'subject_id', width: 50, key: true ,hidden:true},
			{ label: '科目名称', name: 'name', index: 'name', width: 80 }		
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:false,
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
		subject: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.subject = {};
		},
		update: function (event) {
			var subjectId = getSelectedRow();
			if(subjectId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(subjectId)
		},
		saveOrUpdate: function (event) {
			var url = vm.subject.subjectId == null ? "../subject/save" : "../subject/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.subject),
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
			var subjectIds = getSelectedRows();
			if(subjectIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../subject/delete",
				    data: JSON.stringify(subjectIds),
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
		getInfo: function(subjectId){
			$.get("../subject/info/"+subjectId, function(r){
                vm.subject = r.subject;
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