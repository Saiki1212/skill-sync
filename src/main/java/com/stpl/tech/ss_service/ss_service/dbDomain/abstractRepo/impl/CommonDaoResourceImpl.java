package com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.impl;

import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.CommonDaoResource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommonDaoResourceImpl implements CommonDaoResource {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public <T> T update(T data) {
        try {
            data = manager.merge(data);
            manager.flush();
            return data;
        } catch (Exception e) {
            log.error("Error updating {}", data.getClass().getName(), e);
        }
        return null;
    }

    @Override
    public <T> T add(T data) {
        try {
            manager.persist(data);
            manager.flush();
            return data;
        } catch (Exception e) {
            log.error("Error adding {}", data.getClass().getName(), e);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(Class<T> data) {
        Query query = manager.createQuery("FROM " + data.getName() + " T");
        return query.getResultList();
    }

    @Override
    public <T, R> T find(Class<T> data, R key) {
        return manager.find(data, key);
    }

    @Override
    public <T> void delete(T data) {
        try {
            manager.remove(data);
            manager.flush();
        } catch (Exception e) {
            log.error("Error deleting {}", data.getClass().getName(), e);
        }
    }

    @Override
    public <T> void addAll(List<T> tList) {
        try {
            for (T data : tList) {
                manager.persist(data);
            }
            manager.flush();
        } catch (Exception e) {
            log.error("Error adding {}", tList.getClass().getName(), e);
        }
    }

}
