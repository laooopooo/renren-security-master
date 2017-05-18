$(function () {
    $("#jqGrid").jqGrid({
        url: '../course/list',
        datatype: "json",
        colModel: [	
			{ label: '课程编号', name: 'courseId', index: 'course_id', width: 50, key: true ,hidden:true},
			{ label: '班级名称', name: 'courseName', index: 'course_name', width: 80 },
			{ label: '老师', name: 'teacherName', index: 'teacher_id', width: 80 }, 			
			{ label: '总课次', name: 'courseTime', index: 'course_time', width: 60 },
			{ label: '已上课次', name: 'expendTime', index: 'expend_time', width: 60 },
			{ label: '开课日期', name: 'startDate', index: 'start_date', width: 80 }, 			
			{ label: '结课日期', name: 'endDate', index: 'end_date', width: 80 },
			{ label: '价格', name: 'originalPrice', index: 'original_price', width: 60 ,
				formatter: function(value, options, row){
					return '¥'+value}
			},
			{ label: '课程状态', name: 'status', index: 'status', width: 80, formatter: function(value, options, row){
				if(value===1){
					return '<span class="label label-success">进行中</span>';
				}else if(value===2){
					return '<span class="label label-danger">已结束</span>';
				}else{
					return '<span class="label label-warning">未开始</span>';
				}
			}}, 
			{ label: '报名人数',name:'studentNumber', width: 80, 
        		formatter: function(value, options, row){
        			return '<a href="#" onclick="studentList('+options.rowId+')">'+value+'人</a>'
        		}
        	},
        	{ label: '操作', width: 80, 
        		formatter: function(value, options, row){
        			return '<a href="#" onclick="studentList('+options.rowId+')">排课</a>'
        		}
        	},
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
		course: {},
		teachers: {},
		quarterDatas:[
			{name:'春季',value:'1'},
			{name:'暑假',value:'2'},
			{name:'秋季',value:'3'},
			{name:'寒假',value:'4'}
		],
		years:[],
		q:{
			key : null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.getTeachers();
			vm.showList = false;
			vm.title = "新增";
			vm.course = {};
		},
		update: function (event) {
			var courseId = getSelectedRow();
			if(courseId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(courseId)
		},
		saveOrUpdate: function (event) {
			var url = vm.course.courseId == null ? "../course/save" : "../course/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.course),
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
			var courseIds = getSelectedRows();
			if(courseIds == null){
				return ;
			}
			
			confirm('删除时将删除该课程的所有选课记录'+'<br>'+'确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../course/delete",
				    data: JSON.stringify(courseIds),
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
		getInfo: function(courseId){
			vm.getTeachers();
			$.ajax({
			    type : "GET",
			    url : "../course/info/"+courseId,
			    dataType : "json",
			    success : function (data) {
			    	vm.course = data.course;
			    }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		},
		getTeachers: function(){
			$.get("../teacher/list?limit=10000&page=1&sidx=&order=asc",function(r){
				vm.teachers = r.page.list;
			});
		},

	}
});


var studentList= function(rowId){
  vm.getInfo(rowId);
  layer.open({
  type: 2,
  title: '学生名单',
  maxmin: true,
  shadeClose: true, //点击遮罩关闭层
  area : ['600px' , '320px'],
  content: '../course/studentList.html'
  });
};

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