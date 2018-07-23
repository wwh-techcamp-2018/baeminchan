package codesquad.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[0-9a-zA-Z-]+)*$")
    @Column(length=40, unique=true, nullable=false, updatable=false)
    private String email;

    @NotBlank
    @Pattern(regexp="[0-9a-zA-Z]*$")
    @Size(min=8, max=16)
    @Column(length=16, nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp="[0-9a-zA-Z]*$")
    @Size(min=8, max=16)
    @Transient
    private String passwordConfirm;

    @NotBlank
    @Size(min=1, max=10)
    @Column(length=10, nullable = false)
    private String name;

    @NotBlank
    @Size(min=4, max=14)
    @Column(length=14, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserPermissions permissions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
