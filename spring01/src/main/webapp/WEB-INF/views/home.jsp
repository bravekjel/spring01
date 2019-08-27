<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
prefix="sec"%>
<%@ include file="include/header.jsp" %>
<%
String context = request.getContextPath();
%>
<html lang="en">
<h2>${msg}</h2>
<a href="${path}/admin/">관리자 페이지</a><br>
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
<%@ include file="include/menu.jsp" %>
<%@ include file="include/modal.jsp" %>

  </head>

  <body>

    <header class="header">

      <div class="container">

        <h1 class="logo">
        brave  kjel
        </h1>

        <nav class="nav">

          <ul class="list">

            <li class="list-item"><a href="#">board</a></li>

            <li class="list-item"><a href="#">Download</a></li>

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

                  Using Django

                </a>

              </h3>

              <p class="mailing-desc">

                Get help with Django and follow announcements.

              </p>

              <form action="#" class="mailing-form">

                <label for="input-following"

                  >send email for following news</label

                >

                <input

                  type="email"

                  placeholder="Enter email"

                  id="input-following"

                  name="input-following"

                />

                <button type="submit" class="mailing-form-btn">Submit</button>

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