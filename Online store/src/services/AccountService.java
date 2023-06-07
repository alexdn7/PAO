package services;

import models.Costumer;
import repositories.IDatabaseRepository;

public class AccountService implements IAccountService {
    IDatabaseRepository databaseRepository;
    private final AuditService auditService = new AuditService();

    private AccountService(IDatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    private static class SingletonHolder {
        public static AccountService createInstance(IDatabaseRepository databaseRepository) {
            return new AccountService(databaseRepository);
        }
    }

    public static AccountService getInstance(IDatabaseRepository databaseRepository) {
        return AccountService.SingletonHolder.createInstance(databaseRepository);
    }

    public void createAccount(Costumer costumer) {
        databaseRepository.createAccount(costumer);
        auditService.logAction("createAccount");
    }

    public void login(String email) {
        databaseRepository.login(email);
        auditService.logAction("login");
    }

    public void changeAddress(String address) {
        databaseRepository.changeAddress(address);
        auditService.logAction("changeAddress");
    }

    public void changePhoneNumber(String phoneNumber) {
        databaseRepository.changePhoneNumber(phoneNumber);
        auditService.logAction("changeAddress");
    }

    public void deleteAccount() {
        databaseRepository.deleteAccount();
        auditService.logAction("deleteAccount");
    }

    public String accountDetails() {
        auditService.logAction("accountDetails");
        return String.join(", ", databaseRepository.accountDetails());
    }
}
