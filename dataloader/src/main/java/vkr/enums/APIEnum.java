package vkr.enums;

public enum APIEnum {
    EVENT_CATEGORIES("https://kudago.com/public-api/v1.4/event-categories/"),
    EVENT("https://kudago.com/public-api/v1.4/events/"),
    PLACE("https://kudago.com/public-api/v1.4/places/");

    private String url;

    APIEnum(String url){
        this.url = url;
    }

    public String getUrl() { return this.url; }
}
