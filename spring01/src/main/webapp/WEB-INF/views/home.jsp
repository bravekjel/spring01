<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
prefix="sec"%>
<%@ include file="include/header.jsp" %>
<%
String context = request.getContextPath();
%>
<html lang="en">
<h2>${msg}</h2>

<a href="${path}/user/logout.do">로그아웃</a>

  <head>

    <meta charset="UTF-8" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <meta http-equiv="X-UA-Compatible" content="ie=edge" />

    <link rel="stylesheet" href="<%=context%>/resources/normalize.css" />

    <link rel="stylesheet" href="<%=context%>/resources/style.css" />

    <link

      href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900"

      rel="stylesheet"

    />

    <title>The Web framework for perfectionists with deadlines | Django</title>

<%@ include file="include/modal/search_6nums.jsp" %>
<%@ include file="include/modal/6nums.jsp" %>
<script>

function sfind(){
	var invocation = new XMLHttpRequest();
	var formName = document.sfind;
	var str = $(formName).serialize();
	var sname=$("#snames").val();
	var sname1 = escape(sname);
	var myurl = "http://api.seibro.or.kr/openapi/service/StockSvc/getStkIsinByNmN1?secnNm="+sname1+"&numOfRows=2&pageNo=1&ServiceKey=qMWBfGFhp4J1REtArquWOwxObYzh%2FoesRVV7scn4Hf3Xm%2BFEMMen0BMT4MhI%2BBwsrAlz5ik%2Bb87Q0pffXDuEFw%3D%3D";
	var xurl = "http://mannaedu.com";
	
	var request = new XMLHttpRequest();
	/* var req = urllib.request.Request(myurl);
	var res = urllib.request.urlopen(req);
	console.log("res="+res.read()) */
	request.open('GET',myurl);
	request.responseType='json';
	request.send();
	$.get(xurl, function(data){
		console.log('data=='+data);
	},"text/html");
	$.ajax({
		url : '${path}/snames/data.do',
		type : 'get',
		data : sname,
		dataType : 'json',
		success : function(msg){
			console.log(msg.response.body.items.item);
			var myItem = msg.response.body.items.item;
			
		
		}
	});
	$.ajax({
		type:"GET",
		url:myurl,
		 contentType: 'application/xml',
		  dataType:'xml',
		  responseType:'application/xml',
		  headers: {
			    'Access-Control-Allow-Credentials' : true,
			    'Access-Control-Allow-Origin':'*',
			    'Access-Control-Allow-Methods':'GET',
			    'Access-Control-Allow-Headers':'application/xml',
			  },
		success: function(result){
			console.log("success="+result);          
			if($(result).find('items').length > 0){
				$(result).find('items').each(function(){
					console.log('종목번호'+$(this).find('shotnIsin').text());
				});
			}
		},
		error:function(result){
			console.log("error="+result);
		},
		complete:function(result){
			console.log("complete="+result);
		}
		
	});
}	




		
</script>
  </head>

  <body>

    <header class="header">

      <div class="container">

        <h1 class="logo">
        brave  kjel
        </h1>

        <nav class="nav">

          <ul class="list">

            <li class="list-item"><a href="${path}/board/list.do">board</a></li>

            <li class="list-item"><a href="${path}/search_stock/finan2.do">DaechaDownload</a></li>

            <li class="list-item"><a href="#">Documentation</a></li>

            <li class="list-item"><a href="#">News</a></li>

            <li class="list-item"><a href="#">Community</a></li>

            <li class="list-item"><a href="#">Code</a></li>

            <li class="list-item"><a href="#">About</a></li>

            <li class="list-item"><a href="#">β Donate</a></li>

          </ul>

        </nav>

      </div>

    </header>

    <main class="main">

      <div class="hero-section">

        <div class="container">

          <p class="intro-desc">

            Web Project name=finance stock note.

          </p>

          <a data-toggle="modal" href="#search_stock" class="hero-cta cta">

            Searching stock with 6 numbers

          </a>

        </div>

      </div>

      <div class="container-float">

        <div class="main-content">

          <h2 class="main-title">Stay in the loop</h2>

          <p class="main-desc">

            Subscribe to one of our mailing lists to stay up to date with

            everything in the Django community:

          </p>

          <div class="mailing-layout">

            <div class="mailing">

              <h3 class="mailing-title">

                <a href="#">

                  Using DATA.GO.KR

                </a>

              </h3>

              <p class="mailing-desc">
				<span class="img_open">
				
               Information
				</span>

              </p>

              <form action ="${path}/snames/find.do"   class="mailing-form" >
					<%-- name="sfind" method="post"  --%>
                <label for="input-following"

                  >send email for following news</label

                >

                <input

                  type="text"

                  placeholder="Stock name"

                  id="snames"

                  name="snames"
                  />
				
				<!-- data-toggle="modal" data-target="#6nums" -->
                

           <a href="javascript:sfind()"  class="mailing-form-btn" >Find</a>
                    <!-- <button type="submit" class="mailing-form-btn">Find</button>--> 

              </form>

              <p class="mailing-desc">

                You can also subscribe by sending an email to

                <a href="mailto:django-users+subscribe@googlegroups.com"

                  >django-users+subscribe@googlegroups.com</a

                >

                and following the instructions that will be sent to you.

              </p>

            </div>

            <div class="mailing">

              <h3 class="mailing-title">

                <a href="#">Contributing to Django</a>

              </h3>

              <p class="mailing-desc">

                Contribute to the development of Django itself.

              </p>

              <form action="#" class="mailing-form">

                <label for="email-contribute"

                  >send e-mail for contribution</label

                >

                <input

                  type="email"

                  placeholder="Enter email"

                  id="email-contribute"

                  name="email-contribute"

                />

                <button type="submit" class="mailing-form-btn">

                  Submit

                </button>

              </form>

              <p class="mailing-desc">

                Before asking a question about how to contribute, read

                <a

                  href="https://docs.djangoproject.com/en/dev/internals/contributing/"

                  >Contributing to Django</a

                >. Many frequently asked questions are answered there.

              </p>

            </div>

          </div>

        </div>

        <div class="side-content">

          <div class="container-side-cta">

            <a href="#" class="cta side-cta"

              >Download <em>latest release: 2.1.7</em></a

            >

            <a href="#" class="more">Django documentation</a>

          </div>

          <div class="latest-news infos">

            <h2 class="infos-title">Latest news</h2>

            <article class="news-article">

              <h3 class="news-title">

                <a href="#"

                  >Django bugfix releases: 2.1.7, 2.0.12 and 1.11.20</a

                >

              </h3>

              <p class="news-desc">

                Today the Django project issued bugfix releases for the 2.1, 2.0

                and 1.11 release series.

              </p>

              <p class="news-meta">

                Posted by <span>Carlton Gibson</span> on

                <time datetime="2019-02-11">2μ 11, 2019</time>

              </p>

            </article>

            <a href="#" class="more">More news</a>

          </div>

          <div class="get-involved infos">

            <h2 class="infos-title">Get involved</h2>

            <dl class="list-communities">

              <dt class="community-title">

                <a href="#">#django IRC channel</a>

              </dt>

              <dd class="community-desc">Chat with other Django users</dd>

              <dt class="community-title"><a href="#">Ticket system</a></dt>

              <dd class="community-desc">

                Report bugs and make feature requests

              </dd>

            </dl>

            <a href="#" class="more">Inside the django community</a>

          </div>

        </div>

      </div>

    </main>

    <footer class="footer">νΈν°</footer>




  </body>

</html>