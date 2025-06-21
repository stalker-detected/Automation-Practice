package api.pet;

import api.BaseTestAPI;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.openapitools.client.model.Category;
import org.openapitools.client.model.Pet;
import org.openapitools.client.model.Tag;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PetPostTests extends BaseTestAPI {
    @Epic(value = "API")
    @Test
    public void checkAddingNewPet() {
        Long id = Long.parseLong(faker.number().digits(8));

        Category category = new Category();
        category.id(Long.parseLong(faker.number().digits(5)));
        category.name("dog");

        String name = faker.animal().name();
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(0, faker.internet().image());

        Tag tag = new Tag();
        tag.id(Long.parseLong(faker.number().digits(3)));
        tag.name(faker.country().name());
        List<Tag> tags = new ArrayList<>();
        tags.add(0, tag);

        Pet pet = new Pet();
        pet.id(id);
        pet.category(category);
        pet.name(name);
        pet.photoUrls(photoUrls);
        pet.tags(tags);

        Response response = createPetApi().addPet()
                .body(pet)
                .execute(getHandler());
        //System.out.println(response.asString());

        assertEquals(response.statusCode(), 200, INCORRECT_STATUS_CODE_MESSAGE_200);
        Pet petResponse = response.as(Pet.class);
        assertEquals(petResponse, pet, "Request body is different from response");
    }
}
