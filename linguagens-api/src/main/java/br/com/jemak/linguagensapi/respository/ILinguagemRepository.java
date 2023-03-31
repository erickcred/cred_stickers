package br.com.jemak.linguagensapi.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jemak.linguagensapi.model.Linguagem;

public interface ILinguagemRepository extends MongoRepository<Linguagem, String> {

}
