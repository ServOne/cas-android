package br.com.casproject.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import br.com.casproject.deserializer.UsuarioDeserializer;
import br.com.casproject.modelo.Usuario;


public class UsuarioConverter {

    public static Usuario converterJson(String resp) {

        Usuario usuario = new Usuario();

        String code = "100"; // json error code, "0" => everything is o.k.

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(resp);
            code = jsonObject.getString("code");
        } catch (JSONException e) {
            Log.e("ERRO", "error in getArrayFromJSON " + e.getMessage());
            usuario = null;
        }

        if ((jsonObject != null) && (code.trim().equals("0"))) {
            try {

                JSONObject objects = jsonObject.getJSONObject("objects");
                Iterator<?> keys = objects.keys();

                while (keys.hasNext()) {

                    String key = (String) keys.next();
                    if (objects.get(key) instanceof JSONObject) {
                        JSONObject o = (JSONObject) objects.get(key);
                        int id = o.getInt("key");
                        JSONObject fields = o.getJSONObject("fields");
//                        Log.e("RESPOSTA", fields.toString());
                        final GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.registerTypeAdapter(Usuario.class, new UsuarioDeserializer());
                        final Gson gson = gsonBuilder.create();

                        usuario = gson.fromJson(fields.toString(), Usuario.class);
                        usuario.setID(String.valueOf(id));
                    }
                }
                //Log.e("RESPOSTA", String.valueOf(servicos.get(0).getName()));
            } catch (JSONException e) {
                Log.e("ERRO", "objects = null in JSON response.");
                usuario = null;
            }


        } else {
            usuario = null;
        }

        return usuario;
    }
}
