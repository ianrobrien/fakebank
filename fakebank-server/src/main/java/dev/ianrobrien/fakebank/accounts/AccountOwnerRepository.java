package dev.ianrobrien.fakebank.accounts;

import org.springframework.data.repository.CrudRepository;

/***
 * Specifies the functions that need to be implemented in order
 * to retrieve accounts
 */
public interface AccountOwnerRepository extends CrudRepository<AccountOwner, Long> {

}
