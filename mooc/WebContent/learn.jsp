<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

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
	//
    function validate()
    {
    var psw=document.getElementById("psw").value;
    var opsw=document.getElementById("opsw").value;
    var mima1=document.getElementById("npsw").value;
    var mima2=document.getElementById("npsw2").value;
    if(mima1==""||opsw==""||mima2==""){
    alert("密码不能为空");
    document.getElementById("opsw").focus();
    return false;
    }
    else if(opsw!=psw)
    {
    	alert("原始密码错误");
        document.getElementById("opsw").focus();
        return false;            
    }
    else if(mima1!=mima2)
    {
    alert("请确认密码一致");
    document.getElementById("npsw").focus();
    return false;
    }
    else
    {
    alert("修改密码成功");
    return true;
    }
    }                        //check password infomation--YC
    
    
    
    $("#btn1").click(function(){
		$("#btn1").toggle();
	});
    </script>
	</head>

	<body>
	<%String id = session.getAttribute("Id").toString();;//request.getAttribute("Id").toString();%>
	<%String name = session.getAttribute("name").toString();%>
	<%String email = session.getAttribute("email").toString();%>
	<%String tel = session.getAttribute("tel").toString();%>
	<%String sex = session.getAttribute("sex").toString();%>
	<%String des = session.getAttribute("des").toString();%>
	<%String psw = session.getAttribute("psw").toString();%>
	<!-- 头部-start -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		style="position: fixed; top: 30%;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" style="font-size: 18px;">×</span>
					</button>
					<h4 class="modal-title" id="loginTitle"
						style="float: left; color: #337Ab7; cursor: pointer;"
						onclick="login();">个人信息</h4>
					<h4 class="modal-title" id="registeTitle"
						style="float: left; margin-left: 20px; cursor: pointer;"
						onclick="registe();">修改密码</h4>
					<div class="clearfix"></div>
				</div>
				<!-- modify information -->
				>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/ModifyInfo"
						method="post" id="loginForm" class="form-horizontal"
						style="padding: 0px 20px;">
						<div class="form-group">
							account:<%=id%><input type="hidden" name="id" value=<%=id%>>
						</div>
						<div class="form-group">
							name:<input name="name" type="text" class="form-control"
								id="username" value=<%=name%>>
						</div>
						<div class="form-group help">
							sex：
							<h4>
								<select id="sex" name="sex">
									<option id="male" value="male">male</option>
									<option id="female" value="female">female</option>
								</select>
							</h4>
						</div>
						<div class="form-group">
							tel:<input name="tel" type="text" class="form-control"
								id="username" placeholder="tel" value=<%=tel%>>
						</div>
						<div class="form-group help">
							email:<input name="email" type="text" class="form-control"
								id="password" placeholder="email" value=<%=email%>>
						</div>
						<div class="form-group help">
							person description:<input name="des" type="text"
								class="form-control" id="password" placeholder="des"
								value=<%=des%>>
						</div>



						<a href="javascript:void(0)"> <input type="submit" value="保存" />
						</a>
					</form>
					<!-- modify password -->
					<form action="${pageContext.request.contextPath}/ModifyPsw"
						method="post" onSubmit="return validate()" id="registeForm"
						class="form-horizontal" style="padding: 0px 20px; display: none;">
						<input type="hidden" name="methodName" value="0" /> <input
							type="hidden" name="id" value=<%=id%>> <input
							type="hidden" name="psw" id="psw" value=<%=psw%>> <input
							type="hidden" name="name" value=<%=name%>> <input
							type="hidden" name="sex" id="sex1" value=<%=sex%>> <input
							type="hidden" name="tel" value=<%=tel%>> <input
							type="hidden" name="email" value=<%=email%>> <input
							type="hidden" name="des" value=<%=des%>>
						<h4>
							原始密码:<input type="password" name="opsw" id="opsw"></input><br>
						</h4>
						<h4>
							新密码：<input type="password" name="npsw" id="npsw"></input><br>
						</h4>
						<h4>
							确认新密码：<input type="password" name="npsw2" id="npsw2"></input><br>
						</h4>

						<input type="submit" value="提交" />

					</form>

				</div>

			</div>
		</div>
	</div>
	<div class="f-header" id="top">
		<div class="f-header-box clearfix">
			<a  class="logo" title="IT在线学习平台"></a>
			<nav class="header-nav">
				<a href="index.jsp" class="header-nav-item">首 页</a> <a
					href="list.jsp" class="header-nav-item">课 程</a> <a
					href="stCourse.jsp" class="header-nav-item">我的课堂</a>
				<form class="header-nav-item"
					action="${pageContext.request.contextPath}/joinServlet"
					method="post">
					<input type="text" name="cid" name="cid"> <input
						type="submit" value="加入课堂"
						style="position: absolute; left: 780px; top: 30px; border-radius: 3px;">
					<input type="hidden" name="id" value=<%=id%>>
				</form>

			</nav>

			<nav class="header-nav" style="float: right">

				<div id="nav">
					<ul>
						<li><a href="#">Welcome,<%=name%>
						</a>
							<ul>
								<li><a href="#myModal" data-toggle="modal"
									onclick="login();">我的信息</a></li>

							</ul>
						<li><a href="index.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退出</a></li>
					</ul>
				</div>
			</nav>


			<nav class="header-nav" style="float: right">
				<button onclick=$( "[type=file] ").click()  id="btn1"
					style="position: absolute; border: none; left: 937px; top: 40px;">我的头像</button>
				<div id="nav" class="am-form-file">
					<input style="display: none;" type=file name="doc" id="doc"
						onchange="javascript:setImagePreview();"> <img
						id="preview" width=-1 height=-1 style="diplay: none" />


				</div>
			</nav>





		</div>
	</div>

	<script type="text/javascript">
		
			function login(){
				$('#loginTitle').css('color','#337Ab7');
				$('#loginForm').show();
				$('#registeTitle').css('color','#000');
				$('#registeForm').hide();
				var sex=document.getElementById("sex1").value;
				if(sex=="female")
					{
					document.getElementById("female").selected=true;
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

	<!-- 登录注册-end -->
		
		<div class="f-main clearfix">
			<!-- 基本信息 -->
			<div class="main-course-left">
					<div class="course-info">
						<div class="course-title">带您完成神秘的涟漪按钮效果-入门篇</div>
						
						<div class="course-meta">
							<a href="video.jsp" class="learn-btn" >继续学习</a>
							
							<div class="static-item"  >
								<div class="meta">上次学到</div>
								<div class="meta-value">1-1 感受神秘的涟漪效果之美感受神秘的涟漪效果之美</div>
							</div>
							<div class="static-item"  >
								<div class="meta">学习人数</div>
								<div class="meta-value">1452</div>
							</div>
							<div class="static-item">
								<div class="meta">难度级别</div>
								<div class="meta-value">中级</div>
							</div>
							<div class="static-item" style="border:none;">
								<div class="meta">课程时长</div>
								<div class="meta-value">1小时25分</div>
							</div>
							<a onclick="doFollow(this,${(course.id)})" href="javascript:void(0)" class="following" style="float: right;margin-top:5px;" title="收藏">
							</a>
						</div>
						
						<div class="course-brief">
								简介：安卓声音录制与播放功能实现，包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放，还包括录制到字节流，从字节流播放，可以实现视频直播/实时对讲功能中的声音录制和播放。 Android大牛齐聚之地，这里总有人为你答疑解惑！慕课网Android讨论群 556384357
						</div>
						
						<div class="course-menu">
							<a  href="learn.jsp"><span class="menu-item cur">章节</span></a>
							<a  href="comment.jsp"><span class="menu-item">评论</span></a>
						</div>
				</div>
				<!-- 基本信息-end -->		
				<!-- 课程章节 - start -->		
				<div>
						<div class="chapter">
							<a href="video.jsp" class="js-open">
								<h3>
									<strong><div class="icon-chapter">=</div> 第1章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub">
								<a href="video.jsp" >
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 1-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)
										<div class="preview-btn">继续学习</div>
									</li>
								</a>
								
								<a href="video.jsp" >
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 1-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)
									</li>
								</a>
							</ul>
						</div>
						
						<div class="chapter">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong>
										<div class="icon-chapter">=</div> 第2章 课程介绍
									</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub">
								<a href="video.jsp" >
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 2-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)
									</li>
								</a>
								
								<a href="video.jsp ">
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 2-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)
									</li>
								</a>
							</ul>
						</div>
						
						<div class="chapter">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong><div class="icon-chapter">=</div> 第3章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							
							<ul class="chapter-sub">
								<a href="video.jsp" >
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 3-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)
									</li>
								</a>
								
								<a href="video.jsp" >
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 3-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)
									</li>
								</a>
								
								<a href="video.jsp" >
									<li class="chapter-sub-li">
										<i class="icon-video">▶</i> 3-3 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)
									</li>
								</a>
								
							</ul>
						</div>
						
					</div>
				<!-- 课程章节 - end -->		
			</div>
			
			<!-- 教师信息&推荐课程 - start -->		
			<div class="main-course-right"  >
				
				<div class="lecturer-item" style="width: 100%;">
					<img class="lecturer-uimg" src="res/i/header.jpg">
					<span class="lecturer-name">学生</span>
					<span class="lecturer-title">武汉大学</span>
					<span class="lecturer-p" >武汉大学高级讲师，擅长写代码</span>
					<a href="javascript:void(0)" ><span class="follow-btn">关注+</span></a>
				</div>
			</div>		
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
		
		<script type="text/javascript">
			$(function(){
				$('.chapter li').hover(function(){
					$(this).find('.icon-video').css('color','#FFF');
				},function(){
					$(this).find('.icon-video').css('color','#777');
				});
				
				$('.js-open').click(function(){
					var display = $(this).parent().find('ul').css('display');
					if(display == 'none'){
						$(this).parent().find('ul').css('display','block');
						$(this).find('.drop-down').html('▼');
					}else{
						$(this).parent().find('ul').css('display','none');
						$(this).find('.drop-down').html('▲');
					}
				});
			});	
		</script>
		
	</body>
	
</html>
