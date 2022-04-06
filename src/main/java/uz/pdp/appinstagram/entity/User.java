package uz.pdp.appinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appinstagram.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsNameEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private String website;

    private String bio;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    //@Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private boolean isLocked = false;

    @OneToOne
    private Attachment photoProfile;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Follower> followers;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Following> followings;

    private String code;
    private boolean enabled = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }

}
