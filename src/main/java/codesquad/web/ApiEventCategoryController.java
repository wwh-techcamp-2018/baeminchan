package codesquad.web;

import codesquad.domain.EventCategory;
import codesquad.dto.EventCategoryDto;
import codesquad.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventcategories")
public class ApiEventCategoryController {


    @Autowired
    EventCategoryService eventCategoryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<EventCategory> readEventCategories() {
        return eventCategoryService.readEventCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventCategoryDto readEventCategory(@PathVariable Long id) {
        return eventCategoryService.readEventCategory(id);
    }
}
