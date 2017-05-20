$(function () {
    $("#jqGrid").jqGrid({
        url: '../arrclass/allweek',
        datatype: "json",
        colModel: [			
			{ label: '时间', name: 'classtimeName', width: 20 },
			{ label: '星期一', name: 'mon', width: 20, 
				formatter: function(value, options, row){
					debugger;
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick="selectClassroom('+')" class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期二', name: 'tues', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期三', name: 'wed', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期四', name: 'thur', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期五', name: 'fri', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期六', name: 'sat', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期七', name: 'sun', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<a href="#" onclick="quitCourse('+options.rowId+')">剩余'+value+'间教室</a>'
				}
			}	
		], 
		viewrecords: true,
        height: 'auto',
        autowidth:false,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        jsonReader : {
        	root: "arrClass"
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
		arrClass: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.arrClass = {};
		},
		update: function (event) {
			var arrClassId = getSelectedRow();
			if(arrClassId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(arrClassId)
		},
		saveOrUpdate: function (event) {
			var url = vm.arrClass.arrClassId == null ? "../arrclass/save" : "../arrclass/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.arrClass),
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
			var arrClassIds = getSelectedRows();
			if(arrClassIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../arrclass/delete",
				    data: JSON.stringify(arrClassIds),
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
		getInfo: function(arrClassId){
			$.get("../arrclass/info/"+arrClassId, function(r){
                vm.arrClass = r.arrClass;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},

	}
});

var selectClassroom = function(classtimeName,weekday){
	debugger;

}