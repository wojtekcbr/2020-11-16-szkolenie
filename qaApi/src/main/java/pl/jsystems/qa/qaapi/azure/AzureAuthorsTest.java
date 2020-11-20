package pl.jsystems.qa.qaapi.azure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.model.azure.AzureAuthor;
import pl.jsystems.qa.qaapi.service.AzureService;
import static com.google.common.truth.Truth.assertThat;

import java.util.List;

@Tags({@Tag("API"), @Tag("Azure")})
@DisplayName("Azure authors test")
public class AzureAuthorsTest {

    @DisplayName("Get azure authors.")
    @Test
    public void azureAuthors() {

        //when
        List<AzureAuthor> azureAuthors = AzureService.getAzureAuthors();

        //then
        assertThat(azureAuthors.get(0).id).isEqualTo(1);
        assertThat(azureAuthors.get(0).id).isGreaterThan(0);
        assertThat(azureAuthors.get(0).firstName).isEqualTo("First Name " + azureAuthors.get(0).id);
        assertThat(azureAuthors.get(0).lastName).isEqualTo("Last Name " + azureAuthors.get(0).id);


        for (AzureAuthor azureAuthor : azureAuthors) {
            assertThat(azureAuthor.firstName).isEqualTo("First Name " + azureAuthor.id);
            assertThat(azureAuthor.lastName).isEqualTo("Last Name " + azureAuthor.id);
        }

    }
}
