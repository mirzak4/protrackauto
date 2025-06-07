package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private Instant birthDate;
    private boolean active;
    private String lastVerificationCode;
    private Long roleId;
    private Role role;

    // NotMapped
    private final String tmpPassword = "NBP04.tmp_pw";

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String password, Role role, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public User(Long id, String firstName, String lastName, String email, String password, String username, String phoneNumber, Instant birthDate, Long roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.roleId = roleId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role != null ? List.of(new SimpleGrantedAuthority(role.getName())) : List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public boolean isActive() {
        return active;
    }

    public String isActiveToBitString() {
        return active ? "1" : "0";
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastVerificationCode() {
        return lastVerificationCode;
    }

    public void setLastVerificationCode(String lastVerificationCode) {
        this.lastVerificationCode = lastVerificationCode;
    }
}
