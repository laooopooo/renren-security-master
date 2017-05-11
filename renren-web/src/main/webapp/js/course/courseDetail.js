$(function () {
    $("#jqGrid").jqGrid({
        url: '../student/courseList?studentId='+parent.studentId+"&status="+parent.status,
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
		student:{},
		courseRecord:{//提交添加课程到课程记录中使用的对象
			actualPrice:'',
			courseId:'',
			studentId:''
		},
		q:{
			key : null
		}
	},
	methods: {
		query: function () {
			vm.reload();
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
				//postData:{'studentId': vm.student.studentId},
                page:page
            }).trigger("reloadGrid");
		},
		getTeachers: function(){
			$.get("../teacher/list?limit=10000&page=1&sidx=&order=asc",function(r){
				vm.teachers = r.page.list;
			});
		}

	}
});


//报名
window.onload=function(){ 
	$.ajax({
		type: "GET",
	    url: "../student/info/"+parent.studentId,
	    dataType : "json",
	    success: function(r){
			vm.student = r.student;
			//vm.reload();
			debugger;
		}
	});

} 
