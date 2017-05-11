$(function () {
    $("#jqGrid").jqGrid({
        url: '../student/list',
        datatype: "json",
        colModel: [	
        	{ label: '操作', width: 60, align:'center',
        		formatter: function(value, options, row){
        			return '<a href="#" onclick="addCourse('+options.rowId+')">[选课] </a>'+
        			'<a href="#" onclick="addCourse('+options.rowId+')">[退课] </a>'
        		}
        	}, 
			{ label: '学生编号', name: 'studentId', index: 'student_id', width: 50, key: true ,hidden:true},
			{ label: '姓名', name: 'name', index: 'name', width: 50 ,
				formatter: function(value, options, row){
					return '<a href="#" onclick="vm.detail('+options.rowId+')">'+value+'</a>'
				}
			}, 
			{ label: '性别', name: 'sex', index: 'sex', width: 40 },			
			{ label: '学校', name: 'school', index: 'school', width: 100 },
			{ label: '生日', name: 'born', index: 'born', width: 80 }, 
			{ label: '家长姓名', name: 'parentName', index: 'parent_name', width: 60 },
			{ label: '家长电话', name: 'parentTel', index: 'parent_tel', width: 80 },
			
			{ label: '未结课班级', name: 'courseNotEndNum', index: 'courseNotEndNum', width: 60, 
				formatter: function(value, options, row){
					return '<a href="#" onclick="courseDetail('+options.rowId+',1)">'+value+'个</a>'
				}
			},
			{ label: '已结课班级', name: 'courseEndNum', index: 'courseEndNum', width: 60, 
				formatter: function(value, options, row){
					return '<a href="#" onclick="courseDetail('+options.rowId+',0)">'+value+'个</a>'
				}
			}
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
		},
		detail:function(studentId){
			vm.showList = false;
            vm.title = "详细信息";
			vm.getInfo(studentId);
			debugger;
		}
	}
});

var studentId='';
var status=0;

var courseDetail= function(rowId,courseStatus){
  studentId=rowId;
  status=courseStatus;
  layer.open({
  type: 2,
  title: '已报班级详情',
  maxmin: true,
  shadeClose: true, //点击遮罩关闭层
  area : ['1000px' , '520px'],
  content: '../course/courseDetail.html'
  });
};

var addCourse= function(rowId){
  studentId=rowId;
  layer.open({
  type: 2,
  title: '报名班级',
  maxmin: true,
  shadeClose: true, //点击遮罩关闭层
  area : ['1000px' , '520px'],
  content: '../course/addCourse.html'
  });
};
