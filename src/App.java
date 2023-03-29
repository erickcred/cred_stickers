import utils.JsonParser;

import java.net.URL;
import java.util.List;
import java.util.Map;

import model.RConteudo;

public class App {

    private static final String ANSI_BG_PURPLE = "\u001B[45m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudoDaIMBDb extrator = new ExtratorDeConteudoDaIMBDb();
        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-01&end_date=2022-06-14";
        // ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        ClienteHttp clienteHttp = new ClienteHttp();
        String json = clienteHttp.buscaDados(url);

        // Extrair só os dados que interessam (title, image, imDbRating ->
        // classificação)
        GeradorFigurinhas figurinha = new GeradorFigurinhas();

        // Exibir e manipular os dados

        for (RConteudo conteudo : extrator.extraiConteudos(json)) {
            try {
                // figurinha.criar(conteudo.getTitle(), new URL(conteudo.getUrl()).openStream(),
                // "./src/saida_nasa");

                figurinha.criar(conteudo.title(), new URL(conteudo.url()).openStream(),
                        "./src/saida_imdb");
            } catch (Exception e) {
                System.out.println("erro: " + e.getMessage());
            }
        }

    }
}
