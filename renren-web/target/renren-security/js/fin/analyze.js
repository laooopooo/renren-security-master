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
		totalIn:null,
		totalOut:null,
		profit:null,
		payInDatas:[
			{name:'支出',value:'0'},
			{name:'收入',value:'1'}
		],
		finQuarterDatas:[
			{name:'春季',value:'1'},
			{name:'暑假',value:'2'},
			{name:'秋季',value:'3'},
			{name:'寒假',value:'4'}
		],
        years:[],
        q:{
            year:'',
            quarter:'',
            payOrIncome:'0',
            typeOrRemarks:null
        }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		
		reload: function (event) {
            startChart(vm.q.payOrIncome,vm.q.year,vm.q.quarter);
            getProfit(vm.q.year,vm.q.quarter);
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{
                    'payOrIncome': vm.q.payOrIncome,
                    'year':vm.q.year,
                    'quarter':vm.q.quarter
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});



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
            data : [''],
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
            "data":['']
        }
    ]
};

var option2 = {
    title : {
        text: '财务类型',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['']
    },
    
    calculable : true,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[''],
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



 // 路径配置
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

var startChart=function(payOrIncome,year,quarter){
    if (payOrIncome==null) {payOrIncome=""}
    if (year==null) {year=""}
    if (quarter==null) {quarter=""}
// 使用
require(
    [
        'echarts',
        'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('main')); 
		var myChart2 = ec.init(document.getElementById('main2'));

        //因为gqgrid的数据无法取出来，所有重新用ajax再次去后台取一次
        var finData=[];
        var typeName=[];
        var typeAmount=[];
        var pieData=new Array();
        
        $.ajax({
		    type : "GET",
		    url : "../analyze/list?limit=10000&page=1&sidx=&order=asc&payOrIncome="+payOrIncome+"&year="+year+"&quarter="+quarter,
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
		    	option.xAxis[0].data=typeName;
    			option.series[0].data=typeAmount;
                option2.series[0].data=pieData;
		    	myChart.setOption(option); 
		    	myChart2.setOption(option2); 
		    }
		});

    }
);
}

var getProfit = function(year,quarter){
	$.ajax({
	    type : "GET",
	    url : "../analyze/profit?limit=1&page=1&sidx=&order=asc"+"&year="+year+"&quarter="+quarter,
	    dataType : "json",
	    success : function (data) {
	    	vm.totalIn = data.totalIn;
	    	vm.totalOut = data.totalOut;
	    	vm.profit = data.profit;
	    	debugger;
	    }
	});
}


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
    startChart('','','');
    getProfit('','');
} 