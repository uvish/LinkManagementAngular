package com.uvish.springApi.service;

import com.uvish.springApi.entity.Link;
import com.uvish.springApi.entity.User;
import com.uvish.springApi.model.UserRequestModel;
import com.uvish.springApi.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    public LinkRepository linkRepository;

    public Optional<Link> getLinkById(Long id){
        Optional<Link> link=linkRepository.findById(id);
        return link;
    }
    public void save(Link link) {
        linkRepository.save(link);
    }
    public void remove(Long id){
        linkRepository.deleteById(id);
    }
    public List<Link> findAll(long userid){
        List<Link> list=new ArrayList<>();
        linkRepository.findAll().forEach((link)->{
            if(link.getUserid()==userid)
            list.add(link);
        });
        return list;
    }
    public List<Link> findAllByName(String username){
        List<Link> list=new ArrayList<>();
        linkRepository.findAll().forEach((link)->{
            if(link.getUsername().equals(username))
                list.add(link);
        });
        return list;
    }

}
