<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>RantRoom | Signup</title>

        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
<!--        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">-->
        <!--web-fonts-->
        <link href='//fonts.googleapis.com/css?family=Jura:400,300,500,600' rel='stylesheet' type='text/css'>
        <!--//web-fonts-->
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
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                                        <li></li>
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
                                            <li><a href="${contextPath}/login">Login</a></li>
                                            <li><a class="home-links" href="${contextPath}/registration">Sign Up</a></li>
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
                <!--signin-form-->
                <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="form-body">
                        <h3>Register</h3>

                        <div class="login-form">
                            <form:form method="POST" modelAttribute="userForm">
                                <div>
                                    <label>USERNAME:</label>
                                    <spring:bind path="username">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <form:input type="text" path="username" class="form-control" autofocus="true"></form:input>
                                            <form:errors path="username"></form:errors>
                                        </div>
                                    </spring:bind>
                                </div>
                                <div>
                                    <label>FIRST NAME:</label>
                                    <spring:bind path="firstname">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <form:input type="text" path="firstname" class="form-control" autofocus="true"></form:input>
                                            <form:errors path="firstname"></form:errors>
                                        </div>
                                    </spring:bind>
                                </div>
                                <div>
                                    <label>LAST NAME:</label>
                                    <spring:bind path="lastname">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <form:input type="text" path="lastname" class="form-control" autofocus="true"></form:input>
                                        </div>
                                    </spring:bind>
                                </div>
                                <div>
                                    <label>EMAIL:</label>
                                    <spring:bind path="email">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <form:input type="text" path="email" class="form-control" autofocus="true"></form:input>
                                            <form:errors path="email"></form:errors>
                                        </div>
                                    </spring:bind>
                                </div>
                                <div>
                                    <label>PASSWORD:</label>
                                    <spring:bind path="password">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <form:input type="password" path="password" class="form-control"></form:input>
                                            <form:errors path="password"></form:errors>
                                        </div>
                                    </spring:bind>
                                </div>                                   
                                <button type="submit" class="btn btn-primary">Sign up</button>
                            </form:form>                            
                        </div><!--log-in-form-->
                        <h4 class="text-center"style="font-size: 14px"><a href="${contextPath}/registration" style="color: #2E2E2E;">Already a Member?</a></h4>                        
                    </div><!--form-body-->                    
                </div><!--col-sm-4-->  
            </div><!--row-->    
            </div><!-- container -->
            <!-- footer -->
        <footer id="footer" class="text-center">
           <div class="container social">

             <div class="col-sm-12">                
               <a href="#" class="icon-circle fb" target="_blank">
                 <img class="logo" alt="Facebook" src="${contextPath}/resources/social/facebook_transparent-1c6a690dcde061958f50f712404c764613cc193d3f69e81734c8c9a7f36131e4.png" />
               </a>

               <a href="#" class="icon-circle tw" target="_blank">
                 <img class="logo" alt="Twitter" src="${contextPath}/resources/social/twitter_transparent-39f60ddc31bce0d3e3d6ae72af7fbe74dc95e01b7d7a6f66cc38efabda3bb6da.png" />
               </a>

                 <a href="#" class="icon-circle pt" target="_blank">
                 <img class="logo" alt="Pinterest" src="${contextPath}/resources/social/pinterest_transparent-a58a9baa98ec9a2e4fed6d4ab7ffdb69bc607c82649a486544b2b5dab03364ef.png" />
                </a>
             </div>

           </div><!--social-->

           <p id="copyright">&copy; 2018 Team RantRoom. All rights reserved | Designed by <a href="http://www.khansaad.com/" target="_blank" >Saad </a>| Mentored by <a href="http://www.roosnam.com/" target="_blank" >Mansoor</a></p>
     	</footer>
        </div>    
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>
