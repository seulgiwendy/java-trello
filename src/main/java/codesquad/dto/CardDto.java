package codesquad.dto;

public class CardDto {

    private String title;
    private String contents;
    private long index;

    public CardDto (String title, String contents, long index) {
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

    public long getIndex() {
        return index;
    }
}
