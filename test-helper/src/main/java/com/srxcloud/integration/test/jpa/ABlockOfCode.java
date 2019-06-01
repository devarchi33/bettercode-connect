package com.srxcloud.integration.test.jpa;

import javax.persistence.EntityManager;

public interface ABlockOfCode <T> {
    T execute(EntityManager em);
}