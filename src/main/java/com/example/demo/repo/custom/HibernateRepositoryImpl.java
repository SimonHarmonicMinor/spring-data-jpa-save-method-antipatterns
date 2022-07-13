package com.example.demo.repo.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
class HibernateRepositoryImpl<T> implements HibernateRepository<T> {
  @PersistenceContext
  private EntityManager em;

  @Override
  public <S extends T> S persist(S entity) {
    em.persist(entity);
    return entity;
  }

  @Override
  public <S extends T> S merge(S entity) {
    return em.merge(entity);
  }
}
