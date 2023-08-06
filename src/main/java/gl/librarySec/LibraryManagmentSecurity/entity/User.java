package gl.librarySec.LibraryManagmentSecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@NoArgsConstructor
@Table(name = "user_table")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    private String fname;
    private String lname;
    @JsonIgnore
    private String course;
    @JsonIgnore
    private String country;
    private String email;



    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private List<Role> roles = new ArrayList<Role>();

    public User(String username, String password, String fname, String lname, String course, String country, String email, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.course = course;
        this.country = country;
        this.email = email;
        this.roles = roles;
    }
}
