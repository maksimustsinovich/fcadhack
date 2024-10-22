package by.ustsinovich.fcadhack.controller;

import by.ustsinovich.fcadhack.config.FilteringConfig;
import by.ustsinovich.fcadhack.dto.FilteringRuleDto;
import by.ustsinovich.fcadhack.dto.MessageDto;
import by.ustsinovich.fcadhack.service.FilteringRuleService;
import by.ustsinovich.fcadhack.service.MessageService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class FilteringController {

    private final FilteringRuleService filteringRuleService;

    private final FilteringConfig filteringConfig;

    private final MessageService messageService;

    @PostMapping("/api/filter")
    public ResponseEntity<Object> filter(@RequestBody String body) {
        List<FilteringRuleDto> rules = filteringRuleService.getEnabled();

        MessageDto messageDto = new MessageDto();

        messageDto.setBody(body);

        messageService.create(messageDto);

        for (final FilteringRuleDto rule : rules) {
            Pattern pattern = Pattern.compile(rule.getRegex());

            if (pattern.matcher(body).find()) {
                switch (filteringConfig.getGlobalType()) {
                    case MASK -> body = body.replaceAll(pattern.pattern(), "***");
                    case REMOVE -> {
                        return ResponseEntity.badRequest().build();
                    }
                    case IGNORE -> body = body.replaceAll(pattern.pattern(), "");
                }
            }
        }

        String endpoint = extractEndpointFromJson(body);

        RestTemplate restTemplate = new RestTemplate();

        Object r = restTemplate.postForObject(endpoint, body, Object.class);

        return ResponseEntity.ok(r);
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String extractEndpointFromJson(String jsonString) {
        try {
            // Преобразуем строку JSON в JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Извлекаем значение поля "endpoint"
            JsonNode endpointNode = jsonNode.get("endpoint");

            // Проверяем, что поле существует и возвращаем его значение
            if (endpointNode != null) {
                return endpointNode.asText();
            } else {
                throw new IllegalArgumentException("Поле 'endpoint' не найдено в JSON");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при парсинге JSON", e);
        }
    }

}
