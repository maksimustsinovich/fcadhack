package by.ustsinovich.fcadhack.controller;

import by.ustsinovich.fcadhack.dto.DataSourceDto;
import by.ustsinovich.fcadhack.service.DataSourceService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/data-sources")
@RequiredArgsConstructor
public class DataSourceController {

    private final DataSourceService dataSourceService;

    @GetMapping
    public Page<DataSourceDto> getList(Pageable pageable) {
        return dataSourceService.getList(pageable);
    }

    @GetMapping("/{id}")
    public DataSourceDto getOne(@PathVariable Long id) {
        return dataSourceService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<DataSourceDto> getMany(@RequestParam List<Long> ids) {
        return dataSourceService.getMany(ids);
    }

    @PostMapping
    public DataSourceDto create(@RequestBody DataSourceDto dto) {
        return dataSourceService.create(dto);
    }

    @PatchMapping("/{id}")
    public DataSourceDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return dataSourceService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return dataSourceService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public DataSourceDto delete(@PathVariable Long id) {
        return dataSourceService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        dataSourceService.deleteMany(ids);
    }
}
