package br.com.casproject.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.casproject.deserializer.SubServicoDeserializer;
import br.com.casproject.modelo.SubServico;

public class SubServicoConverter {

    public static List<SubServico> converterJson(String resp) {

        List<SubServico> subServicos = new ArrayList<SubServico>();

        String code = "100"; // json error code, "0" => everything is o.k.

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(resp);
            code = jsonObject.getString("code");
        } catch (JSONException e) {
            Log.e("ERRO", "error in getArrayFromJSON " + e.getMessage());
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

                        final GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.registerTypeAdapter(SubServico.class, new SubServicoDeserializer());
                        final Gson gson = gsonBuilder.create();

                        final SubServico jf = gson.fromJson(fields.toString(), SubServico.class);
                        subServicos.add(jf);
                    }
                }
                //Log.e("RESPOSTA", String.valueOf(subServicos.get(0).getName()));
            } catch (JSONException e) {
                Log.e("ERRO", "objects = null in JSON response.");
            }


        }

        return subServicos;
    }
}
