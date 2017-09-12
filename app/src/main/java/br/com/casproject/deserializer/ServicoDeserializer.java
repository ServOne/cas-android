package br.com.casproject.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.casproject.modelo.Servico;


public class ServicoDeserializer implements JsonDeserializer<Servico> {

    @Override
    public Servico deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final Servico servico = new Servico();

        final String id = jsonObject.get("id").getAsString();
        servico.setId(id);

        final JsonElement jsonTitle = jsonObject.get("name");
        final String name = jsonTitle.getAsString();
        servico.setName(name);

        return servico;
    }
}
