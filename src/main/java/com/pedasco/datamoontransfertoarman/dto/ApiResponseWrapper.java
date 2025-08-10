package com.pedasco.datamoontransfertoarman.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiResponseWrapper {
    private List<Map<String, Object>> results;
    private String count;

    public ApiResponseWrapper(List<Map<String, Object>> results, String count) {
        this.results = results;
        this.count = count;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
