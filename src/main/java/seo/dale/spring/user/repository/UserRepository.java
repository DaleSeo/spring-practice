package seo.dale.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seo.dale.spring.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
