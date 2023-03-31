package br.com.jemak.linguagensapi.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.jemak.linguagensapi.model.Linguagem;
import br.com.jemak.linguagensapi.respository.ILinguagemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class LinguagemController {

    @Autowired
    private ILinguagemRepository repository;

    @GetMapping(value = "/")
    public String getLinguagemPreferida() {
        return "Oi, Java!";
    }

    @GetMapping("/linguagens")
    public List<Linguagem> pegaTodasLinguagens() {
        List<Linguagem> linguagens = this.repository.findAll();
        return linguagens;
    }

    @GetMapping("/linguagem/{id}")
    public ResponseEntity<Linguagem> pegaLinguagemPorId(@PathVariable String id) {
        Linguagem linguagem = null;
        if (this.repository.existsById(id)) {
            linguagem = this.repository.findById(id).orElseThrow();

            return ResponseEntity.status(HttpStatus.OK).body(linguagem);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(linguagem);
    }

    @PostMapping("/linguagem")
    public ResponseEntity<Linguagem> criaLinguagem(@RequestBody Linguagem linguagem) { // Normalmente não retorna
        repository.save(linguagem);

        return ResponseEntity.status(HttpStatus.CREATED).body(linguagem);
    }

    @PutMapping("/linguagem/{id}")
    public ResponseEntity<Linguagem> editaLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        if (!this.repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(linguagem);
        }
        Linguagem editar = repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Linguagem não encontrada!"));

        editar.setTitle(linguagem.getTitle());
        editar.setImage(linguagem.getImage());

        repository.save(editar);

        return null;
    }

    @DeleteMapping("/linguagem/{id}")
    public void deletaLinguagem(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

}
