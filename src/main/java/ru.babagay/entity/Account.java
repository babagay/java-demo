package ru.babagay.entity;

/**
 * Created by babagay on 31.05.16.
 */
public class Account implements DomainObject {

    private long id;
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String email;
    private boolean active;
    private String created;
    private String updated;
    private boolean deleted;
    private boolean completed;
    private String uid;

    private String localPhoto;



    public String getLocalPhoto() {
        return localPhoto;
    }

    public void setLocalPhoto(String localPhoto) {
        this.localPhoto = localPhoto;
    }




    public Account(String login, String password, String first_name, String last_name, String email, String uid) {
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.uid = uid;
    }

    public Account(long id, String login, String password,
                   String first_name, String last_name, String middle_name,
                   String email,
                   boolean active, String created, String updated, boolean deleted,
                   boolean completed, String uid) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.email = email;
        this.active = active;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.completed = completed;
        this.uid = uid;
    }

    public Account(String login, String password, String first_name, String last_name, String email) {

        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
