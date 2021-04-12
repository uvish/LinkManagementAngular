package com.uvish.springApi.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
@Entity
@Table(name="Links")
public class Link{
    private @Id @GeneratedValue long id;
    private @NotBlank long userid;
    private @NotBlank String username;
    private @NotBlank String title;
    private @NotBlank String url;
   
    public Link(String title,String url,long userid,String username){
        this.title=title;
        this.url=url;
        this.userid=userid;
        this.username=username;
    }

    public Link() {

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}