package com.bettercode.connect.rest;


import com.bettercode.connect.ConnectWebApplicationTests;
import com.bettercode.connect.entity.ApprovalHoliday;
import com.bettercode.connect.query.dto.CreatedApprovalHoliday;
import com.bettercode.connect.repository.IApprovalHolidayRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApprovalHolidayRestControllerTest extends ConnectWebApplicationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private IApprovalHolidayRepository approvalHolidayRepository;

  private final String BASE_URL = "/api/v1/approval-holiday";

  @Test
  public void creatingApprovalHoliday() {
    // given
    final String requestBody = "{\n" +
        "  \"applicant\": \"li.dongxun\",\n" +
        "  \"holidayStartAt\": \"2019-06-01\",\n" +
        "  \"holidayEndAt\": \"2019-06-15\",\n" +
        "  \"reason\": \"첫째아기 병원\",\n" +
        "  \"approver\": \"ahn.younghoe\"\n" +
        "}";

    // when
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ResponseEntity<Long> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.PUT, new HttpEntity<>(requestBody, headers), Long.class);

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    Optional<ApprovalHoliday> approvalHoliday = approvalHolidayRepository.findById(responseEntity.getBody());
    if(approvalHoliday.isPresent()) {
      assertThat(approvalHoliday.get().getApplicant()).isEqualTo("li.dongxun");
      assertThat(approvalHoliday.get().getApprover()).isEqualTo("ahn.younghoe");
      assertThat(approvalHoliday.get().getReason()).isEqualTo("첫째아기 병원");
      assertThat(approvalHoliday.get().getApprove()).isEqualTo(false);
    }
  }

  @Test
  public void findApprovalHolidayById() {
    // given (before => approvalHoliday1(id = 1))

    // when
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ResponseEntity<CreatedApprovalHoliday> responseEntity = restTemplate.exchange(BASE_URL + "?id=1", HttpMethod.GET, new HttpEntity<>(headers), CreatedApprovalHoliday.class);

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    assertThat(Objects.requireNonNull(responseEntity.getBody()).getId()).isEqualTo(1L);
    assertThat(responseEntity.getBody().getApplicant()).isEqualTo("lee.donghoon");
    assertThat(responseEntity.getBody().getApprover()).isEqualTo("ahn.younghoe");
  }
}
