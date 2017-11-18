package codesquad.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Board {

    @ManyToOne
    private User owner;
    private String title;

    public Board (User owner, String title) {
        this.owner = owner;
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
