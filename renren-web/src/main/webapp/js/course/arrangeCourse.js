$(function () {
    $("#jqGrid").jqGrid({
        url: '../arrclass/allweek',
        datatype: "json",
        colModel: [			
			{ label: '时间', name: 'classtimeName', width: 20 },
			{ label: '星期一', name: 'mon', width: 20, 
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期二', name: 'tues', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期三', name: 'wed', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期四', name: 'thur', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期五', name: 'fri', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期六', name: 'sat', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
				}
			}, 	
			{ label: '星期七', name: 'sun', width: 20 ,
				formatter: function(value, options, row){
					if(value<=0){
						return '剩余0间教室'
					}
					return '<button onclick=\'vm.selectClassroom("'+row.classtimeId+'","'+options.colModel.name+'")\''
					+'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
					+'<br>剩余<span style="font-size: 20px">'+value+'</span>间教室'
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
		arrClass: {},
		weekId:'',
		classtimeId:''
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		selectClassroom: function(classtimeId,weekday){
			vm.toFormdata(weekday);
			vm.classtimeId=classtimeId;
			debugger;
			layer.open({
			  type: 2,
			  title: '请选择排课教室',
			  maxmin: true,
			  shadeClose: true, //点击遮罩关闭层
			  area : ['400px' , '400px'],
			  content: '../course/arrRoom.html'
			 });

		},
		toFormdata:function(weekday){
			switch(weekday){
				case 'mon':
					vm.weekId=1
					break;
				case 'tues':
					vm.weekId=2
					break;
				case 'wed':
					vm.weekId=3
					break;
				case 'thur':
					vm.weekId=4
					break;
				case 'fri':
					vm.weekId=5
					break;
				case 'sat':
					vm.weekId=6
					break;
				case 'sun':
					vm.weekId=7
					break;
			}
		}
	}
});

