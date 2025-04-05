package edu.catolica.api.mikrotik.connection.domain.service;

import edu.catolica.api.mikrotik.connection.domain.dto.UserRegistration;
import me.legrange.mikrotik.MikrotikApiException;

public interface IUserService {
    boolean createAccount(UserRegistration userRegistration) throws MikrotikApiException;
    boolean deleteAccount(String usuario) throws MikrotikApiException;
    boolean updateAccount(UserRegistration usuario) throws MikrotikApiException;
}
