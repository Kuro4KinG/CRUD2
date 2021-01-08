package com.kuro4king.crud.model;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    Long id;
    String firstName;
    String lastName;
    List<Post> posts;
    Region region;
    Role role;

    public User(Long id, String firstName, String lastName, List<Post> posts, Region region, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.region = region;
        this.role = role;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        List<Long> posts = this.posts.stream().map(Post::getId).collect(Collectors.toList());
        String writePosts = posts.toString().replaceAll(" ", "");
        return id + ". " + firstName + ", " + lastName + ", " + writePosts + ", " + region.getName() + ", " + role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
