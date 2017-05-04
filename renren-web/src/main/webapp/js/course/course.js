$(function () {
    $("#jqGrid").jqGrid({
        url: '../course/list',
        datatype: "json",
        colModel: [			
			{ label: '课程ID', name: 'courseId', index: 'course_id', width: 50, key: true },
			{ label: '班级名称', name: 'courseName', index: 'course_name', width: 80 }, 
			{ label: '班级类别', name: 'numberType', index: 'number_type', width: 80 },
			{ label: '老师', name: 'teacherName', index: 'teacher_id', width: 80 }, 			
			{ label: '课次', name: 'courseTime', index: 'course_time', width: 60 },
			{ label: '开课日期', name: 'startDate', index: 'start_date', width: 80 }, 			
			{ label: '结课日期', name: 'endDate', index: 'end_date', width: 80 },
			{ label: '原价', name: 'originalPrice', index: 'original_price', width: 60 ,
				formatter: function(value, options, row){
					return '¥'+value}
			},
			{ label: '优惠价', name: 'actualPrice', index: 'actual_price', width: 60, 
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
			
			confirm('确定要删除选中的记录？', function(){
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
			setTimeout(function(){$.get("../course/info/"+courseId, function(r){
                vm.course = r.course;
            });} ,200 );
			
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