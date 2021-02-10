package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.UsuarioPessoa;
import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<E>{
	
	private EntityManager entityManager = HibernateUtil.geEntityManager();
	
	
	public void salvar(E entidade) {
		EntityTransaction trasaction = entityManager.getTransaction();
		trasaction.begin();
		entityManager.persist(entidade);
		trasaction.commit();
		
		
		
	}
	
	public E pesquisar(E entidade) {
	Object id = HibernateUtil.getPRimaryKey(entidade);
	E e = (E) entityManager.find(entidade.getClass(), id);
	return e;
	}
	
	
	public E pesquisarPorId(Long id, Class<E>entidade) {
		E e = (E) entityManager.find(entidade, id);
		return e;
		}
	
	
	public E updateMerge(E entidade) {
		
		
		EntityTransaction trasaction = entityManager.getTransaction();
		trasaction.begin();
		E entidadeSalva  = entityManager.merge(entidade);
		trasaction.commit();
		return entidadeSalva;
		
		
	}
	
	public void deletarPorId(E entidade) {
		
		Object id = HibernateUtil.getPRimaryKey(entidade);
		EntityTransaction trasaction = entityManager.getTransaction();
		trasaction.begin();
		entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase()+
				" where id=" + id).executeUpdate();
		trasaction.commit();
		
		
	}
	
	
	
	public List<E> listar (Class<E> entidade){
		EntityTransaction trasaction = entityManager.getTransaction();
		trasaction.begin();
		
		List<E> lista  = entityManager.createQuery("from " + entidade.getName()).getResultList();
		trasaction.commit();
		return lista;
		
		
	}
	
	
	
	public EntityManager geEntityManager() {
		
		return entityManager;
	}

	

}
