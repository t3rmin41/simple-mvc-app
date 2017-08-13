package simple.mvc.jpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLES") //, uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "ROLE"}))
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;

    @Column(name = "ROLE")
    private String role;
    
    @Column(name = "ACTIVE")
    private Long active = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        if (   this.getRole().equals(role.getRole())
            && this.getUser().getId().equals(role.getUser().getId())
            && this.getActive().equals(role.getActive())
            ) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.getRole(), this.getUser().getId(), this.getActive());
    }
}
