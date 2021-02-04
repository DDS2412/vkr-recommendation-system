package vkr.enums;

public enum CategoryEnum {
    CONCERT("concert"),
    FESTIVAL("festival"),
    EXHIBITION("exhibition"),
    TOUR("tour");

    private String value;

    CategoryEnum(String value){
        this.value = value;
    }

    public String getValue() { return this.value; }
}
