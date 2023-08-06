package gl.librarySec.LibraryManagmentSecurity.service;
import gl.librarySec.LibraryManagmentSecurity.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getAllUsersOrdered(String order);

    List<User> searchByText(String searchText);
    User addUser(User user);
    User getUserById(long id);
    User putUserById(long id, User user);
    void deleteUserById(long id);



}

