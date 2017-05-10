$(function () {
    $("#jqGrid").jqGrid({
        url: '../student/list',
        datatype: "json",
        colModel: [	
        	{ label: '操作', width: 50,
        		formatter: function(value, options, row){
        			return '<button class="layui-btn layui-btn-mini layui-btn-normal" onclick="courseDetail('
        			+options.rowId+')">班课详情</button>'
        		}
        	}, 
			{ label: '学生编号', name: 'studentId', index: 'student_id', width: 50, key: true ,hidden:true},
			{ label: '姓名', name: 'name', index: 'name', width: 50 }, 
			{ label: '性别', name: 'sex', index: 'sex', width: 40 },			
			{ label: '学校', name: 'school', index: 'school', width: 100 },
			{ label: '生日', name: 'born', index: 'born', width: 80 }, 
			{ label: '家长姓名', name: 'parentName', index: 'parent_name', width: 60 },
			{ label: '家长电话', name: 'parentTel', index: 'parent_tel', width: 80 },
			{ label: '家庭住址', name: 'address', index: 'address', width: 60 },
			{ label: '补习方式', name: 'studyWay', index: 'studyWay', width: 60 },
			{ label: '备注', name: 'remarks', index: 'remarks', width: 60 },
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
		student: {},
		q:{
			name: null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.student = {};
		},
		update: function (event) {
			var studentId = getSelectedRow();
			if(studentId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(studentId)
		},
		saveOrUpdate: function (event) {
			var url = vm.student.studentId == null ? "../student/save" : "../student/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.student),
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
			var studentIds = getSelectedRows();
			if(studentIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../student/delete",
				    data: JSON.stringify(studentIds),
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
		getInfo: function(studentId){
			$.get("../student/info/"+studentId, function(r){
                vm.student = r.student;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

var studentId='';

var courseDetail= function(rowId){
  studentId=rowId;
  layer.open({
  type: 2,
  title: '已报班级详情',
  maxmin: true,
  shadeClose: true, //点击遮罩关闭层
  area : ['1000px' , '520px'],
  content: '../course/courseDetail.html'
  });
};
