package com.gear.dev.nett.ws.client.core;

import com.gear.dev.nett.ws.client.core.data.ClientLogin;
import com.gear.dev.nett.ws.client.core.data.ClientLoginFactory;
import com.gear.dev.nett.ws.client.core.data.ClientLoginRepository;
import com.gear.dev.nett.ws.client.di.UnitTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientLoaderServiceImplTest {

    private static final int FOO_ID = 999;

    private ApplicationContext context;
    private ClientLoaderService instance;
    private ClientLoginRepository repository;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(UnitTestConfiguration.class);
        instance = context.getBean(ClientLoaderService.class);
        repository = context.getBean(ClientLoginRepository.class);
    }

    @Test
    public void retornaraUmaListaVazia_quandoRepositorioRetornarNulo() {
        when(repository.loadAllActiveClients()).thenReturn(null);

        List<ClientData> result = instance.loadAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, atLeastOnce()).loadAllActiveClients();
    }

    @Test
    public void retornaraUmaListaVazia_quandoRepositorioRetornar_ListaVazia() {
        when(repository.loadAllActiveClients()).thenReturn(emptyList());

        List<ClientData> result = instance.loadAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, atLeastOnce()).loadAllActiveClients();
    }

    @Test
    public void retornara3Elementos_quandoExistirem_3ElementosAtivos() {
        var expectedList = Arrays.asList(new ClientLogin(), new ClientLogin(), new ClientLogin());
        when(repository.loadAllActiveClients()).thenReturn(expectedList);

        List<ClientData> result = instance.loadAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        verify(repository, atLeastOnce()).loadAllActiveClients();
    }

    @Test
    public void mapeara3Elementos_quandoExistirem_3ElementosAtivos() {
        var clientLoginList = ClientLoginFactory.create()
                .addValid(FOO_ID, "Client #1", "us")
                .addValid(FOO_ID, "Client #2", "us")
                .addValid(FOO_ID, "Client #3", "us")
                .build();
        when(repository.loadAllActiveClients()).thenReturn(clientLoginList);

        var expectedList = ClientDataFactory.create()
                .from(clientLoginList)
                .build();
        List<ClientData> resultList = instance.loadAll();

        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
        assertArrayEquals(expectedList.toArray(), resultList.toArray());
        verify(repository, atLeastOnce()).loadAllActiveClients();
    }
}