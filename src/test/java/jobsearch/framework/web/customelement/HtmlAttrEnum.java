package jobsearch.framework.web.customelement;

public enum HtmlAttrEnum {
    ARIA_HIDDEN ("aria-hidden"),
    CLASS ("class"),
    ID ("id");

    HtmlAttrEnum(String name){
        this.name = name;
    }
    String name;

    @Override
    public String toString() {
        return name;
    }
}
