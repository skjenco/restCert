package com.skjenco.app;

import com.skjenco.app.dto.OrgChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class OrgController {
    @Autowired
    OrgService orgService;
    String NOT_FOUND = "No employee with this ID could be found.";

    @RequestMapping("/orgs")
    public ResponseEntity<List<OrgChartDTO>> getOrgChart(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePrivate()).body(orgService.getOrgChart(pageSize, pageNum, sort));
    }
}
