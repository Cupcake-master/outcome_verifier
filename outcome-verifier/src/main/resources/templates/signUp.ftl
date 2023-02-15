<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/css/registration.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="icon" type="image/ico" href="https://itvdn.com/Content/img/common/favicon.ico">
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script type="text/javascript">
        function funOnLoad() {
            <#if title?? && message?? && category?? >
            swal('${title}', '${message}', '${category}');
            </#if>
        }
        window.onload = funOnLoad;
    </script>
</head>
<body>
<div class="login-dark">
    <form method="post" autocomplete="off" id="form">
        <h2 class="sr-only">Login Form</h2>
        <div class="illustration"><i class="icon ion-ios-locked-outline"></i><input class="form-control" type="text"
                                                                                    max="30" style="color:pink"
                                                                                    placeholder="Nickname"
                                                                                    name="nickname" id="nickname"
                                                                                    required="required"></div>
        <div class="form-group"><input class="form-control" type="text" name="email" id="email" placeholder="Email"
                                       required="required" max="30" style="color:yellow"></div>
        <div class="form-group"><input class="form-control" type="password" name="password" id="password"
                                       placeholder="Password" min="6" max="30" required="required" style="color:yellow">
        </div>
        <input class="form-control" type="password" id="rePassword" style="color:yellow" name="rePassword" placeholder="Re-password"
               required="required" min="6" max="30">
        <div class="form-group">
            <p>
            <div data-theme="dark" style="transform:scale(0.8); transform-origin:0;" class="g-recaptcha"
                 data-sitekey="6LcIde8UAAAAAAIbECHdzJ1GhxNKZDyqAXCP95zy"></div></p>
            <button class="btn btn-primary btn-block" type="submit" onclick="return validateForm()">Sign up <br>
            </button>
        </div>
        <a href="/signIn" class="forgot">Are you already registered? Login
            here</a></form>
</div>
<script>
    function validateForm() {
        let nickname = document.getElementById('nickname');
        let email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
        let email = document.getElementById('email');
        let password1 = document.getElementById('password');
        let password2 = document.getElementById('rePassword');
        let error = '';
        if (nickname.value.length < 6 || nickname.value.length > 30 ){
            error+='Nickname must be between 6 and 30 characters! \n';
        }
        if (!email_regexp.test(email.value)) {
            error+='Email is entered incorrectly! \n';
        }
        if (password1.value.length < 6 || password1.value.length > 30){
            error+='Password must be between 6 and 30 characters! \n';
        }
        if (password1.value !== password2.value) {
            error+='Passwords do not match! \n';
        }
        if (error !== '') {
            swal("Oops", error, "error");
            return false;
        }else {
            let form = document.getElementById("form");
            form.submit();
        }
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>