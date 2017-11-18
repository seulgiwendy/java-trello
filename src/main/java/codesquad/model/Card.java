package codesquad.model;

import javax.persistence.ManyToOne;

public class Card {

    @ManyToOne
    private Board board;

    @ManyToOne
    private User user;

    private String title;
    private String contents;

    public Card(Board board, User user, String title, String contents) {
        this.board = board;
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
