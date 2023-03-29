import utils.JsonParser;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    private static final String ANSI_BG_PURPLE = "\u001B[45m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI basePath = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(basePath).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String data = response.body();
        // System.out.println(data);

        // Extrair só os dados que interessam (title, image, imDbRating ->
        // classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(data);
        GeradorFigurinhas figurinha = new GeradorFigurinhas();

        // Exibir manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Title: " + filme.get("title"));
            System.out.println("Image: " + filme.get("image"));
            System.out.print(ANSI_BG_PURPLE + ANSI_BLACK);
            System.out.print("Classificação: " + filme.get("imDbRating"));
            System.out.println(ANSI_RESET);

            int star = Integer.parseInt(filme.get("imDbRating").substring(0, 1));
            for (int i = 0; i < star; i++) {
                System.out.print(ANSI_YELLOW + "\uD83C\uDF1F " + ANSI_RESET);
            }
            System.out.println("\n");

            try {
                figurinha.criar(filme.get("title"), new URL(filme.get("image")).openStream(),
                        "");
            } catch (Exception e) {
                System.out.println("erro: " + e.getMessage());
            }
        }

    }
}
