<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String username="";
String password="";
String checked="";
Cookie[] c=request.getCookies();
if(c!=null)
{
	for(int i=0;i<c.length;i++)
	{
		if("username".equals(c[i].getName()))
		{
			username=c[i].getValue();
		}else if("password".equals(c[i].getName()))
		{
			password=c[i].getValue();
		}else if("checked".equals(c[i].getName()))
		{
			checked=c[i].getValue();
			System.out.println(checked);
		}
	}
}
%>
<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>IT在线学习平台</title>
		
		<link href="res/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="res/css/reset.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="res/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="res/js/bootstrap.min.js"></script>
		
		<!--[if lt IE 9]>
		  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<link rel="icon" type="image/png" href="res/i/ico.png" sizes="16x16">
		<script type="text/javascript">
		CONETXT_PATH = '';
		</script>
		
		<script type="text/javascript">
		function validate(){
    			var name=document.getElementById("name").value;
    			var mima1=document.getElementById("mima1").value;
    			var mima2=document.getElementById("mima2").value;
    			if(mima1==""||name==""){
    				alert("用户名或密码不能为空");
    				document.getElementById("mima1").focus();
    				return false;
    			}else if(/.*[\u4e00-\u9fa5]+.*$/.test(mima1)){
    				alert("密码不能含有中文");
    				return false;
    			}else if(mima1!=mima2){
    				alert("确认密码失败，请重新输入");
    				document.getElementById("mima2").focus();
    				return false;
    			}else if(mima1.length>16||mima1.length<8){
    				alert("密码长度为8-16位，请重新输入");
    				return false; 
    			}else{
    				return true;
    			}
    		}
   	 	</script>
    <script type="text/javascript" src="res/js/change.js"></script>
	</head>

	<body>
		<!-- 头部-start -->
		<div class="f-header">
			<div class="f-header-box clearfix">
				<a   class="logo" title="IT在线学习平台"></a>
				<nav class="header-nav">
					<a href="index.jsp" class="header-nav-item">首 页</a>

				</nav>
				
				<nav class="header-nav" style="float:right">
					<a href="#myModal" class="header-nav-item"  data-toggle="modal" onclick="login();"  style="margin-right:0px;font-size:14px;">登录/注册</a>
				</nav>
			</div>
		</div>
		<script type="text/javascript">
			function login(){
				$('#loginTitle').css('color','#337Ab7');
				$('#loginForm').show();
				$('#registeTitle').css('color','#000');
				$('#registeForm').hide();
				if(checked=="checked")
					{
					
					document.getElementById("checked").checked=true;
					}
			}
			function registe(){
				$('#loginTitle').css('color','#000');
				$('#loginForm').hide();
				$('#registeTitle').css('color','#337Ab7');
				$('#registeForm').show();
			}
			$(function(){
				$("#userdetail").popover({
		            trigger:'manual',
		            placement : 'bottom',
		            html: 'true',
		            content : '<div style="width:300px;height:300px;"></div>',
		            animation: false
		        }).on("mouseenter", function () {
		            var _this = this;
		            $(this).popover("show");
		            $(this).siblings(".popover").on("mouseleave", function () {
		                $(_this).popover('hide');
		            });
		        }).on("mouseleave", function () {
		            var _this = this;
		            setTimeout(function () {
		                if (!$(".popover:hover").length) {
		                    $(_this).popover("hide")
		                }
		            }, 0);
		        });
				//课程分类展示 
				$(".category").popover({
		            trigger:'manual',
		            placement : 'right',
		            html: 'true',
		            content : '',
		            animation: false
		        }).on("mouseenter", function () {
		            var cid = $(this).attr('c-id');
		            $('#' + cid).show();
		            $('#' + cid).hover(function(){
		            	$('#' + cid).show();
		            },function(){
		            	$('#' + cid).hide();
					});
		        }).on("mouseleave", function () {
		            var cid = $(this).attr('c-id');
		            $('#' + cid).hide();
		        });
			});
		</script>
		
		<!-- 头部-end -->
		
		<!-- 登录注册-start -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  style="position:fixed; top:30%;">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		        
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true" style="font-size:18px;">×</span>
		                </button>
		                <h4 class="modal-title"  id="loginTitle"  style="float: left; color: #337Ab7;cursor: pointer; " onclick="login();">登 录</h4>
		                <h4 class="modal-title"  id="registeTitle"  style="float: left;margin-left: 20px;cursor: pointer;" onclick="registe();">注 册</h4>
		                <div class="clearfix"></div>
		            </div>
		            
		            <div class="modal-body">
		               	<form action="${pageContext.request.contextPath}/log" method="post" id="loginForm" class="form-horizontal" style="padding: 0px 20px;">
		                      <div class="form-group">
		                          <input name="uName" type="text" class="form-control"  id="username"  placeholder="用户名" value=<%=username %>>
		                      </div>
		                      <div class="form-group help">
		                          <input name="uPwd" type="password" class="form-control"  id="password"  placeholder="密　码" value=<%=password %>>
		                      </div>
		                     
		                       <input name="checked" type="checkbox"  id="checked" value="checked"><span>remember me</span>
		                      
			<table valign="middle" >
				<tr>
					<td>
						<input type="text" name="code" placeholder="验证码" size="8">
					</td>
					<td>
						<a href="#" onclick="changeImg()"><img id="imgObj" src="${pageContext.request.contextPath}/getCode" title="看不清，换一张"></a>
					</td>
				</tr>
			</table>
		                      
		                      <a href="javascript:void(0)">
		                      	<input type="submit" value="log" />
		                      </a>
		                  </form>
		                   <form action="${pageContext.request.contextPath}/AllServlet" method="post" onSubmit="return validate()" id="registeForm" class="form-horizontal" style="padding: 0px 20px;display: none;">
		     
 <input type="hidden" name="methodName" value="0"/>
