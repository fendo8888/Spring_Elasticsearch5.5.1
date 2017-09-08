<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>首页</title>
		<%@ include file="/include/meta.jsp"%>
	</head>
	<body>

<nav class="navbar navbar-inverse" >
      <div class="container-fluid" style="margin-left: 500px;">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <a class="navbar-brand" href="#">小度</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${ctx}/search">首页</a></li>
            <li><a href="#">关于</a></li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
</nav>

  <div style="width: 20%;float: left;">
  	,
  </div>
  <div style="width: 80%;float: left;">
  	
  	
  	<div style="margin-left: 110px;width: 1000px;">
  		
  	
    <form action="${ctx}/search" method="get">
	    <div class="input-group input-group-lg" >
			  <span class="input-group-addon" id="sizing-addon1"><img style="width: 20px;height: 20px;" src="${ctx}/static/img/search.png"/></span>
			  <input type="text" class="form-control" name="param" id="param" placeholder="请输入内容" aria-describedby="sizing-addon1" style="width:800px;">
			  <select class="input-sm" id="type" name="type">
                  <option value="All">全部</option>
                  <option value="title">标题</option>
                  <option value="content">内容</option>
			  </select>
			  <button type="submit" class="btn btn-primary btn-lg">搜索</button> 
		</div>
    </form>
    
    <h3>${page.message}</h3>
    
   
    <c:forEach items="${page.simpleResult}" var="article">
      <div>
      	 <h3><a>${article.titles}</a></h3><br />
      	 <strong>
      	 	
      	 	${fn:substring(article.content, 0, 400)}..
      	 </strong><br />
      	 
      	 <div>
      	 	<img style="width: 30px;height: 30px;" src="${ctx}/static/img/view.png"/>${article.views}&nbsp;&nbsp;
      	 	<img style="width: 30px;height: 30px;" src="${ctx}/static/img/tag.png"/>${article.tags} &nbsp;&nbsp;
      	 	<a href="#"><img style="width: 30px;height: 30px;" src="${ctx}/static/img/url.png"/></a>&nbsp;&nbsp;
      	 	<img style="width: 30px;height: 30px;" src="${ctx}/static/img/category.png"/>${article.category}&nbsp;&nbsp;
      	 	<img style="width: 30px;height: 30px;" src="${ctx}/static/img/data.png"/>${article.dates}

      	 </div>
      	 
      </div>

    </c:forEach>
  
    </div>
  

    <div style="width:800px;margin:0 0 0 440px;">
	<div id="kkpager"></div>
	</div>

	<script type="text/javascript">
	
    var param = "";  
    
    $(function() {  
        var totalPage = "${page.totalPage}";  // 总页数
        var totalRecords = "${page.totalRecord}";  // 总记录数
        var pageSize = "${page.pageSize}";  // 每页显示数量 
  
        var pageNum = parseInt("${page.pageNo}");  // 当前页码 
        //初始化分页控件  
        //有些参数是可选的，比如lang，若不传有默认值  
        kkpager.init({  
            pno: pageNum,  
            //总页码  
            total: totalPage,  
            //总数据条数  
            totalRecords: totalRecords,  
            //链接前部  
            hrefFormer: '${ctx}/search',  
            //链接尾部  
            hrefLatter: '',  
            getLink: function(n) {  
                return getInitParam() + "&pageNo=" + n + "&pageSize=" + pageSize;  
            },  
            lang: {  
                prePageText: '上一页',  
                nextPageText: '下一页',  
                totalPageBeforeText: '共',  
                totalPageAfterText: '页',  
                totalRecordsAfterText: '条数据',  
                gopageBeforeText: '转到',  
                gopageButtonOkText: '确定',  
                gopageAfterText: '页',  
                buttonTipBeforeText: '第',  
                buttonTipAfterText: '页'  
            }  
        });  
        //生成  
        kkpager.generPageHtml();  
  
        $('#mykkpagerselect').val(pageSize);  
    });  
      
      
    function getInitParam() {  
        var type = $('#type').val();  
        var param = $('#param').val();  
  
        var attr = "?param=" + encodeURI(encodeURI(param))   
                + "&type=" + type;  
        return "${ctx}/search" + attr;  
    }  
  
	</script>
	</body>
</html>
