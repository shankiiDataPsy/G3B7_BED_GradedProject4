package gl.librarySec.LibraryManagmentSecurity.repo;

import gl.librarySec.LibraryManagmentSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);

    List<User> findAllByFnameContainsAllIgnoreCase(String text);
}
