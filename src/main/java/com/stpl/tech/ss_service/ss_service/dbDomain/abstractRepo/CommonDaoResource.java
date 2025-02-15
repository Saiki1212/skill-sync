package com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonDaoResource {

    public <T> T update(T data);

    public <T> T add(T data);

    public <T> List<T> findAll(Class<T> data);

    public <T, R> T find(Class<T> data, R key);

    public <T> void delete(T data);

    public <T> void addAll(List<T> data);

}
