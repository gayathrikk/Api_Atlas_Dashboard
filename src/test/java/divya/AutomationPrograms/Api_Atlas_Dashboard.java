package divya.AutomationPrograms;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.LinkedHashMap;
import java.util.Map;

public class Api_Atlas_Dashboard {

    private static final Map<String, String> ENDPOINT_LABELS = new LinkedHashMap<>();

    static {
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/querySectionAnnotateData", "Atlas Annotation");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/updateSectionAnnotateData", "RUN");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/querySectionAnnotateData?bio=213&onto=189", "fb62");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/querySectionAnnotateData?bio=244&onto=189", "fb85");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/querySectionAnnotateData?bio=222&onto=189", "fb74");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/querySectionAnnotateData?bio=142&onto=189", "fb34");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/querySectionAnnotateData?bio=141&onto=189", "fb40");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/GW/getBrainViewerDetails/IIT/V1/SS-94:274:28", "openButton");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/atlas/compareAtlasRegion", "CompareButton");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/dashboard/secByRegion", "secbyregion");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/atlas/get_Atlasviewer", "Get_Atlasviewer");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/atlas/growthShrinkage", "growthShrinkage");
        ENDPOINT_LABELS.put("https://apollo2.humanbrain.in/atlas/compareAtlasRegion", "Analyze_region");

    }

    @Test
    public void testAPIs() {
        for (String endpoint : ENDPOINT_LABELS.keySet()) {
            String endpointLabel = ENDPOINT_LABELS.get(endpoint);
            Response response;

            // Check if endpoint is for POST request
            if (isPostEndpoint(endpointLabel)) {
                response = sendPostRequest(endpoint);
            } else {
                response = sendGetRequest(endpoint);
            }

            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                System.out.println("API request to " + endpointLabel + " passed. Status code: " + statusCode);
            } else {
                System.out.println("API request to " + endpointLabel + " failed. Status code: " + statusCode);
            }

            Assert.assertEquals(statusCode, 200, "API request to " + endpointLabel + " failed");
        }
    }

    private boolean isPostEndpoint(String endpointLabel) {
        // Define the labels that correspond to POST requests
        return endpointLabel.equals("CompareButton") ||
                endpointLabel.equals("secbyregion") ||
                endpointLabel.equals("Get_Atlasviewer") ||
                endpointLabel.equals("growthShrinkage")||
                endpointLabel.equals("Analyze_region");
    }

    private Response sendPostRequest(String endpoint) {
        String requestBody = "{\"key\": \"value\"}";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(endpoint);
    }

    private Response sendGetRequest(String endpoint) {
        return RestAssured.get(endpoint);
    }
}
