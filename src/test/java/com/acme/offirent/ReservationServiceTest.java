package com.acme.offirent;


import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.District;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.repository.ReservationRepository;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.exception.ResourceNotFoundException;
import com.acme.offirent.service.AccountServiceImpl;
import com.acme.offirent.service.OfficeServiceImpl;
import com.acme.offirent.service.ReservationServiceImpl;
import org.junit.jupiter.api.DisplayName;
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
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReservationServiceTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private ReservationService reservationService;


    @TestConfiguration
    static class ReservationServiceImplTestConfiguration{

        @Bean
        public ReservationService reservationService(){
            return new ReservationServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetReservationByIdAndAccountId With Valid Id Then Returns Reservation")
    public void whenGetReservationByIdAndAccountIdWithValidIdThenReturnsReservation(){

        //Arrange
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);

        Long accountId = 2L;
        Account account = new Account();
        account.setId(accountId);



        when(reservationRepository.findByIdAndAccountId(reservation.getId(),account.getId()))
                .thenReturn(Optional.of(reservation));
        //Act
        Reservation searchedReservation = reservationService.getReservationByIdAndAccountId(accountId,reservationId);
        //Assert
        assertThat(searchedReservation.getId()).isEqualTo(reservationId);
    }

    @Test
    @DisplayName("When GetReservationByIdAndAccountId With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetReservationByIdAndAccountIdWithValidIdThenReturnsResourceNotFoundException(){

        //Arrange
        Long invalidReservationId = 1L;
        Long invalidAccountId = 2L;

        String template ="Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template,"Account","Id",invalidAccountId);

        when(reservationRepository.findByIdAndAccountId(invalidReservationId,invalidAccountId))
                .thenReturn(Optional.empty());

        //Act
        Throwable exception= catchThrowable(()->{
            Reservation searchedReservation = reservationService.getReservationByIdAndAccountId(invalidAccountId,invalidReservationId);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }



//    @Test
//    @DisplayName("When GetAllReservationsByAccountId With Valid AccountId Then Returns Reservations")
//    public void whenGetAllReservationsByAccountIdWithValidAccountIdThenReturnsReservations(){
//
//        //Arrange
//
//        Pageable pageable= new Pageable() {
//            @Override
//            public int getPageNumber() {
//                return 0;
//            }
//
//            @Override
//            public int getPageSize() {
//                return 0;
//            }
//
//            @Override
//            public long getOffset() {
//                return 0;
//            }
//
//            @Override
//            public Sort getSort() {
//                return null;
//            }
//
//            @Override
//            public Pageable next() {
//                return null;
//            }
//
//            @Override
//            public Pageable previousOrFirst() {
//                return null;
//            }
//
//            @Override
//            public Pageable first() {
//                return null;
//            }
//
//            @Override
//            public boolean hasPrevious() {
//                return false;
//            }
//        };
//        List<Reservation> reservationList = new List<Reservation>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Reservation> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] a) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Reservation reservation) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Reservation> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(int index, Collection<? extends Reservation> c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public Reservation get(int index) {
//                return null;
//            }
//
//            @Override
//            public Reservation set(int index, Reservation element) {
//                return null;
//            }
//
//            @Override
//            public void add(int index, Reservation element) {
//
//            }
//
//            @Override
//            public Reservation remove(int index) {
//                return null;
//            }
//
//            @Override
//            public int indexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public int lastIndexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public ListIterator<Reservation> listIterator() {
//                return null;
//            }
//
//            @Override
//            public ListIterator<Reservation> listIterator(int index) {
//                return null;
//            }
//
//            @Override
//            public List<Reservation> subList(int fromIndex, int toIndex) {
//                return null;
//            }
//        };
//        int reservationListCount= reservationList.size();
//        Page<Reservation> reservationPage = new PageImpl<>( reservationList,pageable,reservationListCount);
//
//        Long id=2L;
//        Account account= new Account();
//        account.setId(id);
//        account.setReservations(reservationList);
//
//        when(accountRepository.findById(account.getId()))
//                .thenReturn(Optional.of(account));
//
//        //Act
//        Page<Reservation> reservations= reservationService.getAllReservationsByAccountId(id, pageable);
//
//        //Assert
//        assertThat(reservations).isEqualTo(reservationPage);
//    }

    @Test
    @DisplayName("When GetAllReservationsByAccountId With Invalid AccountId Then Returns Resource Resource Not Found Exception")
    public void whenGetAllReservationsByAccountIdWithInvalidAccountIdThenReturnsResourceResourceNotFoundException(){

        //Arrange
        Long id = 1L;

        Pageable pageable=new Pageable() {
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
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template,"Account","Id",id);

        when(accountRepository.findById(id))
                .thenReturn(Optional.empty());

        //Act
        Throwable exception= catchThrowable(()->{
                Page<Reservation> reservations= reservationService.getAllReservationsByAccountId(id, pageable);});

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


}
