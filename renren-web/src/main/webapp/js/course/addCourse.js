$(function () {
    $("#jqGrid").jqGrid({
        url: '../course/list',
        datatype: "json",
        colModel: [			
			{ label: '课程编号', name: 'courseId', index: 'course_id', width: 50, key: true ,hidden:true},
			{ label: '班级名称', name: 'courseName', index: 'course_name', width: 80 }, 
			{ label: '老师', name: 'teacherName', index: 'teacher_id', width: 80 }, 			
			{ label: '课次', name: 'courseTime', index: 'course_time', width: 60 },
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
				postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		},
		getTeachers: function(){
			$.get("../teacher/list?limit=10000&page=1&sidx=&order=asc",function(r){
				vm.teachers = r.page.list;
			});
		},
		signUp: function (event) {
			var courseId = getSelectedRow();
			if(courseId == null){
				return ;
			}
			vm.courseRecord.courseId=courseId;
			vm.courseRecord.studentId=vm.student.studentId;
			$.ajax({
			    type : "GET",
			    url : "../course/info/"+courseId,
			    dataType : "json",
			    success : function (data) {
			    	vm.course = data.course;
			    	if (vm.course.status==2) {
			    		layer.msg('该课程已结课，不能报名'); 
			    		return ;
			    	}
			    	var iframeWin=null;
					layer.open({
					  type: 2,
					  title: '确认报名',
					  btn: ['确认', '取消'],
					  maxmin: true,
					  shadeClose: true, //点击遮罩关闭层
					  area : ['320px' , '220px'],
					  content: '../course/confirmCourse.html',
					  success: function(layero, index){
					  	iframeWin = window[layero.find('iframe')[0]['name']];
					  },
					  yes: function(){
					  	if (iframeWin.confirm()=='0') {
					  		layer.msg('请输入实收费用'); 
					  		return ;
					  	}
					  	//添加课程到数据库
					  	$.ajax({
							type: "POST",
						    url: '../courserecord/save',
						    data: JSON.stringify(vm.courseRecord),
						    success: function(r){
						    	if(r.code === 0){
									alert('操作成功', function(index){
										 layer.close(layer.index); //如果设定了yes回调，需进行手工关闭
									});
								}else{
									alert(r.msg);
								}
							}
						});
					  }
					});
			    }
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
			debugger;
		}
	});
} 
