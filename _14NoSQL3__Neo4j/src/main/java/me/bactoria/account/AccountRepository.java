package me.bactoria.account;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/* Neo4j 이용
*
* 1. SessionFactory 쓴다.
* 2. Repository 쓴다.
*
* 둘중 하나 쓰면 됨.
*
* Runner로 다 구현되있음.
* */

public interface AccountRepository extends Neo4jRepository<Account, Long> {
}
