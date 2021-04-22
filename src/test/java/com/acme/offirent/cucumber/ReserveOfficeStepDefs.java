package com.acme.offirent.cucumber;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.repository.ReservationRepository;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.service.OfficeServiceImpl;
import com.acme.offirent.service.ReservationServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
public class ReserveOfficeStepDefs {

    @MockBean
    private OfficeRepository officeRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private DistrictRepository districtRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private ReservationService reservationService;

    @TestConfiguration
    static class OfficeServiceTestImpl {
        @Bean
        public OfficeService officeService(){return new OfficeServiceImpl();
        }

        @Bean
        public ReservationService reservationService(){return new ReservationServiceImpl();
        }
    }


    @Test
    @Given("Offi-user is within of Offices window")
    public void offiUserIsWithinOfOfficesWindow() {
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
        List<Office> offices = new List<Office>() {
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
            public Iterator<Office> iterator() {
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
            public boolean add(Office office) {
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
            public boolean addAll(Collection<? extends Office> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Office> c) {
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
            public Office get(int index) {
                return null;
            }

            @Override
            public Office set(int index, Office element) {
                return null;
            }

            @Override
            public void add(int index, Office element) {

            }

            @Override
            public Office remove(int index) {
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
            public ListIterator<Office> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Office> listIterator(int index) {
                return null;
            }

            @Override
            public List<Office> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        int officeCount = offices.size();
        Page<Office> officePage = new PageImpl<>(offices,pageable,officeCount);
        when(officeRepository.findAll(pageable)).thenReturn(officePage);
    }

    @Test
    @When("he clicks a Office")
    public void heClicksAOffice() {
        Long officeId = 1L;
        Office office = new Office();
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));
    }

    @Test
    @Then("the system show a window with details of that Office")
    public void theSystemShowAWindowWithDetailsOfThatOffice() {
        Long officeId = 1L;
        Office office = new Office();
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));

        assertThat(officeService.getOfficeById(officeId)).isEqualTo(office);
    }

    @Test
    @Given("Offi-user is within of the selected Office window")
    public void offiUserIsWithinOfTheSelectedOfficeWindow() {
        Long officeId = 1L;
        Office office = new Office();
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));

        assertThat(officeService.getOfficeById(officeId)).isEqualTo(office);
    }

    @Test
    @When("he clicks in the button Reserve")
    public void heClicksInTheButtonReserve() {
        Long officeId = 1L;
        Long accountId = 2L;
        Account account = new Account();
        Office office = new Office();
        Reservation reservation = new Reservation();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(reservationRepository.save(reservation)).thenReturn(reservation);
    }

    @Test
    @Then("the system will register the Reservation")
    public void theSystemWillRegisterTheReservation() {
        Long officeId = 1L;
        Long accountId = 2L;
        Account account = new Account();
        Office office = new Office();
        Reservation reservation = new Reservation();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        assertThat(reservationService.createReservation(reservation, accountId, officeId)).isInstanceOfAny(Reservation.class);
    }
}
