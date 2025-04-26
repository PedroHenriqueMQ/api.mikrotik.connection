package edu.catolica.api.mikrotik.connection.domain.service;

import edu.catolica.api.mikrotik.connection.domain.dto.UserRegistration;
import edu.catolica.api.mikrotik.connection.domain.exception.ServerConnectionTimeoutException;
import edu.catolica.api.mikrotik.connection.infra.MikrotikConnectionProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static edu.catolica.api.mikrotik.connection.domain.constants.MikrotikCommands.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final MikrotikConnectionProvider provider;

    private String formulateComment(UserRegistration user) {
        return String.format(
                "MATRICULA:%s; EMAIL:%s; NOME:%s; TELEFONE:%s;",
                user.registration(),
                user.email(),
                user.name(),
                user.phoneNumber()
        );
    }

    private String formulateCommand(String command, Object... args) {
        return String.format(command, args);
    }

    @Override
    public boolean createAccount(UserRegistration userRegistration) throws MikrotikApiException {
        var createUserCommand = formulateCommand(
                CREATE_USER,
                userRegistration.user(),
                userRegistration.password(),
                formulateComment(userRegistration)
        );

        provider.getConnection().execute(createUserCommand);
        return true;
    }

    @Override
    public boolean deleteAccount(String username) throws MikrotikApiException {
        var searchedUser = searchUser(username);

        if (!searchedUser.isEmpty()) {
            var userId = searchedUser.getFirst().get(".id");
            var deleteCommand = formulateCommand(DELETE_USER, userId);

            provider.getConnection().execute(deleteCommand);
            log.info("Usuário removido com sucesso.");
            return true;
        }

        return false;
    }

    @Override
    public boolean updateAccount(UserRegistration user) throws MikrotikApiException {
        var searchedUser = searchUser(user.user());

        if (!searchedUser.isEmpty()) {
            var userId = searchedUser.getFirst().get(".id");
            var comment = formulateComment(user);
            var updateCommand = formulateCommand(
                    UPDATE_USER,
                    userId,
                    user.user(),
                    user.password(),
                    comment
            );

            provider.getConnection().execute(updateCommand);
            log.info("Usuário atualizado com sucesso.");
            return true;
        }

        return false;
    }

    private List<Map<String, String>> searchUser(String username) throws MikrotikApiException {
        var searchCommand = formulateCommand(SEARCH_USER, username);
        var searchResult = provider.getConnection().execute(searchCommand);

        if (searchResult.isEmpty())
            log.warn("Usuário não encontrado.");

        return searchResult;
    }
}

