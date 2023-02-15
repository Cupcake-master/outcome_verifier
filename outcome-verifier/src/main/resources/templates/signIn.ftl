<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/css/entrance.css">
    <link rel="icon" type="image/ico" href="https://itvdn.com/Content/img/common/favicon.ico">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script type="text/javascript">
        function funOnLoad() {
            <#if title?? && message?? && category?? >
            swal('${title}', '${message}', '${category}');
            </#if>
            <#--            <#if SPRING_SECURITY_LAST_EXCEPTION??>-->
            <#--                swal('Error', ${SPRING_SECURITY_LAST_EXCEPTION.message}, 'error');-->
            <#--            </#if>-->
        }
        window.onload = funOnLoad;
    </script>
</head>
<body>
<div class="login-dark">
    <form method="post" autocomplete="off" id="form">
        <h2 class="sr-only">Login Form</h2>
        <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
        <div class="form-group"><input style="color:yellow" class="form-control" type="email" name="email" id="email"
                                       placeholder="Email" required="required" max="30"></div>
        <div class="form-group"><input style="color: yellow" class="form-control" type="password" name="password"
                                       id="password" placeholder="Password" required="required"
                                       min="6" max="30">
        </div>
        <div class="form-group">
            <p>
            <div data-theme="dark" style="transform:scale(0.8); transform-origin:0;" class="g-recaptcha"
                 data-sitekey="6LcIde8UAAAAAAIbECHdzJ1GhxNKZDyqAXCP95zy"></div></p>
            <button class="btn btn-primary btn-block" type="submit" onclick="return validateForm()">Log In</button>
        </div>
        <a href="${rc.getContextPath()}/signUp" class="forgot">You aren't registered? Register here&nbsp;<br></a>
        <a onclick="return forgotPassword()" class="forgot">Forgot password&nbsp;<br></a>
    </form>
</div>
<script>
    function validateForm() {
        let password1 = document.getElementById('password');
        let email = document.getElementById('email');
        let error = '';
        let email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
        if (!email_regexp.test(email.value)) {
            error += 'Email is entered incorrectly! \n';
        }
        if (password1.value.length < 6 || password1.value.length > 30) {
            error += 'Password must be between 6 and 30 characters! \n';
        }
        if (error !== '') {
            swal("Oops", error, "error");
            return false;
        } else {
            let form = document.getElementById("form");
            form.submit();
        }
    }
</script>
<script>
    function forgotPassword() {
        swal("Your email:", {
            content: "input",
            buttons: true,
        }).then((email1) => {
            let email = email1;
            let error = '';
            let email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
            if (!email_regexp.test(email)) {
                error += 'Email is entered incorrectly! \n';
            }
            if (error !== '') {
                swal("Oops", error, "error");
                return false;
            }
        });
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>