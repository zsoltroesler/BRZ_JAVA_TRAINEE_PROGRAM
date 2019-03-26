package at.gv.brz.roeslerz.inventur.utils;

public class APIUtils {

    private static final String BASE_URL = "your URL";

    private APIUtils() {}

    public static ProduktApiService getProduktService() {
        return ProduktApiClient.getClient(BASE_URL).create(ProduktApiService.class);
    }
}
