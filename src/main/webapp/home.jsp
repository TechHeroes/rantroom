<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="Welcome to RantRoom. A public forum for people to rant about anything under the sun. 
        	Here, you can speak your heart out with complete anonymity. So, what are you waiting for? Sign up instantly and start ranting.">
        <meta name="author" content="Saad Khan">
        <title>RantRoom | Home</title>

        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/style.css" rel="stylesheet">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--web-fonts-->
        <link href='//fonts.googleapis.com/css?family=Jura:400,300,500,600' rel='stylesheet' type='text/css'>
        <!--favicons -->
        <link rel="apple-touch-icon" sizes="57x57" href="${contextPath}/resources/favicons/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="${contextPath}/resources/favicons/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="${contextPath}/resources/favicons/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="${contextPath}/resources/favicons/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="${contextPath}/resources/favicons/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="${contextPath}/resources/favicons/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="${contextPath}/resources/favicons/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="${contextPath}/resources/favicons/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="${contextPath}/resources/favicons/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192"  href="${contextPath}/resources/favicons/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="${contextPath}/resources/favicons/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="${contextPath}/resources/favicons/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="${contextPath}/resources/favicons/favicon-16x16.png">
        <link rel="manifest" href="${contextPath}/resources/favicons/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">
        <!--favicons-end -->
        <script src="https://kit.fontawesome.com/7eac90ac67.js" crossorigin="anonymous"></script>
    </head>
    <body>
		<header id="header">
            <nav class="navbar navbar-default">
				<div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="navbar-header">
                              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                              </button>
                              <a class="navbar-brand" href="./home"><img class="logo" alt="RantRoom logo" src="${contextPath}/resources/images/rantroomlogo_bl.png" /></a>
                            </div>
                        </div>    
                        <div class="col-sm-9">
                            <div class="row navbar-collapse collapse" id="bs-example-navbar-collapse-1" aria-expanded="false">                                
                                <div class="col-sm-8 menu">    
                                      <ul class="nav navbar-nav">
                                        <li><a href="home">Home</a></li>
                                        <li><a href="#">Rants</a></li> 
                                        <li class="dropdown">
                                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categories <span class="caret"></span></a>
                                            <ul class="dropdown-menu sublist" role="menu">
                                                    <li><a href="#">Politics</a></li>
                                                    <li><a href="#">Technology</a></li>
                                                    <li><a href="#">Sports</a></li>
                                                    <li><a href="#">Social Issues</a></li>
                                                    <li><a href="#">Social Media</a></li>
                                                    <li><a href="#">Work</a></li>
                                                    <li><a href="#">Religion</a></li>
                                            </ul>
                                        </li>
                                        <li class="dropdown">
                                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">About <span class="caret"></span></a>
                                            <ul class="dropdown-menu sublist" role="menu">
                                                    <li><a href="#">About</a></li>
                                                    <li><a href="#">Rules</a></li> 
                                            </ul>
                                        </li>  
                                      </ul>
                                </div><!--inner col-sm-8--> 
                                <div class="col-sm-4">                                    
                                      <ul class="nav navbar-nav navbar-right menu">
                                      	<c:choose>
                                      		<c:when test="${user != null}">
	                                            <li class="dropdown">
                                          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${user.getUsername()}<span class="caret"></span></a>
                                            			<ul class="dropdown-menu sublist" role="menu">
		                                                    <li><a href="${contextPath}/users/profile/${user.getUsername()}">Profile</a></li>		                                                    
		                                                    <li><a href="${contextPath}/users/profile/settings">Settings</a></li>
		                                                    <li><a onclick="document.forms['logoutForm'].submit()">Logout</a></li> 
                                            			</ul>
                                        		</li>
                            		            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                				</form>  	                                            
                                        	</c:when>
                                        	<c:otherwise>
                                        		<li><a href="${contextPath}/login">Login</a></li>
	                                            <li><a class="home-links" href="${contextPath}/registration">Sign Up</a></li>
                                        	</c:otherwise>
                                        </c:choose>	    
                                      </ul>     
                                </div><!--inner col-sm-4--> 
                            </div><!--inner-row-->
                        </div><!--outer col-sm-8-->   
                    </div><!--outer-row-->    
                </div><!--container-->
			</nav>
		</header>
        <div id="main">
            <div class="container" id="sub-content">
                <div class="row profile">
                	<c:choose>
                		<c:when test="${postregistration!=null}">
		                   <br><p style="font-weight: 500;font-size: 18px;margin-left: 10px">${postregistration}</p>
                		</c:when>
                		<c:otherwise>
		                   <p id="intro">Welcome to RantRoom. A public forum for people to rant about anything under the sun. Here, you can speak your heart out with complete anonymity. So, what are you waiting for? Sign up instantly and start ranting.</p>                                       
                		</c:otherwise>
                	</c:choose>                	
                </div><!-- row --> 
                <div class="row">
                    <div class="col-md-10 col-sm-12">
                    	<div class="clearfix">
                    		<c:choose>
		                    	<c:when test="${rants != null}">
		                        	<c:forEach  items="${rants}" var ="rantData">
                                        <div class="list col-sm-6 col-md-6">
                                            <div class="list-item" style="">
                                            	<div class="row">
	                                            	<div role="button" class="col-sm-1"  style="cursor: pointer;">
														<canvas class="" height="42" width="42" style="position: absolute; top: -5px; left: -5px; width: 42px; height: 42px;"></canvas>
														<span class="user_thumb_span " role="link">
															<img alt="${rantData.getUser().getUsername()} &#39;s profile picture" class="user_thumb_img" src="${contextPath}/uploads/${rantData.getUser().getUserProfile().getFileName()}">
														</span>
													</div>
	                                                <!-- <div class="rantOwner col-sm-11"> 
		                                                <div style="margin: 0 0 5px"> -->
		                                                <div class="rantOwner col-sm-11">
		                                                	<a href="${contextPath}/users/profile/${rantData.getUser().getUsername()}">
		                                                	${rantData.getUser().getUsername()}</a>
		                                                </div>
	                                                <!-- </div> -->
	                                            </div>    
                                                <div class="list-content">
                                                    <h4 class="page-header" id="rantTitle">
                                                    	<c:set var="shortTitle" value="${fn:substring(rantData.getRantTitle(), 0, 30)}" />
                                                    	<a href="${contextPath}/rant/${rantData.getId()}">
                                                    		${shortTitle}
                                                    		<%-- <input type="hidden" id="rId" value="${rantData.getId()}"> --%>
                                                    	</a>
                                                    </h4>
                                                    <c:set var="shortDesc" value="${fn:substring(rantData.getRantDesc(), 0, 150)}" />
                                                    <p><i class="fa fa-quote-left"></i> &nbsp;${shortDesc}....</p>
                                                </div><!-- list-content -->
                                                <div>                                                	
	                                                <c:choose>
                                                		<c:when test="${likeRepo.findByRantId(rantData.getId()).getLikeFlag() eq true}">
		                                                	<i data-mode="1" data-rid="${rantData.getId()}" class="lu fas fa-thumbs-up" style="color:#337ab7;font-size: 1.5em"></i>
                                                		</c:when>
                                                		<c:otherwise>	                                                		                                                		
		                                                	<i  data-mode="-1" data-rid="${rantData.getId()}" class="lu far fa-thumbs-up" style="color:#337ab7;font-size: 1.5em"></i>
                                                		</c:otherwise>
	                                                </c:choose>
                                                </div>
                                                <div>
	                                                <p style="font-style: italic;font-size: 10px;margin-top: 12px; ">${rantData.getUpdatedAt()}</p>                                                
                                                </div>
                                                <div>  
                                                </div>
                                            </div><!--list-item -->								
		                                  </div><!--list -->
		                            </c:forEach> 
		                             <br />
		                          </c:when>        		                                
	                          	<c:otherwise>
	                              	<div class="list">
		                                        <div class="list-item" style="">
		                                            <div class="list-content">
		                                                <p><i class="fa fa-quote-left"></i> &nbsp;No rants found</p>
		                                            </div><!-- list-content -->
		                                        </div><!--list-item -->								
		                            </div><!-- rants-list -->
		                            <br />
		                          </c:otherwise>
	                        </c:choose>
	                    </div><!-- clearfix -->		                                
	                </div><!-- inner row -->                	
                </div>  <!-- row -->
         	</div> <!-- /container -->         
        </div><!-- main -->        

        <!-- footer -->
        <footer id="footer" class="text-center">
			<div class="container social">
				<div class="col-sm-12">
					<a href="https://fb.me/rantsroom" class="icon-circle fb" target="_blank" title="Like us on Facebook"> 
						<img class="logo" alt="Facebook" src="${contextPath}/resources/social/facebook_transparent-1c6a690dcde061958f50f712404c764613cc193d3f69e81734c8c9a7f36131e4.png" />
					</a> 
					<a href="https://twitter.com/rantsroom" class="icon-circle tw" target="_blank" title="Follow us on Twitter"> 
						<img class="logo" alt="Twitter"	src="${contextPath}/resources/social/twitter_transparent-39f60ddc31bce0d3e3d6ae72af7fbe74dc95e01b7d7a6f66cc38efabda3bb6da.png" />
					</a>
					<a href="https://in.pinterest.com/rantsroom/" class="icon-circle pt" target="_blank" title="Go to Pinterest Page"> 
						<img class="logo" alt="Pinterest" src="${contextPath}/resources/social/pinterest_transparent-a58a9baa98ec9a2e4fed6d4ab7ffdb69bc607c82649a486544b2b5dab03364ef.png" />
					</a>
				</div>
			</div>
            <p id="copyright">&copy; ${year} Team RantRoom. All rights reserved | Designed by <a href="http://www.khansaad.com/" target="_blank" >Saad </a>| Mentored by <a href="http://www.roosnam.com/" target="_blank" >Mansoor</a></p>
        </footer> 
		<script src="${contextPath}/resources/js/jquery.min.js"></script>
		<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${contextPath}/resources/js/like-ajax.js"></script>
    </body>
</html>