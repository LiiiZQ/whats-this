<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"
		 import="java.util.*"
		 import="modle.Comment"		 
		 
		 
		 %>


<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>在线网校学习平台</title>
		
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
	</head>

	<body>
		<!-- 头部-start -->
		<div class="f-header">
			<div class="f-header-box clearfix">
				<a  class="logo" title="网校在线学习平台"></a>
				<nav class="header-nav">
					<a href="success.jsp" class="header-nav-item">首 页</a>
					
				</nav>

				</nav>
			</div>
		</div>
		<script type="text/javascript">
			function login(){
				$('#loginTitle').css('color','#337Ab7');
				$('#loginForm').show();
				$('#registeTitle').css('color','#000');
				$('#registeForm').hide();
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


		<div class="f-main clearfix">
			<div class="main-course-left">
					<div class="course-info">
						<div class="course-title" style="font-size: 24px;">1-1 感受神秘的涟漪效果</div>
						
						<div class="course-video">
							<video src="res/demo.mp4" width="100%" height="100%" controls preload></video>
						</div>
						
						<div class="course-menu">
							<a  href="javascript:void(0)"><span class="menu-item  cur">评论</span></a>
						</div>
				</div>
				
				<!-- 评论-start -->
			
				
						
				<div>
			
				<% ArrayList array=(ArrayList)session.getAttribute("comment");
				for(int i=0;i<array.size();i++)
				{
					
					Comment comment=(Comment)array.get(i);
					if(comment.videoID.equals("1"))
					{%>
					
						<div class="comment clearfix">
							<div class="comment-header"><img class="lecturer-uimg" src="res/i/header.jpg"></div>
							<div class="comment-main">
								<div class="user-name"><%=comment.username %></div>
								<div class="comment-content"><%=comment.content %></div>
								<div class="comment-footer">
									<span><%=comment.time %></span>									
								</div>
							</div>
						</div>		
					<%}%>					
					
				<% }%>      
				
								</div> 
				<%String name = session.getAttribute("name").toString();%>
				<!-- 发布评论-start -->
				<div style="margin-top: 20px;">
					<div>
						<span id="commentTitle">发布评论：</span>
						<span id="commentTip" style="margin-left: 10px;color:#777;">长度小于200</span>
					</div>
					<form id="commentForm" action="${pageContext.request.contextPath}/CreateComment"  method="post"  onSubmit="return check()" style="margin: 5px 0px;">
						<input type="hidden" id="id" name="name" value=<%=name %>>

						<input type="hidden" name="courseId" value="1">
						
						<textarea id="content" name="content" rows="" cols="100"></textarea>
						<input type="submit" value="发布" class="btn" >
					</form>
					
				</div>
			<script type="text/javascript">
			function check()
			{
				
				var cont=document.getElementById("content").value;
				if(cont=="")
					{
					alert("内容不能为空!");
					return false;
					}
				if(cont.length()>=200)
					{
					alert("超出字数限制!");
					return false;
					}
				return true;
			}
			</script>
				<!-- 评论-end -->
			</div>
			
			<!-- 章节-start -->
			<div class="main-course-right"  >
				<h4 class="mt-50">所有章节</h4>
				<div class="video-course-fix-parent">
					<div class="video-course-fix">
						<div class="chapter" style="padding: 0px ;border: none;">
							<a href="res/demo.mp4" class="js-open">
								<h3>
									<strong>第1章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>

							<ul class="chapter-sub" style="padding-left:10px;">
								<a href="res/demo.mp4" >
									<li class="ellipsis video-li"><i class="icon-video">▶</i> 1-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)</li>
								</a>
								
								<a href="res/demo.mp4" >
									<li class="ellipsis  video-li"><i class="icon-video">▶</i> 1-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)</li>
								</a>
							</ul>

						</div>
					
						<div class="chapter" style="padding: 0px ;border: none;">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong>第2章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub" style="padding-left:10px;">
								<a href="res/demo.mp4" >
									<li class="ellipsis video-li"><i class="icon-video">▶</i> 1-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)</li>
								</a>
								
								<a href="res/demo.mp4" >
									<li class="ellipsis  video-li"><i class="icon-video">▶</i> 1-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)</li>
								</a>
							</ul>
						</div>
					
						<div class="chapter" style="padding: 0px ;border: none;">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong>第3章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub" style="padding-left:10px;">
								<a href="res/demo.mp4" >
									<li class="ellipsis video-li"><i class="icon-video">▶</i> 1-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)</li>
								</a>
								
								<a href="res/demo.mp4" >
									<li class="ellipsis  video-li"><i class="icon-video">▶</i> 1-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)</li>
								</a>
							</ul>
						</div>
					
						<div class="chapter" style="padding: 0px ;border: none;">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong>第4章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub" style="padding-left:10px;">
								<a href="res/demo.mp4" >
									<li class="ellipsis video-li"><i class="icon-video">▶</i> 1-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)</li>
								</a>
								
								<a href="res/demo.mp4" >
									<li class="ellipsis  video-li"><i class="icon-video">▶</i> 1-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)</li>
								</a>
							</ul>
						</div>
					
						<div class="chapter" style="padding: 0px ;border: none;">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong>第5章 课程介绍</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub" style="padding-left:10px;">
								<a href="res/demo.mp4" >
									<li class="ellipsis video-li"><i class="icon-video">▶</i> 1-1 使用RecyclerView优雅实现复杂布局-课程介绍 (06:46)</li>
								</a>
								
								<a href="res/demo.mp4" >
									<li class="ellipsis  video-li"><i class="icon-video">▶</i> 1-2 包括录制到文件、播放文件，可以实现类似于微信的语音消息发送与播放 (06:46)</li>
								</a>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- 章节-end -->
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
					$(this).find('.icon-video').css('color','#0089D2');
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
