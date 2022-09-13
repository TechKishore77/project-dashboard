package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qa.ade.project_dashboard.exceptions.ConflictException;
import qa.ade.project_dashboard.model.User;
import qa.ade.project_dashboard.service.UserService;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/register")
    public void register(@RequestBody User user) {
        try {
            userService.createUser(user);
        } catch (DuplicateKeyException exception) {
            throw new ConflictException("User with the given username already exists");
        }
    }
}
