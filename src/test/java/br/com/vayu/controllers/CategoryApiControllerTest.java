package br.com.vayu.controllers;

import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.CourseBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.repositories.CategoryRepository;
import br.com.vayu.repositories.CourseRepository;
import br.com.vayu.repositories.SubcategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final String code = "code";
    private final String name = "name";
    private final String description = "description";
    private final String studyGuide = "studyGuide";
    private final String iconPath = "iconPath";
    private final String colorCode = "#FFF";

    @AfterEach
    void afterEach() {
        categoryRepository.deleteAll();
    }

    @Test
    void getCategoryApiDtoList__should_return_200() throws Exception {
        mockMvc
                .perform(get(new URI("/api/categories"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCategoryApiDtoList__should_return_correct_content() throws Exception {
        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .iconPath(iconPath)
                .colorCode(colorCode)
                .build();

        categoryRepository.save(category);

        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(category)
                .build();

        subcategoryRepository.save(subcategory);

        Course course = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(true)
                .targetAudience("targetAudience")
                .instructorName("instructorName")
                .syllabus("syllabus")
                .developedAbilities("developedAbilities")
                .subcategory(subcategory)
                .build();

        courseRepository.save(course);

        mockMvc
                .perform(get(new URI("/api/categories"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].code", is(code)))
                .andExpect(jsonPath("$[0].order", is(0)))
                .andExpect(jsonPath("$[0].colorCode", is(colorCode)))
                .andExpect(jsonPath("$[0].studyGuide", is(studyGuide)))
                .andExpect(jsonPath("$[0].totalOfCourses", is(1)))
                .andExpect(jsonPath("$[0].subcategories", hasSize(1)))
                .andExpect(jsonPath("$[0].subcategories[0].name", is(name)))
                .andExpect(jsonPath("$[0].subcategories[0].code", is(code)))
                .andExpect(jsonPath("$[0].subcategories[0].studyGuide", is(studyGuide)))
                .andExpect(jsonPath("$[0].subcategories[0].courses", hasSize(1)))
                .andExpect(jsonPath("$[0].subcategories[0].courses[0].name", is(name)))
                .andExpect(jsonPath("$[0].subcategories[0].courses[0].code", is(code)))
                .andExpect(jsonPath("$[0].subcategories[0].courses[0].estimatedHoursToFinish", is(1)))
                .andExpect(jsonPath("$[0].subcategories[0].courses[0].developedAbilities", is("developedAbilities")));
    }

    @Test
    void getCategoryApiDtoList__should_return_empty_array() throws Exception {
                mockMvc
                .perform(get(new URI("/api/categories"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

}