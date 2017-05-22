$(function () {
    $("#jqGrid").jqGrid({
        url: '../teacher/list',
        datatype: "json",
        colModel: [			
			{ label: '教师编号', name: 'teacherId', index: 'teacher_id', width: 50, key: true ,hidden:true},
			{ label: '姓名', name: 'name', index: 'name', width: 80 }, 	
			{ label: '性别', name: 'sex', index: 'sex', width: 40 }, 	
			{ label: '职位', name: 'positionName', index: 'position_id', width: 80 },
			{ label: '主教科目', name: 'subjectName', index: 'subject_id', width: 80 }, 
			{ label: '电话号码', name: 'phone', index: 'phone', width: 80 }, 
			{ label: '教龄', name: 'teachAge', index: 'teach_age', width: 40 },	
			{ label: '全职', name: 'isFulltime', index: 'is_fulltime', width: 40 },
			{ label: '出生日期', name: 'born', index: 'born', width: 100 }, 
			{ label: '毕业学校', name: 'graduateSchool', index: 'graduate_school', width: 100 }, 
			{ label: '操作', width: 80 ,
				formatter: function(value, options, row){
					return '<a href="#" onclick="vm.showCourseTable('+options.rowId+')">[查看课表] </a>'
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
		teacher: {},
		subjects: {},
		positions:{},
		teacherId:'',
		teacherName:'',
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
			vm.teacher = {};
			vm.getSubjects();
			vm.getPositions();
		},
		update: function (event) {
			var teacherId = getSelectedRow();
			if(teacherId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(teacherId)

		},
		saveOrUpdate: function (event) {
			var url = vm.teacher.teacherId == null ? "../teacher/save" : "../teacher/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.teacher),
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
			var teacherIds = getSelectedRows();
			if(teacherIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../teacher/delete",
				    data: JSON.stringify(teacherIds),
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
		getInfo: function(teacherId){
			vm.getSubjects();
            vm.getPositions();
            $.ajax({
			    type : "GET",
			    url : "../teacher/info/"+teacherId,
			    dataType : "json",
			    success : function (data) {
			    	vm.teacher = data.teacher;
			    }
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
		getSubjects:function(){
			$.get("../subject/list?limit=100&page=1&sidx=&order=asc",function(r){
				vm.subjects = r.page.list;
			});
		},
		getPositions:function(){
			$.get("../position/list?limit=100&page=1&sidx=&order=asc",function(r){
				vm.positions = r.page.list;
			});
		},
		showCourseTable:function(teacherId){
			vm.teacherId=teacherId;
			$.ajax({
			    type : "GET",
			    url : "../teacher/info/"+teacherId,
			    dataType : "json",
			    success : function (data) {
			    	vm.teacher = data.teacher;
			    }
			});
			layer.open({
				  type: 2,
				  title: '教师课表',
				  maxmin: true,
				  shadeClose: true, //点击遮罩关闭层
				  area : ['100%' , '100%'],
				  content: '../personel/teacherCourse.html',
				  end:function(){
				  	vm.reload();
				  }
			});
		}
	}
});