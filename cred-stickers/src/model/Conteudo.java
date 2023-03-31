package model;

public class Conteudo {

    private final String title;
    private final String url;

    public Conteudo(String title, String image) {
        this.title = title;
        this.url = image;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "title='" + this.title + '\'' +
                ", image='" + this.url + '\'' +
                '}';
    }
}
