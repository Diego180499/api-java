package com.diego.api.dto.whatsapp_manager.message_default;

public class RequestDTO {

    private String messaging_product;
    private String to;
    private String type;
    private TemplateDTO template;

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TemplateDTO getTemplate() {
        return template;
    }

    public void setTemplate(TemplateDTO template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "{\n"
                + "    \"messaging_product\": \"whatsapp\",\n"
                + "    \"to\": \""+to+"\",\n"
                + "    \"type\": \"template\",\n"
                + "    \"template\": {\n"
                + "        \"name\": \"hello_world\",\n"
                + "        \"language\": {\n"
                + "            \"code\": \"en_US\"\n"
                + "        }\n"
                + "    }\n"
                + "}";
    }

}