<h4>  昵称：<input type="text" name="name" id="name"></input><br></h4>
<h4>  密码：<input type="password" name="password" id="mima1"></input><br></h4>
<h4>  确认密码：<input type="password" name="password2" id="mima2"></input><br></h4>
<h4> 类型：<select id="type" name="type">  
    <option value="student">student</option>  
    <option value="teacher">teacher</option> 
</select>  </h4>
 <input type="submit" value="注册"/>
 
  </form>
		                  
		            </div>
					
		        </div>
		    </div>
		</div>
		<!-- 登录注册-end -->
		
		<div class="f-main">
			<!-- 轮播 分类-start -->
			<div class="clearfix">
				<div class=main-category>
					<div class="main-bg">
						<div class="main-bg-item " style="background-image: url('res/i/c1.jpg'); ">
							<div class="main-title-1"></div>
						</div>
		
						<div class="main-bg-item " style="background-image: url('res/i/c2.jpg'); ">
							<div class="main-title-2"></div>
						</div>
		
						<div class="main-bg-item " style="background-image: url('res/i/c3.jpg'); ">
							<div class="main-title-3"></div>
						</div>
					</div>
		
					<div class="f-nav-box">
						<div class="bg-nav">
							<a class="cur"></a> <a></a> <a></a>
						</div>
					</div>
					
					<div class="main-category-menu">
						<div class="category" c-id="a">
							<a><div class="group">前端开发 </div></a>
						</div>
						
						<div class="category" c-id="b">
							<a><div class="group">移动开发</div></a>
						</div>
						
						<div class="category" c-id="c">
							<a><div class="group">后端开发</div></a>
						</div>
						
						<div class="category" c-id="d">
							<a><div class="group">数据库</div></a>
						</div>
						
						<div class="category" c-id="e">
							<a><div class="group">运维&测试</div></a>
						</div>
						
						<div class="category" c-id="f">
							<a><div class="group">UI设计</div></a>
						</div>
					</div>
										
					<div class="main-category-submenu-content"  id="a" >
						-----------------------------------------------------------------------------------------------------------
						阶段一：前端小白入门系列课程->html基础，css基础，javascript基础，实战案例
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段二：响应式开发与常用框架->走近html5，css3进阶，移动基础与响应式开发
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段三：H5&JS进阶与组件化网页开发->html5进阶,jQuery进阶，组件化网页
						-----------------------------------------------------------------------------------------------------------
						阶段四：Vue移动页面开发与常用工具包->es6基础，vue初始，移动常用工具包
						-----------------------------------------------------------------------------------------------------------
					</div>
					
					<div class="main-category-submenu-content"  id="b" >
						-----------------------------------------------------------------------------------------------------------
						阶段一：零基础入门Android语法与界面->java基础语法，Java面向对象，ui基础
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段二：Android进阶：网络与数据存储->Android网络通信，常用框架，本地数据操作，项目实战案例
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段三：Android强化：服务与通信之广播、服务、蓝牙->Anderson服务与应用，app通信，常用框架，项目案例
						-----------------------------------------------------------------------------------------------------------
						阶段四：Android深化：多媒体之动画、游戏、音视频->动画，音视屏，常用框架，项目实战
						-----------------------------------------------------------------------------------------------------------
					</div>
					
					<div class="main-category-submenu-content"  id="c" >
						-----------------------------------------------------------------------------------------------------------
						阶段一：Java零基础入门->java基础语法，java面向对象，Java常用工具类
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段二：从网页搭建入门Java Web->网页搭建入门，javaweb基础入门，基础进阶，常用功能，项目实战
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段三：Java Web进阶：数据库与MyBatis入门->初始数据库操作，项目管理必备及Mybatis入门
						-----------------------------------------------------------------------------------------------------------
						阶段四：Java Web强化：SSM框架整合->SPring从入门到进阶，Mybatis进阶，SprinMVC初体验
						-----------------------------------------------------------------------------------------------------------
					</div>
					
					
					
					<div class="main-category-submenu-content"  id="d" >
						-----------------------------------------------------------------------------------------------------------
						阶段一：前端小白入门系列课程->html基础，css基础，javascript基础，实战案例
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段二：响应式开发与常用框架->走近html5，css3进阶，移动基础与响应式开发
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段三：H5&JS进阶与组件化网页开发->html5进阶,jQuery进阶，组件化网页
						-----------------------------------------------------------------------------------------------------------
						阶段四：Vue移动页面开发与常用工具包->es6基础，vue初始，移动常用工具包
						-----------------------------------------------------------------------------------------------------------

					</div>
					
					<div class="main-category-submenu-content"  id="e" >
						-----------------------------------------------------------------------------------------------------------
						阶段一：零基础入门Android语法与界面->java基础语法，Java面向对象，ui基础
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段二：Android进阶：网络与数据存储->Android网络通信，常用框架，本地数据操作，项目实战案例
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段三：Android强化：服务与通信之广播、服务、蓝牙->Anderson服务与应用，app通信，常用框架，项目案例
						-----------------------------------------------------------------------------------------------------------
						阶段四：Android深化：多媒体之动画、游戏、音视频->动画，音视屏，常用框架，项目实战
						-----------------------------------------------------------------------------------------------------------

					</div>
					
					<div class="main-category-submenu-content"  id="f" >
						-----------------------------------------------------------------------------------------------------------
						阶段一：Java零基础入门->java基础语法，java面向对象，Java常用工具类
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段二：从网页搭建入门Java Web->网页搭建入门，javaweb基础入门，基础进阶，常用功能，项目实战
						-----------------------------------------------------------------------------------------------------------
						-----------------------------------------------------------------------------------------------------------
						阶段三：Java Web进阶：数据库与MyBatis入门->初始数据库操作，项目管理必备及Mybatis入门
						-----------------------------------------------------------------------------------------------------------
						阶段四：Java Web强化：SSM框架整合->SPring从入门到进阶，Mybatis进阶，SprinMVC初体验
						-----------------------------------------------------------------------------------------------------------

					</div>
					
				</div>
			</div>
			<!-- 轮播 分类-end -->
			
			<!-- 实战推荐-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">实战推荐</h3>
				<div class="types-content">
					
					<a href="video.jsp">
					<div class="course-card-container">
						<div class="course-card-top green-bg">
							<span>测试</span>
						</div>

						<div class="course-card-content">
							<h3 class="course-card-name">Android自动化测试实战 工具 框架 脚本</h3>
							<p title="找Android自动化测试工作必学的主流工具、框架和自动化脚本">找Android自动化测试工作必学的主流工具、框架和自动化脚本</p>
							<div class="course-card-bottom">
								<div class="course-card-info">178人在学</div>
								
							</div>
						</div>
					</div>
					</a>

					<a href="video.jsp">
					<div class="course-card-container">
						<div class="course-card-top pink-bg">
							<span>WebApp</span>
						</div>
						<div class="course-card-content">
							<h3 class="course-card-name">AngularJS仿拉勾网WebApp 开发移动端单页应用</h3>
							<p title="基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用">基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">344人在学</div>
	
								
							</div>
						</div>
					</div>
					</a>
					
					<a href="video.jsp">
					<div class="course-card-container">
						<div class="course-card-top brown-bg">
							<span>Django</span>
						</div>
	
						<div class="course-card-content">
							<h3 class="course-card-name">强力django杀手级xadmin 打造上线标准的在线教育平台</h3>
							<p title="全面掌握django框架,轻松应对python web开发工作">全面掌握django框架,轻松应对python web开发工作</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">488人在学</div>
							
							</div>
						</div>
					</div>
					</a>
	
					<a href="video.jsp">
					<div class="course-card-container">
						<div class="course-card-top purple-bg">
							<span>Python</span>
						</div>
	
						<div class="course-card-content">
							<h3 class="course-card-name">Python高级编程技巧实战</h3>
							<p title="精选50个python训练任务，提升实战技能与高效编程技巧">精选50个python训练任务，提升实战技能与高效编程技巧</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">813人在学</div>
							
							</div>
						</div>
					</div>
					</a>
					
					<a href="video.jsp">
					<div class="course-card-container" style="margin-right: 0px;">
						<div class="course-card-top green-bg">
							<span>PHP</span>
						</div>
						
						<div class="course-card-content">
							<h3 class="course-card-name">前端后台ThinkPHP开发整站</h3>
							<p title="用PHP+MySQL+Ajax开完新闻资讯整站，实现“小全栈”的梦想">用PHP+MySQL+Ajax开完新闻资讯整站，实现“小全栈”的梦想</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">2324人在学</div>
							
							</div>
						</div>
					</div>
					</a>
					
				</div>
			</div>
			<!-- 实战推荐-end -->
			
			
			<!-- java课程-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">Java开发工程师</h3>
				<div class="types-content-left "
					style="background-image: url(http://img.mukewang.com/58ac18fd00012a4202240348.jpg);">
					<div class="course-card-container-fix">
						<div class="course-card-content">
							<h3 class="course-card-name">Java职业路径</h3>
							<p class="color-fff" title="带你研究Java技术框架">带你研究Java技术框架，系统地学习java技术</p>
							<div class="course-card-bottom" style="margin-top: 50px;">
								<div class="course-card-info color-fff">了解详情 →</div>
							</div>
						</div>
					</div>
				</div>
	
				<div class="types-content-right ">
					<div class="types-content-banner ">
						<a target="_blank" href="http://coding.imooc.com/class/81.html">
							<div class="types-content-banner-block green-bg"  style="margin-right:20px;" >
								Spring框架实战讲解！
							</div>
						</a>
						<a target="_blank" href="http://coding.imooc.com/class/76.html">
							<div class="types-content-banner-block gray-bg"  >
								MyBatis框架实战讲解！
							</div>
						</a>
					</div>
					
					<div class="clearfix">
						<a href="video.jsp">
							<div class="course-card-container">
								<div class="course-card-top green-bg">
									<span>测试</span>
								</div>
	
								<div class="course-card-content">
									<h3 class="course-card-name">Android自动化测试实战 工具 框架 脚本</h3>
									<p title="找Android自动化测试工作必学的主流工具、框架和自动化脚本">找Android自动化测试工作必学的主流工具、框架和自动化脚本</p>
									<div class="course-card-bottom">
										<div class="course-card-info">
											初级<span>·</span>178人在学
										</div>
									</div>
								</div>
							</div>
						</a> 
						
						<a href="video.jsp">
							<div class="course-card-container">
								<div class="course-card-top pink-bg">
									<span>WebApp</span>
								</div>
								<div class="course-card-content">
									<h3 class="course-card-name">AngularJS仿拉勾网WebApp 开发移动端单页应用</h3>
									<p title="基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用">基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用</p>
									<div class="clearfix course-card-bottom">
										<div class="course-card-info">
											初级<span>·</span>3444人在学
										</div>
									</div>
								</div>
							</div>
						</a>
		<a href="video.jsp">
						<div class="course-card-container">
							<div class="course-card-top brown-bg">
								<span>Django</span>
							</div>
	
							<div class="course-card-content">
								<h3 class="course-card-name">强力django杀手级xadmin 打造上线标准的在线教育平台</h3>
								<p title="全面掌握django框架,轻松应对python web开发工作">全面掌握django框架,轻松应对python
									web开发工作</p>
								<div class="clearfix course-card-bottom">
									<div class="course-card-info">
										初级<span>·</span>4888人在学
									</div>
								</div>
							</div>
						</div>
	
	      </a>
	      <a href="video.jsp">
						<div class="course-card-container" style="margin-right: 0px;">
							<div class="course-card-top purple-bg">
								<span>Python</span>
							</div>
	
							<div class="course-card-content">
								<h3 class="course-card-name">Python高级编程技巧实战</h3>
								<p title="精选50个python训练任务，提升实战技能与高效编程技巧">精选50个python训练任务，提升实战技能与高效编程技巧</p>
								<div class="clearfix course-card-bottom">
									<div class="course-card-info">
										高级<span>·</span>81333人在学
									</div>
								</div>
							</div>
						</div>
				 </a>
	
					</div>
				</div>
			</div>
			<!-- java课程-end -->
			
			<!--名校讲师-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">名校讲师</h3>
				<a >
					<div class="lecturer-card-container">
						<div class="lecturer-item">
							<img class="lecturer-uimg" src="res/i/header.jpg">
							<span class="lecturer-name">颜聪</span>
							<span class="lecturer-title">武汉大学</span>
							<span class="lecturer-p" >武汉大学高级讲师，擅长写代码</span>
						</div>
					</div>
				</a>
					
				<a >
					<div class="lecturer-card-container">
						<div class="lecturer-item">
							<img class="lecturer-uimg" src="res/i/header.jpg">
							<span class="lecturer-name">赵炳</span>
							<span class="lecturer-title">武汉大学</span>
							<span class="lecturer-p" >武汉大学高级讲师，擅长写代码</span>
						</div>
					</div>
				</a>
            <a>
				<div class="lecturer-card-container">
					<div class="lecturer-item">
						<img class="lecturer-uimg" src="res/i/header.jpg">
						<span class="lecturer-name">周航</span>
						<span class="lecturer-title">武汉大学</span>
						<span class="lecturer-p" >武汉大学高级讲师，擅长写代码</span>
					</div>
				</div>
				</a>

            <a >
				<div class="lecturer-card-container"  >
					<div class="lecturer-item">
						<img class="lecturer-uimg" src="res/i/header.jpg">
						<span class="lecturer-name">许岩</span>
						<span class="lecturer-title">武汉大学</span>
						<span class="lecturer-p" >武汉大学高级讲师，擅长写代码</span>
					</div>
				</div>
				</a>
				
				<a >
				<div class="lecturer-card-container" style="margin-right: 0px;">
					<div class="lecturer-item">
						<img class="lecturer-uimg" src="res/i/header.jpg">
						<span class="lecturer-name">周航</span>
						<span class="lecturer-title">武汉大学</span>
						<span class="lecturer-p" >武汉大学高级讲师，擅长写代码</span>
					</div>
				</div>
				</a>
					
			</div>
			<!--名校讲师-end -->
		</div>
		
		
		<!-- 页脚-start -->
		<div class="f-footer">
			<div class="f-footer-box clearfix">
				<div class="footer-link">
					<a href="javascript:void(0);"  target="_blank" title="企业合作">企业合作</a> 
					<a href="javascript:void(0);" target="_blank" title="联系我们">联系我们</a> 
					<a href="javascript:void(0);" target="_blank" title="常见问题">常见问题</a> 
					<a href="javascript:void(0);" target="_blank" title="意见反馈">意见反馈</a>
					<a href="javascript:void(0);" target="_blank" title="友情链接">友情链接</a>
				</div>
				<div class="footer-copyright">
					<span>©&nbsp;2018&nbsp; 实训 </span>
				</div>
			</div>
		</div>
		<!-- 页脚-end-->
		
	</body>
	
	<script>
	$(function(){
		var index = 0;
		var timer = 4000;
		$('.bg-nav a').click(function(){
			index = $('.bg-nav a').index($(this));
			rollBg(index);
		});
		$('.index-roll-item').click(function(){
			index = $('.index-roll-item').index($(this));
			rollBg(index);
		});
		var rollBg = function(i){
			$('.main-bg-item').fadeOut(1000);
			$($('.main-bg-item')[i]).fadeIn(1000);
			$('.bg-nav a').removeClass('cur');
			$($('.bg-nav a')[i]).addClass('cur');
			$('.index-roll-item').removeClass('cur');
			$($('.index-roll-item')[i]).addClass('cur');
		}
		setInterval(function(){
			index += 1;
			index = index%3;
			rollBg(index);
		}, timer);
		
	});
	</script>
</html>