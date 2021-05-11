package com.gear.dev.nett.ws.client.infra.data;

import com.gear.dev.nett.ws.client.core.data.ClientLogin;
import com.gear.dev.nett.ws.client.core.data.ClientLoginRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

/**
 * Este seria o repositório que iria ao BD e carregaria a lista de clientes, porém por questões de facilidade de uso, a
 * implementação irá apenas carregar uma lista estática de valores sendo criados em memória.
 *
 * @author guerinorodella
 */
@Repository
public class ClientLoginRepositoryImpl implements ClientLoginRepository {

    private int lastGeneratedId;

    @Override
    public List<ClientLogin> loadAllActiveClients() {
        return Arrays.asList(new ClientLogin(randomId(),
                        "Cliente 1",
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        "US",
                        UUID.randomUUID().toString(),
                        false,
                        true),
                new ClientLogin(randomId(),
                        "Cliente 2",
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        "US",
                        UUID.randomUUID().toString(),
                        false,
                        true),
                new ClientLogin(randomId(),
                        "Cliente 3",
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        "US",
                        UUID.randomUUID().toString(),
                        false,
                        true));
    }

    private int randomId() {
        int randomId = new Random(currentTimeMillis()).nextInt(100);
        if(randomId == 0){
            return randomId();
        }
        if (randomId == lastGeneratedId) {
            return randomId();
        }
        lastGeneratedId = randomId;
        return randomId;
    }
}
