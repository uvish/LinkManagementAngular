package com.uvish.springApi.controller;

import com.uvish.springApi.entity.Link;
import com.uvish.springApi.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @PostMapping("/add")
    public ResponseEntity<String> addLink(@Valid @RequestBody Link link){
        linkService.save(link);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<String> removeLink(@PathVariable("id")Long id){
        linkService.remove(id);
        return new ResponseEntity<>("Removed", HttpStatus.OK);
    }
    @GetMapping("all/{userid}")
    public List<Link> all(@PathVariable("userid")Long userid)
    {
        return linkService.findAll(userid);
       //return new ResponseEntity<List>(linkService.findAll(userid),HttpStatus.OK);
    }
    @GetMapping("all/user/{username}")
    public List<Link> allByUsername(@PathVariable("username")String username){
        return linkService.findAllByName(username);
    }


}
