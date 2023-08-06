package gl.librarySec.LibraryManagmentSecurity.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gl.librarySec.LibraryManagmentSecurity.entity.Role;
import gl.librarySec.LibraryManagmentSecurity.entity.RoleDao;
import gl.librarySec.LibraryManagmentSecurity.entity.User;
import gl.librarySec.LibraryManagmentSecurity.entity.UserDao;
import gl.librarySec.LibraryManagmentSecurity.repo.RoleRepo;
import gl.librarySec.LibraryManagmentSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class UserAPIController {

    @Autowired
    UserService userService;
    @Autowired
    private RoleRepo roleRepo;


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

//    {username=temp, password=12345, roles=[{id=2, name=USER}]}

    @PostMapping("/add/")
    public User addEmployee(Model model, @RequestBody UserDao user){
        System.out.println(user);
        return userService.addUser(new User(user.getUsername(),user.getPassword(),"","","","","", List.of(roleRepo.findAll().get(1))));
    }

    @PostMapping("/add/role")
    public Role addEmployeeRole(Model model, @RequestBody RoleDao role){
        System.out.println(role);
        return roleRepo.save(new Role(role.getName()));
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
