package com.sadcrow.auth_service.services;

import com.sadcrow.auth_service.entity.Realm;
import com.sadcrow.auth_service.error.RealmNotFoundException;
import com.sadcrow.auth_service.repositories.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealmService {

    @Autowired
    private RealmRepository realmRepository;

    //CREATE REALM
    public Realm createRealm(Realm realm){
        realm.setActive(true);
        return realmRepository.save(realm);
    }

    //get all reamls
    public Page<Realm> getAllRealmsPageable(Pageable pageable){
        return realmRepository.findAll(pageable);
    }


    //get realm by id
    public Realm getById(Long id){
        Optional<Realm> realm = realmRepository.findById(id);
        if(!realm.isPresent()){
            throw new RealmNotFoundException("realm not found - realm service - id:" + id);
        }
        return realm.get();
    }

    //update realm


}
