<#include "main-template.ftl"/>

<#macro content>
    <section class="home-team">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col col-md-8">
                    <div class="sectionTitle text-center">
                        <h2>Our Professionals </h2>
                        <p>People without whom you would not see this site. Thanks to them.</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-3">
                    <div class="card card-style2 team-card">
                        <div class="card_img"><img class="img-fluid img-full loaded"
                                                   src="${rc.getContextPath()}/img/team1.jpg" alt="Team one"
                                                   data-original="templates.img/team1.jpg" data-was-processed="true">
                            <div class="hover-overlay effect-scale"><a href="#" class="overlay_icon"><i
                                            class="fa fa-facebook"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-twitter"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-linkedin"></i></a></div>
                        </div>
                        <div class="card-block">
                            <h4 class="card-title">Bulat Bilalov</h4><span>Team leader</span></div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card card-style2 team-card">
                        <div class="card_img"><img class="img-fluid img-full loaded"
                                                   src="${rc.getContextPath()}/img/team2.jpg" alt="Team one"
                                                   data-original="templates.img/team1.jpg" data-was-processed="true">
                            <div class="hover-overlay effect-scale"><a href="#" class="overlay_icon"><i
                                            class="fa fa-facebook"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-twitter"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-linkedin"></i></a></div>
                        </div>
                        <div class="card-block">
                            <h4 class="card-title">Sky</h4><span>Financial support</span></div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card card-style2 team-card">
                        <div class="card_img"><img class="img-fluid img-full loaded"
                                                   src="${rc.getContextPath()}/img/team3.jpg" alt="Team one"
                                                   data-original="templates.img/team1.jpg" data-was-processed="true">
                            <div class="hover-overlay effect-scale"><a href="#" class="overlay_icon"><i
                                            class="fa fa-facebook"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-twitter"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-linkedin"></i></a></div>
                        </div>
                        <div class="card-block">
                            <h4 class="card-title">Buddy pie<br></h4><span>Protective support</span></div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card card-style2 team-card">
                        <div class="card_img"><img class="img-fluid img-full loaded"
                                                   src="${rc.getContextPath()}/img/team4.jpg" alt="Team one"
                                                   data-original="/templates.img/team1.jpg" data-was-processed="true">
                            <div class="hover-overlay effect-scale"><a href="#" class="overlay_icon"><i
                                            class="fa fa-facebook"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-twitter"></i></a><a href="#" class="overlay_icon"><i
                                            class="fa fa-linkedin"></i></a></div>
                        </div>
                        <div class="card-block">
                            <h4 class="card-title">Mr. Carrot</h4><span>Food Support</span></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="footer-dark">
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-3 item">
                        <h3>Services</h3>
                        <ul>
                            <li><a href="#">Web design</a></li>
                            <li><a href="#">Development</a></li>
                            <li><a href="#">Hosting</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-6 col-md-3 item">
                        <h3>About</h3>
                        <ul>
                            <li><a href="#">Company</a></li>
                            <li><a href="#">Team</a></li>
                            <li><a href="#">Careers</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 item text">
                        <h3>Description</h3>
                        <p>Our team is the best in its field. We do everything to make you happy. We are very glad that
                            you
                            are using our site.</p>
                    </div>
                    <div class="col item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i
                                    class="icon ion-social-twitter"></i></a><a href="#"><i
                                    class="icon ion-social-snapchat"></i></a><a
                                href="#"><i class="icon ion-social-instagram"></i></a></div>
                </div>
                <p class="copyright">OutcomeVerifier © 2023</p>
            </div>
        </footer>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</#macro>

<@main name="Developers &mdash; Website by BulConferences" nameCSS="developers.css"/>


