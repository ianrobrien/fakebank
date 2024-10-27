package dev.ianrobrien.fakebank.repository;

import dev.ianrobrien.fakebank.model.Owner;
import org.springframework.data.repository.CrudRepository;

/***
 * Specifies the functions that need to be implemented in order
 * to retrieve accounts
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
