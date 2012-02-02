package org.fullchaine.dhhr.android.model;

/* {"date":1328059089445,"title":"stats","content":"stats fait","author":"dh","archived":false,"draft":false,"id":14253} */

public class News {
    private final long date;
    private final String title;
    private final String content;
    private final String author;
    private final boolean archived;
    private final boolean draft;
    private final long id;

    public News(long id, boolean draft, boolean archived, String author, String content, String title, long date) {
        this.id = id;
        this.draft = draft;
        this.archived = archived;
        this.author = author;
        this.content = content;
        this.title = title;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isDraft() {
        return draft;
    }

    public long getId() {
        return id;
    }

}
