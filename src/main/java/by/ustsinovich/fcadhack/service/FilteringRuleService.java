package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.dto.FilteringRuleDto;
import by.ustsinovich.fcadhack.entity.FilteringRuleEntity;
import by.ustsinovich.fcadhack.mapper.FilteringRuleMapper;
import by.ustsinovich.fcadhack.repository.FilteringRuleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class FilteringRuleService{

    private final FilteringRuleMapper filteringRuleMapper;

    private final FilteringRuleRepository filteringRuleRepository;

    private final ObjectMapper objectMapper;

    public Page<FilteringRuleDto> getList(Pageable pageable) {
        Page<FilteringRuleEntity> filteringRuleEntities = filteringRuleRepository.findAll(pageable);
        return filteringRuleEntities.map(filteringRuleMapper::toDto);
    }

    public FilteringRuleDto getOne(Long id) {
        Optional<FilteringRuleEntity> filteringRuleEntityOptional = filteringRuleRepository.findById(id);
        return filteringRuleMapper.toDto(filteringRuleEntityOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<FilteringRuleDto> getMany(List<Long> ids) {
        List<FilteringRuleEntity> filteringRuleEntities = filteringRuleRepository.findAllById(ids);
        return filteringRuleEntities.stream()
                .map(filteringRuleMapper::toDto)
                .toList();
    }

    public FilteringRuleDto create(FilteringRuleDto dto) {
        FilteringRuleEntity filteringRuleEntity = filteringRuleMapper.toEntity(dto);
        FilteringRuleEntity resultFilteringRuleEntity = filteringRuleRepository.save(filteringRuleEntity);
        return filteringRuleMapper.toDto(resultFilteringRuleEntity);
    }

    public FilteringRuleDto patch(Long id, JsonNode patchNode) throws IOException {
        FilteringRuleEntity filteringRuleEntity = filteringRuleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        FilteringRuleDto filteringRuleDto = filteringRuleMapper.toDto(filteringRuleEntity);
        objectMapper.readerForUpdating(filteringRuleDto).readValue(patchNode);
        filteringRuleMapper.updateWithNull(filteringRuleDto, filteringRuleEntity);

        FilteringRuleEntity resultFilteringRuleEntity = filteringRuleRepository.save(filteringRuleEntity);
        return filteringRuleMapper.toDto(resultFilteringRuleEntity);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<FilteringRuleEntity> filteringRuleEntities = filteringRuleRepository.findAllById(ids);

        for (FilteringRuleEntity filteringRuleEntity : filteringRuleEntities) {
            FilteringRuleDto filteringRuleDto = filteringRuleMapper.toDto(filteringRuleEntity);
            objectMapper.readerForUpdating(filteringRuleDto).readValue(patchNode);
            filteringRuleMapper.updateWithNull(filteringRuleDto, filteringRuleEntity);
        }

        List<FilteringRuleEntity> resultFilteringRuleEntities = filteringRuleRepository.saveAll(filteringRuleEntities);
        return resultFilteringRuleEntities.stream()
                .map(FilteringRuleEntity::getId)
                .toList();
    }

    public FilteringRuleDto delete(Long id) {
        FilteringRuleEntity filteringRuleEntity = filteringRuleRepository.findById(id).orElse(null);
        if (filteringRuleEntity != null) {
            filteringRuleRepository.delete(filteringRuleEntity);
        }
        return filteringRuleMapper.toDto(filteringRuleEntity);
    }

    public void deleteMany(List<Long> ids) {
        filteringRuleRepository.deleteAllById(ids);
    }

    public List<FilteringRuleDto> getEnabled() {
        return filteringRuleRepository.findAllByStatus(true)
                .stream()
                .map(filteringRuleMapper::toDto)
                .toList();
    }

}
