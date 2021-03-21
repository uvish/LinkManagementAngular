package sp_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sp_db.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
