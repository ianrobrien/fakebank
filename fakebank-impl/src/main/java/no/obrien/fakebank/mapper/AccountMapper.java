package no.obrien.fakebank.mapper;

import no.obrien.fakebank.model.Account;
import no.obrien.fakebank.model.AccountBalance;
import no.obrien.fakebank.model.AccountDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/***
 * Provides mapping functions for the Account class
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

  /***
   * Maps an Account object to an AccountDetails object to return in a resposne
   * @param account the given account
   * @return the account details
   */
  @Mapping(target = "accountId", source = "id")
  @Mapping(target = "balance", source = "balance", numberFormat = "#.00")
  @Mapping(target = "currency", source = "currency")
  @Mapping(target = "ownerId", source = "account.owner.id")
  AccountDetails toAccountDetails(Account account);

  /***
   * Maps an Account object to an AccountBalance object to return in a resposne
   * @param account the given account
   * @return the account balance
   */
  @Mapping(target = "accountId", source = "id")
  @Mapping(target = "balance", source = "balance", numberFormat = "#.00")
  @Mapping(target = "currency", source = "currency")
  AccountBalance toAccountBalance(Account account);
}
