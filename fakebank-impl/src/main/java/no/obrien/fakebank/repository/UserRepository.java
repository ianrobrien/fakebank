package no.obrien.fakebank.repository;

import no.obrien.fakebank.model.User;
import org.springframework.data.repository.CrudRepository;

/***
 * Specifies the functions that need to be implemented in order
 * to retrieve accounts
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
