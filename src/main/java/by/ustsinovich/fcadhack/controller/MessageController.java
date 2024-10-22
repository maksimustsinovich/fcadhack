package by.ustsinovich.fcadhack.controller;

import by.ustsinovich.fcadhack.dto.MessageDto;
import by.ustsinovich.fcadhack.entity.Message;
import by.ustsinovich.fcadhack.service.MessageService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public Page<MessageDto> getList(@ParameterObject Pageable pageable) {
        return messageService.getList(pageable);
    }

    @GetMapping("/{id}")
    public MessageDto getOne(@PathVariable Long id) {
        return messageService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<MessageDto> getMany(@RequestParam List<Long> ids) {
        return messageService.getMany(ids);
    }

    @PostMapping
    public MessageDto create(@RequestBody MessageDto dto) {
        return messageService.create(dto);
    }

    @PatchMapping("/{id}")
    public MessageDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return messageService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Message> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return messageService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public MessageDto delete(@PathVariable Long id) {
        return messageService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        messageService.deleteMany(ids);
    }
}
