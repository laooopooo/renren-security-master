$(function () {
    $("#jqGrid").jqGrid({
        url: '../arrclass/teachercourse?teacherId='+parent.vm.teacherId,
        datatype: "json",
        colModel: [			
			{ label: '时间', name: 'classtimeName', width: 20 ,
				formatter: function(value, options, row){
					return row.classtimeName+'<br>('+row.startTime+'~'+row.endTime+')'
				}	
			},
			{ label: '星期一', name: 'mon', width: 20, 
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
				}
			}, 	
			{ label: '星期二', name: 'tues', width: 20 ,
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
				}
			}, 	
			{ label: '星期三', name: 'wed', width: 20 ,
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
				}
			}, 	
			{ label: '星期四', name: 'thur', width: 20 ,
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
				}
			}, 	
			{ label: '星期五', name: 'fri', width: 20 ,
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
				}
			}, 	
			{ label: '星期六', name: 'sat', width: 20 ,
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
				}
			}, 	
			{ label: '星期日', name: 'sun', width: 20 ,
				formatter: function(value, options, row){
					if(value.courseName==null){
						return "";
					}
					return value.courseName;
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
        	root: "page"
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
		classtimeId:'',
		years:[],
		q:{
            year:'',
            quarter:'',
        },
        finQuarterDatas:[
 			{name:'春季',value:'1'},
 			{name:'暑假',value:'2'},
 			{name:'秋季',value:'3'},
 			{name:'寒假',value:'4'}
 		]
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