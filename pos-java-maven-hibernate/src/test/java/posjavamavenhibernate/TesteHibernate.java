package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	
	
	@Test 
	public void testeHibernetUtil() {
		
		
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setEmail("testando");
		pessoa.setIdade(1);
		pessoa.setLogin("123");
		pessoa.setNome("Marcelo ");
		pessoa.setSobrenome("Freitas");
		pessoa.setSenha("123");
		
		daoGenereuc.salvar(pessoa);
		
		
	}
	
	
	@Test
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(3L);
		pessoa = daoGenereuc.pesquisar(pessoa);
		System.out.println(pessoa);
	}
	
	
	
	@Test
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGenereuc.pesquisarPorId(3L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}

	
	
	
	@Test
	public void testeUpdate() {
		
		
		
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGenereuc.pesquisar(pessoa);
		pessoa.setIdade(99);
		pessoa.setNome("Nome atualizado");
		pessoa = daoGenereuc.updateMerge(pessoa);
		System.out.println(pessoa);
	}
	
	
	@Test 
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGenereuc.pesquisar(pessoa);
		daoGenereuc.deletarPorId(pessoa);
		
	}

	
	
	
	@Test 
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		 List<UsuarioPessoa> list =daoGenereuc.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------");
			
		}
		
		
	}
	
	
	
	@Test 
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		 List<UsuarioPessoa> list =daoGenereuc.geEntityManager().createQuery(" from UsuarioPessoa where nome = 'Nome atualizado'").getResultList();
		 
		 for (UsuarioPessoa usuarioPessoa : list) {
				System.out.println(usuarioPessoa);
				System.out.println("-------------------------");
			
		}
		 
		
	}
	
	
	@Test 
	public void testeQueryMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		 List<UsuarioPessoa> list =daoGenereuc.geEntityManager().createQuery(" from UsuarioPessoa order by id")
				 .setMaxResults(5).getResultList();
		 
		 for (UsuarioPessoa usuarioPessoa : list) {
				System.out.println(usuarioPessoa);
				System.out.println("-------------------------");
			
		}
		 
		
	}
	
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		 List<UsuarioPessoa> list = daoGenereuc.geEntityManager().createQuery(" from UsuarioPessoa where nome =:nome or sobrenome = :sobrenome")
				 .setParameter("nome", "Nome atualizado")
				 .setParameter("sobrenome", "Freitas").getResultList();
		 
		 
		 for (UsuarioPessoa usuarioPessoa : list) {
				System.out.println(usuarioPessoa);
				System.out.println("-------------------------");
			
		
	}
	
	}
	
	
	
	@Test
	public void testeQuerySomaIdade() {
		
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		Long somaIdade = (Long) daoGenereuc.geEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println("Soma de todas as idades é --> "+somaIdade);
		
	}
	
	
	@Test
	public void testeNamedQuery1 () {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		 List<UsuarioPessoa> list = daoGenereuc.geEntityManager().createNamedQuery("UsuarioPessoa.todos")
				 .getResultList();
		 
		 for (UsuarioPessoa usuarioPessoa : list) {
				System.out.println(usuarioPessoa);
				System.out.println("-------------------------");
			
		
	}
		
		
	}
	
	
	@Test 
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGenereuc = new DaoGeneric<UsuarioPessoa>();
		 List<UsuarioPessoa> list = daoGenereuc.geEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				 .setParameter("nome","Nome atualizado" )
				 .getResultList();
		 
		 for (UsuarioPessoa usuarioPessoa : list) {
				System.out.println(usuarioPessoa);
				System.out.println("-------------------------");
			
		
	}
		
		
	}
	
	
	
	
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGenereuc = new DaoGeneric();
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGenereuc.pesquisarPorId(3L, UsuarioPessoa.class);
		
		
		TelefoneUser teluser = new TelefoneUser();
		
		teluser.setNumero("1999999");
		teluser.setTipo("Celular");
		teluser.setUsuarioPessoa(pessoa);
		
		daoGenereuc.salvar(teluser);
		
		
	}
	
	@Test
	public void testeConsulaTelefone() {
		
		
		DaoGeneric daoGeneric = new DaoGeneric();
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisarPorId(3L, UsuarioPessoa.class);
		
		
			for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
				
				System.out.println(fone.getNumero());
				System.out.println(fone.getTipo());
				System.out.println(fone.getUsuarioPessoa().getNome());
				System.out.println("---------------------------------------");
				
			}
		
		
	}
	
	
	
	
}
