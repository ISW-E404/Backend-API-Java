package com.acme.offirent.cucumber;

import com.acme.offirent.domain.model.*;
import com.acme.offirent.domain.repository.*;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.exception.ResourceConditionException;
import com.acme.offirent.service.OfficeServiceImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ActiveOfficeStepDefs {
    @MockBean
    private OfficeRepository officeRepository;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private DiscountRepository discountRepository;

    @MockBean
    private DistrictRepository districtRepository;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private OfficeService officeService;

    public Department department = new Department(1L,"asas");
    public District district = new District(1L,"sdsd",department);
    public Discount discount = new Discount(1L,30.3F,"sdsd");
    public Account account= new Account(2L,"hola","assa","dni",1L,"sdsd","asa",121313L,true,discount);
    public Office office= new Office(1L,"dsd",1L,12L,true,5.0F,"sdsd",30.3F,true,"sds",district,account);
    public Long officeId = 1L;
    public Long accountId = 2L;


    @TestConfiguration
    static class OfficeServiceImplTestConfiguration{

        @Bean
        public OfficeService officeService(){
            return new OfficeServiceImpl();
        }
    }

    @Test
    @Given("Offi-provider has premium Account")
    public void offiProviderHasPremium() {
        account.setPremium(true);
        assertThat(account.isPremium()).isTrue();
    }

    @Test
    @And("Offi-provider is in the Deactivated Office window")
    public void offiProviderIsInTheDeactivatedOfficeWindow() {
        departmentRepository.save(department);
        districtRepository.save(district);
        discountRepository.save(discount);
        accountRepository.save(account);
        officeRepository.save(office);
        office.setStatus(false);
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));

        assertThat(officeService.getOfficeById(officeId).isStatus()).isEqualTo(false);

    }

    @Test
    @When("Offi-provider clicks in Activate Product")
    public void offiProviderClicksInActivateProduct() {
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        officeService.activeOffice(accountId,officeId);
    }

    @Test
    @Then("the system change the office status to activated")
    public void theSystemChangeTheOfficeStatusTo() {
        office.setStatus(false);
        account.setPremium(true);

        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        assertThat(officeService.activeOffice(accountId,officeId).isStatus()).isTrue();

    }

    @Test
    @Given("Offi-provider has not premium Account")
    public void offiProviderHasNotPremium() {
        account.setPremium(false);
        assertThat(account.isPremium()).isFalse();
    }

    @Test
    @Then("the system show the message Resource Office can not be changed with status with value activated")
    public void theSystemShowTheMessage() {
        Office office1 = new Office();
        office.setStatus(false);
        Account account1 = new Account();
        account.setPremium(false);

        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office1));
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account1));

        Throwable exception = catchThrowable(()-> {Office searchedOffice=officeService.activeOffice(accountId,officeId);});
        assertThat(exception)
                .isInstanceOf(ResourceConditionException.class)
                .hasMessage("Resource Office can not be changed with status with value activated");
    }
}
