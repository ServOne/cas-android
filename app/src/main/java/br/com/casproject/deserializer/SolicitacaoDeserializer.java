package br.com.casproject.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.casproject.modelo.Solicitacao;

public class SolicitacaoDeserializer implements JsonDeserializer<Solicitacao> {

    @Override
    public Solicitacao deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final Solicitacao solicitacao = new Solicitacao();

        final String operational_status = jsonObject.get("operational_status").getAsString();
        solicitacao.setOperational_status(operational_status);

        final String ref = jsonObject.get("ref").getAsString();
        solicitacao.setRef(ref);

        final String org_id = jsonObject.get("org_id").getAsString();
        solicitacao.setOrg_id(org_id);

        final String org_name = jsonObject.get("org_name").getAsString();
        solicitacao.setOrg_name(org_name);

        final String caller_id = jsonObject.get("caller_id").getAsString();
        solicitacao.setCaller_id(caller_id);

        final String caller_name = jsonObject.get("caller_name").getAsString();
        solicitacao.setCaller_name(caller_name);

        final String title = jsonObject.get("title").getAsString();
        solicitacao.setTitle(title);

        final String description = jsonObject.get("description").getAsString().replace("<p>", "").replace("</p>", "");
        solicitacao.setDescription(description);

        final String start_date = jsonObject.get("start_date").getAsString();
        solicitacao.setStart_date(start_date);

        final String end_date = jsonObject.get("end_date").getAsString();
        solicitacao.setEnd_date(end_date);

        final String last_update = jsonObject.get("last_update").getAsString();
        solicitacao.setLast_update(last_update);

        final String close_date = jsonObject.get("close_date").getAsString();
        solicitacao.setClose_date(close_date);

        final String status = jsonObject.get("status").getAsString();
        solicitacao.setStatus(status);

        final String request_type = jsonObject.get("request_type").getAsString();
        solicitacao.setRequest_type(request_type);

        final String impact = jsonObject.get("impact").getAsString();
        solicitacao.setImpact(impact);

        final String priority = jsonObject.get("priority").getAsString();
        solicitacao.setPriority(priority);

        final String urgency = jsonObject.get("urgency").getAsString();
        solicitacao.setUrgency(urgency);

        final String origin = jsonObject.get("origin").getAsString();
        solicitacao.setOrigin(origin);

        final String approver_id = jsonObject.get("approver_id").getAsString();
        solicitacao.setApprover_id(approver_id);

        final String approver_email = jsonObject.get("approver_email").getAsString();
        solicitacao.setApprover_email(approver_email);

        final String service_id = jsonObject.get("service_id").getAsString();
        solicitacao.setService_id(service_id);

        final String service_name = jsonObject.get("service_name").getAsString();
        solicitacao.setService_name(service_name);

        final String servicesubcategory_id = jsonObject.get("servicesubcategory_id").getAsString();
        solicitacao.setServicesubcategory_id(servicesubcategory_id);

        final String servicesubcategory_name = jsonObject.get("servicesubcategory_name").getAsString();
        solicitacao.setServicesubcategory_name(servicesubcategory_name);

        final String caller_id_friendlyname = jsonObject.get("caller_id_friendlyname").getAsString();
        solicitacao.setCaller_id_friendlyname(caller_id_friendlyname);


        return solicitacao;
    }
}
