package sp.microservices.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.microservices.user.entities.User;

public interface UserRepository extends JpaRepository<User,String>
{
    //if you want to implement any custom method or query
    //write
}