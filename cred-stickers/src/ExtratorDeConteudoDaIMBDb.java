import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.RConteudo;
import utils.JsonParser;

public class ExtratorDeConteudoDaIMBDb implements ExtratorDeConteudo {

    public List<RConteudo> extraiConteudos(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<RConteudo> conteudos = new ArrayList<>();
        for (Map<String, String> atributos : listaDeAtributos) {
            conteudos.add(new RConteudo(atributos.get("title"), atributos.get("image")));
        }

        return conteudos;
    }

}
