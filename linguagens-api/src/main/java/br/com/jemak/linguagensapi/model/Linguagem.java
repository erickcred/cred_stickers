package br.com.jemak.linguagensapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principaisLinguagens")
public class Linguagem {

    @Id
    private String id;
    private String title;
    private String image;
    private int ranking;

    public Linguagem() {
    }

    public Linguagem(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Linguagem(String title, String image, int ranking) {
        this(title, image);
        this.ranking = ranking;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

}
