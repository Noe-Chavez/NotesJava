package com.dissekcorportion.notesjava.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_note") // cambiar en DB nombre de propiedad.
    private int id;

    private String title;

    private String content;

    private boolean favorite;

    private int color;

    public Note(String title, String content, boolean favorite, int color) {
        this.title = title;
        this.content = content;
        this.favorite = favorite;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
