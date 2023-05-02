<#macro main name nameCSS>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${name}</title>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cookie">
        <link rel="stylesheet" href="${rc.getContextPath()}/css/${nameCSS}">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="icon" type="image/ico" href="https://itvdn.com/Content/img/common/favicon.ico">
        <script type="text/javascript">
            function funOnLoad() {
                <#if title?? && message?? && category?? >
                swal('${title}', '${message}', '${category}');
                </#if>
            }

            window.onload = funOnLoad;
        </script>

    </head>


    </html>

    <body>
    <nav class="navbar navbar-light navbar-expand-md custom-header">
        <div class="container-fluid"><a class="navbar-brand" href="#">Outcome<span>Verifier</span> </a>
            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse"><span class="sr-only">Toggle navigation</span><span
                        class="navbar-toggler-icon"></span></button>
            <div
                    class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav links">
                    <li class="nav-item" role="presentation"><a class="nav-link"
                                                                href="${rc.getContextPath()}/home">Home</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link"
                                                                href="${rc.getContextPath()}/repository">Репозиторий</a>
                    </li>
                    <li class="nav-item" role="presentation"><a class="nav-link"
                                                                href="${rc.getContextPath()}/results">Результаты</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link"
                                                                href="${rc.getContextPath()}/developers">Разработчики</a>
                    </li>
                    <li class="nav-item" role="presentation"><a class="nav-link custom-navbar"
                                                                href="${rc.getContextPath()}/feedback">Contact</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link custom-navbar"
                                                                href="${rc.getContextPath()}/aboutUs">About us</a></li>
                </ul>
                <ul class="nav navbar-nav ml-auto">
                    <li class="dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown"
                                            aria-expanded="false"
                                            href="#"> <img src="${rc.getContextPath()}/img/avatar.jpg"
                                                           class="dropdown-image">
                            <#if nickname??>
                                ${nickname}
                            </#if>&nbsp;
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" role="menu"><a class="dropdown-item"
                                                                                      role="presentation"
                                                                                      href="${rc.getContextPath()}/changePassword">Settings </a>
                            <a class="dropdown-item"
                               role="presentation"
                               href="${rc.getContextPath()}/logout">Logout </a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <@content/>
    </body>
</#macro>