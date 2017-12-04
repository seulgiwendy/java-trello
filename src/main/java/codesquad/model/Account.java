package codesquad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "seq", sequenceName = "account_no_seq", allocationSize = 1)
    @JsonIgnore
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    private List<Role> role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private List<Deck> decks;

    private String userId;
    private String password;
    private String name;

    public Account() {

    }

    public Account(String userId, String password, String name) {
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

    public boolean isLoginable(Account account) {
        return account.equals(this);
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(String role) {
        if (this.role == null) {
            this.role = Lists.newArrayList();
        }
        this.role.add(new Role(role));
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public void addRole(Role role) {
        if (this.role == null) {
            this.role = Lists.newArrayList();
        }

        this.role.add(role);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    public Deck addDeck(Deck deck){
        if (this.decks == null) {
            this.decks = Lists.newArrayList();
        }

        if (!(this.decks.add(deck))) {
            return null;
        }
        return deck;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account) {
            Account compare = (Account)o;
            return this.userId.equals(compare.getUserId()) && this.password.equals(compare.getPassword());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
