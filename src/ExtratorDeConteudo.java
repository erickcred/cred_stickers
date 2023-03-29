import java.util.List;

import model.Conteudo;
import model.RConteudo;

public interface ExtratorDeConteudo {

    public List<RConteudo> extraiConteudos(String json);

}
