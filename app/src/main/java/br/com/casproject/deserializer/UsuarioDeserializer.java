package br.com.casproject.deserializer;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.casproject.modelo.Usuario;

public class UsuarioDeserializer implements JsonDeserializer<Usuario> {

    @Override
    public Usuario deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final Usuario usuario = new Usuario();

        final String name = jsonObject.get("name").getAsString();
        usuario.setName(name);

        final String status = jsonObject.get("status").getAsString();
        usuario.setStatus(status);

        final String email = jsonObject.get("email").getAsString();
        usuario.setEmail(email);

        final String phone = jsonObject.get("phone").getAsString();
        usuario.setPhone(phone);

        final JsonElement jsonPictureObject = jsonObject.get("picture");

        final String datapicture = jsonPictureObject.getAsJsonObject().get("data").getAsString();
        usuario.setDatapicture(datapicture);

        final String mimetype = jsonPictureObject.getAsJsonObject().get("mimetype").getAsString();
        usuario.setMimetype(mimetype);

        final String org_id = jsonObject.get("org_id").getAsString();
        usuario.setOrg_id(org_id);

        return usuario;
    }
}
