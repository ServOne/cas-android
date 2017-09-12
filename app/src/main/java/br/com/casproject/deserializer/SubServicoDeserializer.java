package br.com.casproject.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.casproject.modelo.SubServico;

public class SubServicoDeserializer implements JsonDeserializer<SubServico> {

    @Override
    public SubServico deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final SubServico subServico = new SubServico();

        final String id = jsonObject.get("id").getAsString();
        subServico.setId(id);

        final JsonElement jsonTitle = jsonObject.get("name");
        final String name = jsonTitle.getAsString();
        subServico.setName(name);

        final String description = jsonObject.get("description").getAsString();
        subServico.setDescription(description);

        final String service_id = jsonObject.get("service_id").getAsString();
        subServico.setService_id(service_id);

        final String service_name = jsonObject.get("service_name").getAsString();
        subServico.setService_name(service_name);

        final String request_type = jsonObject.get("request_type").getAsString();
        subServico.setRequest_type(request_type);

        return subServico;
    }
}
