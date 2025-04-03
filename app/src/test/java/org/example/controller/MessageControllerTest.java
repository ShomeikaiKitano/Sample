package org.example.controller;

import org.example.entity.Message;
import org.example.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @BeforeEach
    public void setup() {
        // 各テスト前にデータをクリア
        messageRepository.deleteAll();
    }

    @Test
    public void shouldCreateMessage() {
        // 準備
        String content = "{\"content\":\"テストメッセージ\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(content, headers);

        // 実行
        ResponseEntity<Message> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/api/messages", 
                request, 
                Message.class);

        // 検証
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isEqualTo("テストメッセージ");
    }

    @Test
    public void shouldGetAllMessages() {
        // データ準備
        messageRepository.save(new Message("テストメッセージ1"));
        messageRepository.save(new Message("テストメッセージ2"));

        // 実行
        ResponseEntity<Message[]> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/api/messages", 
                Message[].class);

        // 検証
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(2);
    }
}