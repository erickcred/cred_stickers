import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradorFigurinhas {

    public void criar(String newName, InputStream inputStream, String pathOut) throws IOException {
        String newNameImage = newName.replace(" ", "_").replace(":", "_");

        // Leitura da imagem
        // InputStream inputStream = new FileInputStream(new File(pathIn));
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Criar nova imagem em memório com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + (largura / 4);
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // Cipiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a font
        int fontSize = (largura / 7);
        graphics.setColor(Color.PINK);
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));

        // Escrever uma frase na nova imagem
        graphics.drawString("TopZeira", ((largura / 4) - (fontSize / 4)), (novaAltura - 30));

        // Escrever a nova imagem em um arquivo
        if (pathOut == "" || pathOut == null) {
            pathOut = "./src/saida";
        }
        if (!new File(pathOut).exists()) {
            new File(pathOut).mkdir();
        }
        ImageIO.write(novaImagem, "png", new File(pathOut + "/" + newNameImage + ".png"));

    }

    // public static void main(String[] args) throws IOException {
    // GeradorFigurinhas figurinhas = new GeradorFigurinhas();
    // figurinhas.criar("Novo_Nome",
    // new FileInputStream("./src/entrada/filme.jpg"), "");
    // }

}
