package com.acme.offirent;

import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.PaymentMethod;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.PaymentMethodRepository;
import com.acme.offirent.domain.service.PaymentMethodService;
import com.acme.offirent.service.PaymenMethodServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PaymentMethodServiceTest {

    @MockBean
    private PaymentMethodRepository paymentMethodRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @TestConfiguration
    static class PaymentMethodServiceTestConfiguration {
        @Bean
        public PaymentMethodService paymentMethodService() {return new PaymenMethodServiceImpl();
        }
    }

    @Test
    public void WhenGetAllAccountByIdWithValidAccountIdThenReturnsPaymentMethods() {
        //Arrange
        Long accountId = 1L;
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        List<PaymentMethod> paymentMethodList = new List<PaymentMethod>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<PaymentMethod> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(PaymentMethod paymentMethod) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends PaymentMethod> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends PaymentMethod> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public PaymentMethod get(int index) {
                return null;
            }

            @Override
            public PaymentMethod set(int index, PaymentMethod element) {
                return null;
            }

            @Override
            public void add(int index, PaymentMethod element) {

            }

            @Override
            public PaymentMethod remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<PaymentMethod> listIterator() {
                return null;
            }

            @Override
            public ListIterator<PaymentMethod> listIterator(int index) {
                return null;
            }

            @Override
            public List<PaymentMethod> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        int paymentMethodListCount = paymentMethodList.size();
        Page<PaymentMethod> paymentMethodPage = new PageImpl<>(paymentMethodList,pageable,paymentMethodListCount);

        when(paymentMethodRepository.findByAccountId(accountId,pageable)).thenReturn(Optional.of(paymentMethodPage));

        //Act
        Page<PaymentMethod> paymentMethods = paymentMethodService.GetAllByAccountId(accountId,pageable);

        //Assert
        assertThat(paymentMethods).containsExactlyElementsOf(paymentMethodPage);
    }

}
