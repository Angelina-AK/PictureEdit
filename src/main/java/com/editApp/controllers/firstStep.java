package com.editApp.controllers;


import com.editApp.objects.FirstObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//--------------------------------------------------------------------------------
//   Контроллер запросов для первого шага работы - формирования исх. изображения
//--------------------------------------------------------------------------------
@RestController
@RequestMapping("/firstStep")
public class firstStep {


    @PostMapping(value = "/getPicture")
    public ResponseEntity<FirstObject> makeOne(@RequestBody FirstObject firstObject) {
        // какие-то дела с Джэйсоном
        return ResponseEntity.ok(firstObject);
    }

}
