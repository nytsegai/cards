package jobsearch.framework.web.customelement;

public class WebElementsTypes {
    private WebElementType type;

    public WebElementsTypes(String str) {
        switch (str) {
            case "TEXTBOX": {
                this.type = WebElementType.TEXTBOX;
                break;
            }
            case "TEXT": {
                this.type = WebElementType.TEXT;
                break;
            }
            case "INPUT": {
                this.type = WebElementType.INPUT;
                break;
            }
            case "BUTTON": {
                this.type = WebElementType.BUTTON;
                break;
            }
            case "HIDDEN": {
                this.type = WebElementType.HIDDEN;
                break;
            }
            case "PASSWORD": {
                this.type = WebElementType.PASSWORD;
                break;
            }
            case "RADIO": {
                this.type = WebElementType.RADIO;
                break;
            }
            case "SUBMIT": {
                this.type = WebElementType.SUBMIT;
                break;
            }
            case "RESET": {
                this.type = WebElementType.RESET;
                break;
            }
            case "IMAGE": {
                this.type = WebElementType.IMAGE;
                break;
            }
            case "SELECT": {
                this.type = WebElementType.SELECT;
                break;
            }
            case "FILE": {
                this.type = WebElementType.FILE;
                break;
            }
            case "COMBOBOX": {
                this.type = WebElementType.COMBOBOX;
                break;
            }
            case "CHECKBOX": {
                this.type = WebElementType.CHECKBOX;
                break;
            }
            case "TEXTAREA": {
                this.type = WebElementType.TEXTAREA;
                break;
            }
            case "DATEPICKER": {
                this.type = WebElementType.DATEPICKER;
                break;
            }
            case "SEARCH_CMB": {
                this.type = WebElementType.SEARCH_CMB;
                break;
            }
            case "SEARCH_TXB": {
                this.type = WebElementType.SEARCH_TXB;
                break;
            }
        }
    }

    public WebElementType getType() {
        return type;
    }

    public enum WebElementType {
        TEXTBOX,
        TEXT,
        BUTTON,
        HIDDEN,
        PASSWORD,
        RADIO,
        SUBMIT,
        RESET,
        IMAGE,
        SELECT,
        FILE,
        COMBOBOX,
        CHECKBOX,
        TEXTAREA,
        DATEPICKER,
        SEARCH_CMB,
        SEARCH_TXB,
        INPUT
    }
}
