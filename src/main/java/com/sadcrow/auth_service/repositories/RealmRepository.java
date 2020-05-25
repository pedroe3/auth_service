package com.sadcrow.auth_service.repositories;

import com.sadcrow.auth_service.entity.Realm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmRepository extends JpaRepository<Realm, Long> {
}
