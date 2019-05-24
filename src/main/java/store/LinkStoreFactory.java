package store;

public class LinkStoreFactory {

    private static LinkStore instance;
    static {
    	CloudantLinkStore cvif = new CloudantLinkStore();
        if (cvif.getDB() != null) {
            instance = cvif;
        }      
    }

    public static LinkStore getInstance() {
        return instance;
    }
}
