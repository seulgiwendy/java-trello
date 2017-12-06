package codesquad.dto;

public class CardDto {


    private String deckName;
    private String title;
    private String contents;
    private long index;

    public CardDto (String deckName, String title, String contents, long index) {
        this.deckName = deckName;
        this.title = title;
        this.contents = contents;
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getDeckName() {
        return deckName;
    }

    public long getIndex() {
        return index;
    }
}
