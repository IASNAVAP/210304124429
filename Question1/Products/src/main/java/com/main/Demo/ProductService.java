package com.main.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProducts() {

//        String companyName="";
//        String top="";
//        String minPrice="";
//        String maxPrice="";
//        String categoryName="";
//        String url = String.format("http://20.244.56.144/test/companies/%s/categories/%s/products?top=%d&minPrice=%.2f&maxPrice=%.2f",
//                companyName, categoryName, top, minPrice, maxPrice);
//
       String url = "http://20.244.56.144/test/companies/AMZ/categories/Laptop/products?top=10&minPrice=1&maxPrice=10000";

       String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzE4NzgyNzk5LCJpYXQiOjE3MTg3ODI0OTksImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsInN1YiI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4ifSwiY29tcGFueU5hbWUiOiJnb01hcnQiLCJjbGllbnRJRCI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsImNsaWVudFNlY3JldCI6ImtUVHFGakJUd2dPemZhQnMiLCJvd25lck5hbWUiOiJWeWFtYXNhbmkgUGF2YW4gU2FpIiwib3duZXJFbWFpbCI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4iLCJyb2xsTm8iOiIzMzIifQ.G0XZLDYDKa69d9BJluilEQNoJJaYqfOdJEHT3syGly8";
       // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzE4Nzc2Nzk1LCJpYXQiOjE3MTg3NzY0OTUsImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsInN1YiI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4ifSwiY29tcGFueU5hbWUiOiJnb01hcnQiLCJjbGllbnRJRCI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsImNsaWVudFNlY3JldCI6ImtUVHFGakJUd2dPemZhQnMiLCJvd25lck5hbWUiOiJWeWFtYXNhbmkgUGF2YW4gU2FpIiwib3duZXJFbWFpbCI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4iLCJyb2xsTm8iOiIzMzIifQ.MCCsbqhLnl7D8apgFmOB2AD8I2c19QZt4_be2zsyedM";
      //  String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzE4Nzc4MDI5LCJpYXQiOjE3MTg3Nzc3MjksImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsInN1YiI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4ifSwiY29tcGFueU5hbWUiOiJnb01hcnQiLCJjbGllbnRJRCI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsImNsaWVudFNlY3JldCI6ImtUVHFGakJUd2dPemZhQnMiLCJvd25lck5hbWUiOiJWeWFtYXNhbmkgUGF2YW4gU2FpIiwib3duZXJFbWFpbCI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4iLCJyb2xsTm8iOiIzMzIifQ.B8iC1W74n1vMgrxsTv0_qiJftAnT7YLgAt1q7qzCNyE";
      // String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzE4Nzc5MTE1LCJpYXQiOjE3MTg3Nzg4MTUsImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsInN1YiI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4ifSwiY29tcGFueU5hbWUiOiJnb01hcnQiLCJjbGllbnRJRCI6IjljYzFlMmQ0LTYyYzYtNDhmOS04YzMwLWVmNzllMGExNjQ3NSIsImNsaWVudFNlY3JldCI6ImtUVHFGakJUd2dPemZhQnMiLCJvd25lck5hbWUiOiJWeWFtYXNhbmkgUGF2YW4gU2FpIiwib3duZXJFbWFpbCI6IjIxMDMwNDEyNDQyOUBwYXJ1bHVuaXZlcnNpdHkuYWMuaW4iLCJyb2xsTm8iOiIzMzIifQ.NxxDxPIjo9lo_UY64IbLIJlUP-V9xNz9GJQTB-KZPYM";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Log the error response for debugging
            System.err.println("Request failed with status code: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
            return "Request failed: " + e.getStatusCode();
        }
    }
}
