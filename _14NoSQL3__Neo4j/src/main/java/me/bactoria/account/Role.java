package me.bactoria.account;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/* Neo4j는

관계를 만듬.

그냥 엔티티 하나인듯.

Category - Post 관계에서 Post를 만든거고,

관계 맺는건 Account에서 @Relationship을 이용함.

* */

@NodeEntity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
