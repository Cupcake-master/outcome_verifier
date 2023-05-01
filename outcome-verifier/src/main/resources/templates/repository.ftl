<#include "main-template.ftl"/>

<#macro content>
<head>
    <title>Academics &mdash; Website by OutcomeVerifier</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="${rc.getContextPath()}/fonts/icomoon/style.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/css/jquery-ui.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/css/owl.theme.default.min.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/css/jquery.fancybox.min.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/css/bootstrap-datepicker.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/fonts/flaticon/font/flaticon.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/css/aos.css">
    <link href="${rc.getContextPath()}/css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet"
          type="text/css">

    <link rel="stylesheet" href="${rc.getContextPath()}/css/style.css">


</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

<div class="site-wrap">

    <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
            <div class="site-mobile-menu-close mt-3">
                <span class="icon-close2 js-menu-toggle"></span>
            </div>
        </div>
        <div class="site-mobile-menu-body"></div>
    </div>


    <div class="py-2 bg-light">
    </div>

    <div class="site-section ftco-subscribe-1 site-blocks-cover pb-4"
         style="background-image: url('./images/bg_1.jpg')">
        <div class="container">
            <div class="row align-items-end">
                <div class="col-lg-7">
                    <h2 class="mb-0">Репозиторий</h2>
                    <p>Получение образовательных результатов студента по git репозиторию</p>
                </div>
            </div>
        </div>
    </div>


    <div class="custom-breadcrumns border-bottom">
        <div class="container">
            <a href="${rc.getContextPath()}/home">Home</a>
            <span class="mx-3 icon-keyboard_arrow_right"></span>
            <span class="current">Repository</span>
        </div>
    </div>

    <form id="form3" autocomplete="off" method="post">
        <div class="site-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label for="firstName">Имя</label>
                        <input type="text" id="name" name="name" class="form-control form-control-lg">
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="lastName">Фамилия</label>
                        <input type="text" id="surname" name="surname" class="form-control form-control-lg">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label for="patronymic">Отчество</label>
                        <input type="text" id="patronymic" name="patronymic" class="form-control form-control-lg">
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="git_path">Путь до репозитория</label>
                        <input type="text" id="git_path" name="git_path" class="form-control form-control-lg">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 form-group">
                        <label for="comment">Комментарий</label>
                        <textarea name="comment" id="comment" cols="30" rows="10" class="form-control"></textarea>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <input type="submit" onclick="return validateForm3()" value="Получить результаты"
                               class="btn btn-primary btn-lg px-5">
                    </div>
                </div>
            </div>
        </div>
    </form>


    <script>
        function validateForm3() {
            let name1 = document.getElementById('name');
            let name2 = document.getElementById('surname');
            let message = document.getElementById('git_path');
            let error = '';
            if (name1.value.trim() === '') {
                error += 'Имя не должно быть пустым! \n';
            }
            if (name2.value.trim() === '') {
                error += 'Фамилия не должна быть пустая! \n';
            }
            if (message.value.length < 20) {
                error += 'Формат пути к git репозиторию неверен! \n';
            }
            if (error !== '') {
                swal("Oops", error, "error");
                return false;
            } else {
                let form = document.getElementById("form3");
                form.submit();
            }
        }
    </script>

    <div class="section-bg style-1" style="background-image: url('./images/hero_1.jpg');">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-5 mb-lg-0">
                    <h3>Our Philosphy</h3>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reiciendis recusandae, iure repellat
                        quis delectus ea? Dolore, amet reprehenderit.</p>
                </div>
                <div class="col-lg-4 col-md-6 mb-5 mb-lg-0">
                    <h3>Academics Principle</h3>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reiciendis recusandae, iure repellat
                        quis delectus ea?
                        Dolore, amet reprehenderit.</p>
                </div>
                <div class="col-lg-4 col-md-6 mb-5 mb-lg-0">
                    <h3>Key of Success</h3>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reiciendis recusandae, iure repellat
                        quis delectus ea?
                        Dolore, amet reprehenderit.</p>
                </div>
            </div>
        </div>
    </div>

</div>

<div id="loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#51be78"/>
    </svg>
</div>

<script src="${rc.getContextPath()}/js/jquery-3.3.1.min.js"></script>
<script src="${rc.getContextPath()}/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${rc.getContextPath()}/js/jquery-ui.js"></script>
<script src="${rc.getContextPath()}/js/popper.min.js"></script>
<script src="${rc.getContextPath()}/js/bootstrap.min.js"></script>
<script src="${rc.getContextPath()}/js/owl.carousel.min.js"></script>
<script src="${rc.getContextPath()}/js/jquery.stellar.min.js"></script>
<script src="${rc.getContextPath()}/js/jquery.countdown.min.js"></script>
<script src="${rc.getContextPath()}/js/bootstrap-datepicker.min.js"></script>
<script src="${rc.getContextPath()}/js/jquery.easing.1.3.js"></script>
<script src="${rc.getContextPath()}/js/aos.js"></script>
<script src="${rc.getContextPath()}/js/jquery.fancybox.min.js"></script>
<script src="${rc.getContextPath()}/js/jquery.sticky.js"></script>
<script src="${rc.getContextPath()}/js/jquery.mb.YTPlayer.min.js"></script>


<script src="${rc.getContextPath()}/js/main.js"></script>

</#macro>

<@main name="Repository &mdash; Website by OutcomeVerifier" nameCSS="developers.css"/>
