package me.bactoria.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // @Query 가 없어도 동작.
    Optional<Account> findByUsername(String username);

    // @Query 가 없어도 동작.
    //Account findByUsername(String username);

}
