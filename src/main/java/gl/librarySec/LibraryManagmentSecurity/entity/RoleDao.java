package gl.librarySec.LibraryManagmentSecurity.entity;


public class RoleDao{
    String name;

    public RoleDao() {
    }

    public RoleDao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

