package io.github.darioteles.localizacao;

import io.github.darioteles.localizacao.domain.entity.Cidade;
import io.github.darioteles.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Todas as cidades:");
		cidadeService.listarCidades();
		System.out.println("Cidades com nome 'Salvador':");
		cidadeService.listarCidadePorNome("Salvador");
		System.out.println("Cidades Like 'sal%':");
		cidadeService.listarCidadePorNomeLike("sal%");
		System.out.println("Cidade(s) começando com 'S':");
		cidadeService.listarCidadePorNomeInicio("S");
		System.out.println("Cidade(s) terminando com 'te':");
		cidadeService.listarCidadePorNomeFim("te");
		System.out.println("Cidade(s) contendo le:");
		cidadeService.listarCidadeContendo("le");
		System.out.println("Cidade(s) com 1212396372 habitantes:");
		cidadeService.listarCidadePorHabitantes(1212396372L);
		System.out.println("Cidade(s) com menos de 800001:");
		cidadeService.listarCidadePorHabitantesMenorQue(800001L);
		System.out.println("Cidade(s) com mais de 800001:");
		cidadeService.listarCidadePorHabitantesMaiorQue(800001L);
		System.out.println("Cidade(s) com mais de 800001 e começando com 'S':");
		cidadeService.listarCidadePorHabitantesMaiorQueNomeInicio(800001L, "S");
		System.out.println("Cidades Like 'S%' ordenando por habitantes:");
		cidadeService.listarCidadePorNomeLike("S%", Sort.by("habitantes"));
		System.out.println("Cidades Like '%' paginando por 2:");
		cidadeService.listarCidadePorNomeLike("%", PageRequest.of(1, 2));

		System.out.println("Exemplo Filtro Dinâmico utilizando Query Example:");
		var cidade = new Cidade(null, "sa", null);
		cidadeService.filtroDinamico(cidade).forEach(System.out::println);

		System.out.println("Exemplo listagem utilizando Specifications:");
		cidadeService.listarCidadesByNomeSpecs();

		System.out.println("Exemplo Filtro Dinâmico utilizando Specifications:");
		cidade = new Cidade(1L, "São Paulo", 200L);
		cidadeService.listarCidadesSpecsFiltroDinamico(cidade);

		System.out.println("Exemplo listagem utilizando SQL nativo:");
		cidadeService.listarCidadePorNomeSqlNativo("Salvador");
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
