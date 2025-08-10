package com.pedasco.datamoontransfertoarman.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedasco.datamoontransfertoarman.dto.ApiResponseWrapper;
import com.pedasco.datamoontransfertoarman.dto.LogEntryDto;
import com.pedasco.datamoontransfertoarman.entity.LogEntity;
import com.pedasco.datamoontransfertoarman.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LogFetcherService {

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final TokenService authService;

    @Autowired
    private final LogRepository logRepository;

    private Long minId = null;

    public LogFetcherService(RestTemplate restTemplate, TokenService authService, LogRepository logRepository) {
        this.restTemplate = restTemplate;
        this.authService = authService;
        this.logRepository = logRepository;
    }

    public List<LogEntryDto> fetchLogsWithMinId(long minId) {
        String url = "http://172.20.15.74:4001/reportsPlate";
        String token = authService.getToken();

        // Headers - مثل متد اولی که کار می‌کرد
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Accept-Language", "en-US,en;q=0.9,fa;q=0.8");
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");
        headers.set("Origin", "http://172.20.15.74:4000");
        headers.set("Referer", "http://172.20.15.74:4000/");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36");

        // JSON body با پارامترهای جدید
        String body = """
    {
      "commonSearchParams": {
        "startDate": 0,
        "min_id": %d,
        "endDate": 17538209999999900,
        "all_camera_ids": [],
        "camera_ids": [],
        "memberId": -3,
        "cameraId": -1,
        "permission": -1,
        "phoneNumber": "",
        "companyName": "",
        "direction": -1,
        "plate_types": [0,1,2,3,4,5,6,7],
        "price_types": [],
        "vehicle_classes": [],
        "vehicle_types": [],
        "update_statuses": [],
        "corrupt_statuses": [],
        "vehicle_colors": [],
        "export_options": [0,1,2,3,4,5,6,8,9,10,11,12],
        "parking_statuses": [],
        "min_vehicle_class_conf": 0,
        "min_vehicle_type_conf": 0,
        "min_vehicle_color_conf": 0,
        "min_vision_speed": -180,
        "max_vision_speed": 180,
        "show_vision_speed": false,
        "show_null_speed": true,
        "min_lane": 0,
        "max_lane": 5,
        "min_ocr": 0,
        "max_ocr": 1,
        "show_lane": true,
        "min_radar_speed": 0,
        "max_radar_speed": 180,
        "show_radar_speed": false,
        "search_not_legible": false,
        "export_crop_image": true,
        "export_full_image": false,
        "max_count": 1000,
        "can_delete": false,
        "can_verify": true,
        "show_legible_option": true,
        "is_verified": null,
        "is_rejected": null,
        "plate_search_list": [],
        "verify_status": 0,
        "owner_id": -1,
        "permissionId": [],
        "allMemberIds": [],
        "description_list": [],
        "plate": [null,null,null,null,null,null,null,null]
      },
      "page": 1,
      "pageSize": 550888
    }
    """.formatted(minId);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            System.out.println("Status Code: " + response.getStatusCode());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // تبدیل JSON به لیست LogEntryDto
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode resultsNode = root.get("results");

                List<LogEntryDto> logs = new ArrayList<>();
                if (resultsNode != null && resultsNode.isArray()) {
                    for (JsonNode item : resultsNode) {
                        logs.add(new LogEntryDto(
                                item.path("plate_char").asText(""),
                                item.path("insert_time").asText(""),
                                item.path("first_name").asText(""),
                                item.path("last_name").asText(""),
                                item.path("melli_code").asText(""),
                                item.path("company_name").asText(""),
                                item.path("phone_number").asText(""),
                                item.path("direction").asInt(0),
                                item.path("camera_id").asInt(0),
                                item.path("time_epoch_ms").asText("")
                        ));
                    }
                }

                return logs;
            } else {
                System.err.println("Bad response status: " + response.getStatusCode());
                return Collections.emptyList();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
