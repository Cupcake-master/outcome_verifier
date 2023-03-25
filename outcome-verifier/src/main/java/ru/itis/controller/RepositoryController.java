package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.model.Repository;
import ru.itis.service.RepositoryServiceImpl;
import ru.itis.service.SquadServiceImpl;
import ru.itis.service.UserServiceImpl;

import java.util.Date;

@Controller
public class RepositoryController {

    private final RepositoryServiceImpl repositoryService;
    private final UserServiceImpl userService;

    private final SquadServiceImpl squadService;

    @Autowired
    public RepositoryController(RepositoryServiceImpl repositoryService, UserServiceImpl userService, SquadServiceImpl squadService) {
        this.repositoryService = repositoryService;
        this.userService = userService;
        this.squadService = squadService;
    }

    @GetMapping("/index")
    public String cloneRepository(Authentication authentication) {
        try {
            Repository repository = Repository.builder()
                    .name("Bulat")
                    .surname("Bilalov")
                    .patronymic("Foatovich")
                    .created(new Date())
                    .git_path("https://github.com/Cupcake-master/glowing-eureka.git")
                    .squad_id(squadService.findByName("11-906").get())
                    .user_id(userService.findUserByAuthentication(authentication))
                    .build();
            repositoryService.handler(repository);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "index";
    }
}
