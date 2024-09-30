package io.java.learning.reactor.repositories;

import io.java.learning.reactor.pojo.TUser;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 *
 */
public interface UserRepository extends ReactiveCrudRepository<TUser,String> {
    @Query("select tid,name,age  from T_User u where u.name = :name")
    Flux<TUser> findByEmailAddress(@Param("name") String name);
}
