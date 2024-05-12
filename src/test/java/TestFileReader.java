import com.wellknownorg.coding.challenge.file.FileParser;
import com.wellknownorg.coding.challenge.file.FileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class TestFileReader {

    @Mock
    private FileParser fileParser;

    @InjectMocks
    private FileReader unitUnderTest;

    @Test
    public void shouldNotParseFile_givenFileDoesNotExists() {
        unitUnderTest.readFile("test.txt");

        verifyNoInteractions(fileParser);
    }

    @Test
    public void shouldParseFile_givenFileExists() {
        unitUnderTest.readFile("test.csv");

        verify(fileParser).parse("test");
    }
}