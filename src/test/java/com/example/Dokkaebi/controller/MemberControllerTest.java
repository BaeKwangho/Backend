package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.Repository.MemberRepository;
import com.example.Dokkaebi.domain.Auth;
import com.example.Dokkaebi.service.MemberService;
import jdk.jfr.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .identity("sadf")
                .name("Asdf")
                .auth(Auth.ADMIN)
                .birth("asdf")
                .build();

        String url = "http://localhost:" + port + "/member/new";
        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,requestDto,String.class );
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}