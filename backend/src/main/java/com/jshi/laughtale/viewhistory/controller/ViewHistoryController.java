package com.jshi.laughtale.viewhistory.controller;

import com.jshi.laughtale.viewhistory.service.ViewHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class  ViewHistoryController {

    private final ViewHistoryService viewHistoryService;
}
