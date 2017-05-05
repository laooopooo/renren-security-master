$(function () {
    $("#jqGrid").jqGrid({
        url: '../analyze/list',
        datatype: "json",
        colModel: [			
			{ label: '财务类型', name: 'typeName', index: 'typeName', width: 20 },
			{ label: '收支金额', name: 'typeAmount', index: 'typeAmount', width: 20 }, 		
			{ label: '百分比', name: 'percent', index: 'percent', width: 20 ,
				formatter: function(value, options, row){
					return value+'%'
				}
			}
		], 
		viewrecords: true,
        height: 'auto',
       // rowNum: 30,
        autowidth:false,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
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
		analyze: {},
		payInDatas:[
			{text:'支出',value:'0'},
			{text:'收入',value:'1'}
		],
		finQuarterDatas:[
			{text:'春季',value:'1'},
			{text:'暑假',value:'2'},
			{text:'秋季',value:'3'},
			{text:'寒假',value:'4'}
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
		}
	}
});

//因为gqgrid的数据无法取出来，所有重新用ajax再次去后台取一次
var finData=[];
var typeName=[];
var typeAmount=[];
var pieData=new Array();

$.ajax({
    type : "GET",
    url : "../analyze/list?limit=10000&page=1&sidx=&order=asc",
    dataType : "json",
    success : function (data) {
    	finData=data.page.list;
    	//数据格式化，为下面的画图做准备
    	for (var i = 0; i< finData.length; i++) {
    		var tempobj=new Object();
    		typeName[i]=finData[i].typeName;
    		typeAmount[i]=finData[i].typeAmount;
    		tempobj.name=finData[i].typeName;
    		tempobj.value=finData[i].typeAmount;
    		pieData.push(tempobj);
    	}
    	
    }
});

 // 路径配置
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

// 使用
require(
    [
        'echarts',
        'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('main')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['金额']
            },
            xAxis : [
                {
                    type : 'category',
                    data : typeName,
                    axisLabel:{
                    	formatter:function(val){
						    return val.split("、").join("\n");
						},
						interval:0
                    }
                }
                
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"金额",
                    "type":"bar",
                    "data":typeAmount
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);

// 使用
require(
    [
        'echarts',
        'echarts/chart/pie' // 使用柱状图就加载pie模块，按需加载
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart2 = ec.init(document.getElementById('main2')); 
        
        option2 = {
            title : {
                text: '饼状图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:typeName
            },
            
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:pieData,
                    itemStyle:{ 
			            normal:{ 
			                label:{ 
			                    show: true, 
			                    formatter: '{b} : {c} ({d}%)' 
			                }, 
			                labelLine :{show:true} 
			            } 
			        } 

                }
            ]
        };
            

        // 为echarts对象加载数据 
        myChart2.setOption(option2); 
    }
);