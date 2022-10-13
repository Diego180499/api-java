package com.diego.api.client.whatsapp.dto.request;

public class MessageDefaultDTO {

    //private String messaging_product;
    private String to;
    //private String type;
    //private TemplateDTO template;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "{\n"
                + "    \"messaging_product\": \"whatsapp\",\n"
                + "    \"to\": \"" + to + "\",\n"
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
