package io.github.darioteles.localizacao.service;

import io.github.darioteles.localizacao.domain.entity.Cidade;
import io.github.darioteles.localizacao.domain.repository.CidadeRepository;
import io.github.darioteles.localizacao.domain.repository.specs.CidadeSpecs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    void salvarCliente(){
        var cidade = new Cidade(1L, "São Paulo", 1212396372L);
        cidadeRepository.save(cidade);
    }

    //Listagens Query Methods utilizando String
    public void listarCidades(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    public void listarCidadePorNome(String nome){
        cidadeRepository.findByNome(nome).forEach(System.out::println);
    }

    public void listarCidadePorNomeInicio(String nome){
        cidadeRepository.findByNomeStartingWith(nome).forEach(System.out::println);
    }

    public void listarCidadePorNomeFim(String nome){
        cidadeRepository.findByNomeEndingWith(nome).forEach(System.out::println);
    }

    public void listarCidadeContendo(String nome){
        cidadeRepository.findByNomeContaining(nome).forEach(System.out::println);
    }


    //Listagem utilizando Like e @Query
    public void listarCidadePorNomeLike(String nome){
        cidadeRepository.findByNomeLike(nome).forEach(System.out::println);
    }


    //Listagens Query Methods utilizando valores numéricos
    public void listarCidadePorHabitantes(Long habitantes){
        cidadeRepository.findByHabitantes(habitantes).forEach(System.out::println);
    }

    public void listarCidadePorHabitantesMenorQue(Long habitantes){
        cidadeRepository.findByHabitantesLessThan(habitantes).forEach(System.out::println);
    }

    public void listarCidadePorHabitantesMaiorQue(Long habitantes){
        cidadeRepository.findByHabitantesGreaterThanEqual(habitantes).forEach(System.out::println);
    }


    //Listagem utlizando QueryMethods "composto"
    public void listarCidadePorHabitantesMaiorQueNomeInicio(Long habitantes, String nome){
        cidadeRepository.findByHabitantesGreaterThanEqualAndNomeStartingWith(habitantes, nome).forEach(System.out::println);
    }


    //Listagens utilizando Ordenação e Paginação
    public void listarCidadePorNomeLike(String nome, Sort sort){
        cidadeRepository.findByNomeLike(nome, sort).forEach(System.out::println);
    }

    public void listarCidadePorNomeLike(String nome, Pageable pageable){
        cidadeRepository.findByNomeLike(nome, pageable).forEach(System.out::println);
    }


    //Listagem utilizando Queries by Examples
    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepository.findAll(example);
    }


    //Listagens utilizando Specifications
    public void listarCidadesByNomeSpecs(){
        Specification<Cidade> spec = CidadeSpecs.nomeEqual("São Paulo").and(CidadeSpecs.habitantesGreaterThan(100000L));
        cidadeRepository.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where(((root, query, cb) -> cb.conjunction()));
        if(filtro.getId() != null){
            specs = specs.and(CidadeSpecs.idEqual(filtro.getId()));
        }
        if(StringUtils.hasText(filtro.getNome())){
            specs = specs.and(CidadeSpecs.nomeLike(filtro.getNome()));
        }
        if(filtro.getHabitantes() != null){
            specs = specs.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
        }
        cidadeRepository.findAll(specs).forEach(System.out::println);
    }


    //Listagem utilizando SQL Nativo
    public void listarCidadePorNomeSqlNativo(String nome){
        cidadeRepository.findByNomeSqlNativo(nome)
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

}
