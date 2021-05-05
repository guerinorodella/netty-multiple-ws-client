package com.gear.dev.nett.ws.client.di;

import com.gear.dev.nett.ws.client.core.data.ClientLoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

/**
 * @author guerinorodella
 */
public class UnitTestConfiguration extends SpringConfiguration {

    @Bean
    @Primary
    public ClientLoginRepository getClientLoginRepository() {
        return mock(ClientLoginRepository.class);
    }

}
