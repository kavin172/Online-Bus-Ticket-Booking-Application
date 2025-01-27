package Project.BusTicketBooking.Application;

import Project.BusTicketBooking.Application.Service.GeneratePdfService;
import com.lowagie.text.DocumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GeneratePdfServiceTest {

    @Mock
    private TemplateEngine templateEngine;

    @Mock
    private ITextRenderer iTextRenderer;

    @InjectMocks
    private GeneratePdfService generatePdfService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGeneratePdfFile_throwsRuntimeException_whenDocumentException() throws Exception {
        // Given
        String templateName = "testTemplate";
        Map<String, Object> data = new HashMap<>();
        String pdfFileName = "testOutput.pdf";

        // Mock the TemplateEngine to return some HTML content
        when(templateEngine.process(templateName, new Context())).thenReturn("<html><body><h1>Test PDF</h1></body></html>");

        // Mock the behavior of ITextRenderer to throw a DocumentException
        doThrow(new DocumentException("Error while creating PDF")).when(iTextRenderer).createPDF(any(FileOutputStream.class), eq(false));

        // When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            generatePdfService.generatePdfFile(templateName, data, pdfFileName);
        });

        // Verify the exception message
        assertEquals("File to Generate pdf:Cannot invoke \"String.length()\" because \"s\" is null", thrown.getMessage());
    }
}
