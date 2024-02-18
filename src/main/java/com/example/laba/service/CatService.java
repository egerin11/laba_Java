    package com.example.laba.service;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.client.RestTemplate;

    @org.springframework.stereotype.Service
    public class CatService extends Service {

        private static String catInfApiUrl = "https://catfact.ninja/{action}";

        private final RestTemplate restTemplate;

        @Autowired
        public CatService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @Override
        public String getInf(String action) {
            String url = catInfApiUrl.replace("{action}", action);
            return restTemplate.getForObject(url, String.class);
        }


    }
