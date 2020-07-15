package io.github.paulooorg.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.paulooorg.infra.Page;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.entities.BaseEntity;

public abstract class AbstractEntityRepository<E extends BaseEntity, PK extends Serializable> {
	@Inject
	protected EntityManager em;
	
	private final Class<E> entityType;

    public AbstractEntityRepository(Class<E> entityType) {
        this.entityType = entityType;
    }
    
    public Optional<E> save(E entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.flush();
        return Optional.of(entity);
    }
    
    public void remove(E entity) {
        em.remove(entity);
        em.flush();
    }
    
    public Optional<E> findBy(PK primaryKey) {
        return Optional.ofNullable(em.find(entityType, primaryKey));
    }
    
    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e ", entityType).getResultList();
    }
    
    public Page<E> findAll(Pagination pagination) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	
    	Long count = count();
    	
    	CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityType);
    	Root<E> from = criteriaQuery.from(entityType);
    	CriteriaQuery<E> select = criteriaQuery.select(from);
    	TypedQuery<E> typedQuery = em.createQuery(select);
    	typedQuery.setFirstResult((pagination.getPage() - 1) * pagination.getPerPage());
    	typedQuery.setMaxResults(pagination.getPerPage());
    	List<E> result = typedQuery.getResultList();
    	
    	return new Page<E>(result, count, calculateNumberOfPages(count, pagination), Long.valueOf(pagination.getPage()));
    }
    
    private long calculateNumberOfPages(Long totalRows, Pagination pagination) {
    	return totalRows % pagination.getPerPage() == 0 ? totalRows / pagination.getPerPage() : totalRows / pagination.getPerPage() + 1;
    }
    
    private Long count() {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    	Root<E> fromCount = countQuery.from(entityType);
    	countQuery.select(criteriaBuilder.count(fromCount));
    	Long count = em.createQuery(countQuery).getSingleResult();
    	return count;
    }
    
    public Page<E> paginate(Query findQuery, Query countQuery, Pagination pagination) {
    	Long count = (Long) countQuery.getSingleResult();
    	findQuery.setFirstResult((pagination.getPage() - 1) * pagination.getPerPage());
    	findQuery.setMaxResults(pagination.getPerPage());
    	List<E> result = findQuery.getResultList();
    	return new Page<E>(result, count, calculateNumberOfPages(count, pagination), Long.valueOf(pagination.getPage()));
    }
}
