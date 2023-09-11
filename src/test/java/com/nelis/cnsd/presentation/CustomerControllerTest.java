package com.nelis.cnsd.presentation;

import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.service.BankAccountService;
import com.nelis.cnsd.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;
    @Mock
    private BankAccountService bankAccountService;

    @Test
    void get() {
        Customer c = Customer.builder().id(1L).build();
        Mockito.when(customerService.get(Mockito.anyLong())).thenReturn(c);
        Long responseId = customerController.get(1L).getBody().id();
        Mockito.verify(customerService, Mockito.times(1)).get(1L);
        assertEquals(1L, responseId);
    }

    @Test
    void getFailure() {
//        Customer c = Customer.builder().id(1L).build();
//        Mockito.when(customerService.get(Mockito.anyLong())).thenThrow(RuntimeException.class);
//        Long responseId = customerController.get(1L).getBody().id();
//
//        Mockito.verify(customerService, Mockito.times(1)).get(1L);
//
//        assertThrows(customerController.get(1L));
    }
}