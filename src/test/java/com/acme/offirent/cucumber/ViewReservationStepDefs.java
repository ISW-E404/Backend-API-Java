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

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ViewReservationStepDefs {
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
    @Given("Offi-user is within in the Home window")
    public void offiUserIsWithinInTheHomeWindow() {
        Account account = new Account();
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
    }

    @Test
    @When("he clicks in the Reservations button")
    public void heClicksInTheReservationsButton() {
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
        List<Reservation> reservations = new List<Reservation>() {
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
            public Iterator<Reservation> iterator() {
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
            public boolean add(Reservation reservation) {
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
            public boolean addAll(Collection<? extends Reservation> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Reservation> c) {
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
            public Reservation get(int index) {
                return null;
            }

            @Override
            public Reservation set(int index, Reservation element) {
                return null;
            }

            @Override
            public void add(int index, Reservation element) {

            }

            @Override
            public Reservation remove(int index) {
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
            public ListIterator<Reservation> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Reservation> listIterator(int index) {
                return null;
            }

            @Override
            public List<Reservation> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        int reservationCount = reservations.size();
        Page<Reservation> reservationPage = new PageImpl<>(reservations,pageable,reservationCount);

        when(reservationRepository.findByAccountId(accountId,pageable )).thenReturn(reservationPage);
    }

    @Test
    @Then("the system shows the Reservation window with all the Reservations")
    public void theSystemShowsTheReservationWindowWithAllTheReservations() {
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
        List<Reservation> reservations = new List<Reservation>() {
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
            public Iterator<Reservation> iterator() {
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
            public boolean add(Reservation reservation) {
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
            public boolean addAll(Collection<? extends Reservation> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Reservation> c) {
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
            public Reservation get(int index) {
                return null;
            }

            @Override
            public Reservation set(int index, Reservation element) {
                return null;
            }

            @Override
            public void add(int index, Reservation element) {

            }

            @Override
            public Reservation remove(int index) {
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
            public ListIterator<Reservation> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Reservation> listIterator(int index) {
                return null;
            }

            @Override
            public List<Reservation> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        int reservationCount = reservations.size();
        Page<Reservation> reservationPage = new PageImpl<>(reservations,pageable,reservationCount);
        when(reservationRepository.findByAccountId(accountId, pageable)).thenReturn(reservationPage);

        assert(reservationService.getAllReservationsByAccountId(accountId,pageable)==reservationPage);

    }

    @Test
    @Given("Offi-user is within of the Reservations window")
    public void offiUserIsWithinOfTheReservationsWindow() {
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
        List<Reservation> reservations = new List<Reservation>() {
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
            public Iterator<Reservation> iterator() {
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
            public boolean add(Reservation reservation) {
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
            public boolean addAll(Collection<? extends Reservation> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Reservation> c) {
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
            public Reservation get(int index) {
                return null;
            }

            @Override
            public Reservation set(int index, Reservation element) {
                return null;
            }

            @Override
            public void add(int index, Reservation element) {

            }

            @Override
            public Reservation remove(int index) {
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
            public ListIterator<Reservation> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Reservation> listIterator(int index) {
                return null;
            }

            @Override
            public List<Reservation> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        int reservationCount = reservations.size();
        Page<Reservation> reservationPage = new PageImpl<>(reservations,pageable,reservationCount);
        when(reservationRepository.findAll(pageable)).thenReturn(reservationPage);
    }

    @Test
    @When("he clicks in a Reservation")
    public void heClicksInAReservation() {
        Long reservationId = 1L;
        Long accountId = 2L;
        Reservation reservation = new Reservation();

       when(reservationRepository.findByIdAndAccountId(reservationId,accountId)).thenReturn(Optional.of(reservation));

    }

    @Test
    @Then("the system shows details of the selected Reservation")
    public void theSystemShowsDetailsOfTheSelectedReservation() {
        Long reservationId = 1L;
        Long accountId = 2L;
        Reservation reservation = new Reservation();

        when(reservationRepository.findByIdAndAccountId(reservationId,accountId)).thenReturn(Optional.of(reservation));

        assert(reservationService.getReservationByIdAndAccountId(accountId,reservationId)==reservation);
    }
}
