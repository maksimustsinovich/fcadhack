package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.dto.DataSourceDto;
import by.ustsinovich.fcadhack.entity.DataSourceEntity;
import by.ustsinovich.fcadhack.mapper.DataSourceMapper;
import by.ustsinovich.fcadhack.repository.DataSourceRepository;
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
public class DataSourceService {

    private final DataSourceMapper dataSourceMapper;

    private final DataSourceRepository dataSourceRepository;

    private final ObjectMapper objectMapper;

    public Page<DataSourceDto> getList(Pageable pageable) {
        Page<DataSourceEntity> dataSourceEntities = dataSourceRepository.findAll(pageable);
        return dataSourceEntities.map(dataSourceMapper::toDto);
    }

    public DataSourceDto getOne(Long id) {
        Optional<DataSourceEntity> dataSourceEntityOptional = dataSourceRepository.findById(id);
        return dataSourceMapper.toDto(dataSourceEntityOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<DataSourceDto> getMany(List<Long> ids) {
        List<DataSourceEntity> dataSourceEntities = dataSourceRepository.findAllById(ids);
        return dataSourceEntities.stream()
                .map(dataSourceMapper::toDto)
                .toList();
    }

    public DataSourceDto create(DataSourceDto dto) {
        DataSourceEntity dataSourceEntity = dataSourceMapper.toEntity(dto);
        DataSourceEntity resultDataSourceEntity = dataSourceRepository.save(dataSourceEntity);
        return dataSourceMapper.toDto(resultDataSourceEntity);
    }

    public DataSourceDto patch(Long id, JsonNode patchNode) throws IOException {
        DataSourceEntity dataSourceEntity = dataSourceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        DataSourceDto dataSourceDto = dataSourceMapper.toDto(dataSourceEntity);
        objectMapper.readerForUpdating(dataSourceDto).readValue(patchNode);
        dataSourceMapper.updateWithNull(dataSourceDto, dataSourceEntity);

        DataSourceEntity resultDataSourceEntity = dataSourceRepository.save(dataSourceEntity);
        return dataSourceMapper.toDto(resultDataSourceEntity);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<DataSourceEntity> dataSourceEntities = dataSourceRepository.findAllById(ids);

        for (DataSourceEntity dataSourceEntity : dataSourceEntities) {
            DataSourceDto dataSourceDto = dataSourceMapper.toDto(dataSourceEntity);
            objectMapper.readerForUpdating(dataSourceDto).readValue(patchNode);
            dataSourceMapper.updateWithNull(dataSourceDto, dataSourceEntity);
        }

        List<DataSourceEntity> resultDataSourceEntities = dataSourceRepository.saveAll(dataSourceEntities);
        return resultDataSourceEntities.stream()
                .map(DataSourceEntity::getId)
                .toList();
    }

    public DataSourceDto delete(Long id) {
        DataSourceEntity dataSourceEntity = dataSourceRepository.findById(id).orElse(null);
        if (dataSourceEntity != null) {
            dataSourceRepository.delete(dataSourceEntity);
        }
        return dataSourceMapper.toDto(dataSourceEntity);
    }

    public void deleteMany(List<Long> ids) {
        dataSourceRepository.deleteAllById(ids);
    }
}
