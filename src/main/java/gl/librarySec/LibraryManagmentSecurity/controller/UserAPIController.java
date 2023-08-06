package gl.librarySec.LibraryManagmentSecurity.controller;

import gl.librarySec.LibraryManagmentSecurity.entity.User;
import gl.librarySec.LibraryManagmentSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class UserAPIController {

    @Autowired
    UserService userService;


    @RequestMapping({"", "/"})
    public List<User> getAllEmployees(Model model){
        return userService.getAllUsers();
    }

    @GetMapping({ "/sort" })
    public List<User> getAllEmployeesOrdered(Model model, @RequestParam("order") String order){
//        System.out.println(order);
        return userService.getAllUsersOrdered(order);
    }

    @GetMapping("/get/{id}")
    public User getEmployee(Model model, @PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/put/{id}")
    public User putEmployee(Model model, @PathVariable("id") Long id, @RequestBody User user){
        User newUser = userService.getUserById(id);
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setEmail(user.getEmail());

        return userService.putUserById(id, newUser);
    }

    @PostMapping("/add/")
    public User addEmployee(Model model, @RequestBody User user){
        System.out.println(user);
        return userService.addUser(user);
    }

    @GetMapping("/del/{id}")
    public String deleteEmployee(Model model, @PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "Deleted employee id - " + id;
    }

    @GetMapping("/search/{searchText}")
    public List<User> deleteEmployee(Model model, @PathVariable("searchText") String searchText){
        return userService.searchByText(searchText);
    }
}
