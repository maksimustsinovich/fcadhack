package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.dto.MessageDto;
import by.ustsinovich.fcadhack.entity.Message;
import by.ustsinovich.fcadhack.mapper.MessageMapper;
import by.ustsinovich.fcadhack.repository.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;

    private final MessageRepository messageRepository;

    private final ObjectMapper objectMapper;


    public Page<MessageDto> getList(Pageable pageable) {
        Page<Message> messages = messageRepository.findAll(pageable);
        return messages.map(messageMapper::toDto);
    }

    public MessageDto getOne(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        return messageMapper.toDto(messageOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<MessageDto> getMany(List<Long> ids) {
        List<Message> messages = messageRepository.findAllById(ids);
        return messages.stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public MessageDto create(MessageDto dto) {
        Message message = messageMapper.toEntity(dto);
        Message resultMessage = messageRepository.save(message);
        return messageMapper.toDto(resultMessage);
    }

    public MessageDto patch(Long id, JsonNode patchNode) throws IOException {
        Message message = messageRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        MessageDto messageDto = messageMapper.toDto(message);
        objectMapper.readerForUpdating(messageDto).readValue(patchNode);
        messageMapper.updateWithNull(messageDto, message);

        Message resultMessage = messageRepository.save(message);
        return messageMapper.toDto(resultMessage);
    }

    public List<Message> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Message> messages = messageRepository.findAllById(ids);

        for (Message message : messages) {
            MessageDto messageDto = messageMapper.toDto(message);
            objectMapper.readerForUpdating(messageDto).readValue(patchNode);
            messageMapper.updateWithNull(messageDto, message);
        }

        return messageRepository.saveAll(messages);
    }

    public MessageDto delete(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            messageRepository.delete(message);
        }
        return messageMapper.toDto(message);
    }

    public void deleteMany(List<Long> ids) {
        messageRepository.deleteAllById(ids);
    }
}
