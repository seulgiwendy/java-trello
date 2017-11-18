package codesquad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userId;
    private String password;
    private String name;

    public User() {

    }

    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean isLoginable(User user) {
        return user.equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User compare = (User)o;
            return this.userId.equals(compare.getUserId()) && this.password.equals(compare.getPassword());
        }
        return false;
    }
}
