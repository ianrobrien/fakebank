package no.obrien.fakebank.repository;

import no.obrien.fakebank.model.Owner;
import org.springframework.data.repository.CrudRepository;

/***
 * Specifies the functions that need to be implemented in order
 * to retrieve accounts
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
