package com.acme.offirent;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.Resource;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.repository.ResourceRepository;
import com.acme.offirent.domain.service.ResourceService;
import com.acme.offirent.exception.ResourceNotFoundException;
import com.acme.offirent.service.ResourceServiceImpl;
import org.junit.jupiter.api.DisplayName;
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
public class ResourceServiceTest {

    @MockBean
    private ResourceRepository resourceRepository;

    @MockBean
    private OfficeRepository officeRepository;

    @Autowired
    private ResourceService resourceService;

    @TestConfiguration
    static class ResourceServiceTestConfiguration{

        @Bean
        public ResourceService resourceService(){return new ResourceServiceImpl();
        }
    }

    @Test
    @DisplayName("When CreateOffice With ValidOffice Then Return sResource")
    public void WhenCreateOfficeWithValidOfficeThenReturnsResource(){
        //Arrangw
        Long officeId = 1L;
        Office office = new Office();
        Resource resource = new Resource();
        when(officeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(resourceRepository.save(resource)).thenReturn(resource);

        //Act
        Resource resourceExample = resourceService.createResource(officeId, resource);

        //Assert
        assertThat(resourceExample).isEqualTo(resource);
    }

    @Test
    @DisplayName("When CreateOffice With InvalidOffice Then Returns ResourceNotFoundException")
    public void WhenCreateOfficeWithInvalidOfficeThenReturnsResourceNotFoundException(){
        //Arrange
        Long invalidId = 1L;
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Office", "Id", invalidId);

        Resource resource = new Resource();

        when(officeRepository.findById(invalidId)).thenReturn(Optional.empty());

        //Act
        Throwable exception = catchThrowable(()->{
            Resource resourceExample = resourceService.createResource(invalidId, resource);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


}
