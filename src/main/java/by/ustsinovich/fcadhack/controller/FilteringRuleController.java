package by.ustsinovich.fcadhack.controller;

import by.ustsinovich.fcadhack.dto.FilteringRuleDto;
import by.ustsinovich.fcadhack.service.FilteringRuleService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/filtering-rules")
@RequiredArgsConstructor
public class FilteringRuleController {

    private final FilteringRuleService filteringRuleService;

    @GetMapping
    public Page<FilteringRuleDto> getList(Pageable pageable) {
        return filteringRuleService.getList(pageable);
    }

    @GetMapping("/{id}")
    public FilteringRuleDto getOne(@PathVariable Long id) {
        return filteringRuleService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<FilteringRuleDto> getMany(@RequestParam List<Long> ids) {
        return filteringRuleService.getMany(ids);
    }

    @PostMapping
    public FilteringRuleDto create(@RequestBody FilteringRuleDto dto) {
        return filteringRuleService.create(dto);
    }

    @PatchMapping("/{id}")
    public FilteringRuleDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return filteringRuleService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return filteringRuleService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public FilteringRuleDto delete(@PathVariable Long id) {
        return filteringRuleService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        filteringRuleService.deleteMany(ids);
    }
}
