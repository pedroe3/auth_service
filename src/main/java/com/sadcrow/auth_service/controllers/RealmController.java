package com.sadcrow.auth_service.controllers;

import com.sadcrow.auth_service.entity.Realm;
import com.sadcrow.auth_service.services.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/realm")
public class RealmController {

    @Autowired
    private RealmService realmService;

    @GetMapping("")
    public HttpEntity<PagedModel<Realm>> realms(Pageable pageable, PagedResourcesAssembler pagedResourcesAssembler ) {
        Page<Realm> realms = realmService.getAllRealmsPageable(pageable);
        return new ResponseEntity<>(pagedResourcesAssembler.toModel(realms), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> createRealm(@RequestBody Realm realm){
        Realm realmCreated = realmService.createRealm(realm);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(realmCreated.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Realm getRealmById(@PathVariable Long id){
        return realmService.getById(id);
    }

}
