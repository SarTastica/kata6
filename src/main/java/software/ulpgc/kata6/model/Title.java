package software.ulpgc.kata6.model;

public class Title {
    private final String id;
    private final String titleType;
    private final String primaryTitle;

    public Title(String id, String titleType, String primaryTitle) {
        this.id = id;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
    }

    public String getId() { return id; }
    public String getTitleType() { return titleType; }
    public String getPrimaryTitle() { return primaryTitle; }

    @Override
    public String toString() {
        return id + " " + primaryTitle;
    }
}

