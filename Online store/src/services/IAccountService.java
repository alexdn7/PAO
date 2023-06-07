package services;

import models.Costumer;

public interface IAccountService {
    void createAccount(Costumer costumer);

    void login(String email);

    void changeAddress(String address);

    void changePhoneNumber(String phoneNumber);

    void deleteAccount();

    String accountDetails();
}
