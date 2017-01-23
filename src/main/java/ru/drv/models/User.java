package ru.drv.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group_users")
public class User {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "group_users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Auto> auto = new ArrayList<>();

    public User(){

    }

    public User(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.auto = builder.auto;
    }

    public static class Builder{
        private int id;
        private String name;
        private int age;
        private List<Auto> auto = new ArrayList<>();

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setAuto(Auto auto){
            this.auto.add(auto);
            return this;
        }

        public Builder setAuto(List<Auto> auto){
            this.auto.addAll(auto);
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Auto> getAuto() {
        return auto;
    }

    public void setAuto(List<Auto> auto) {
        this.auto = auto;
    }
}
