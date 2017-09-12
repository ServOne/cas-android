
package br.com.casproject.modelo;


import java.io.Serializable;

public class Solicitacao implements Serializable {
    private static final long serialVersionUID = -5998434779602343501L;
    private String operational_status;
    private String ref;
    private String org_id;
    private String org_name;
    private String caller_id;
    private String caller_name;
    private String title;
    private String description;
    private String start_date;
    private String end_date;
    private String last_update;
    private String close_date;
    private String status;
    private String request_type;
    private String impact;
    private String priority;
    private String urgency;
    private String origin;
    private String approver_id;
    private String approver_email;
    private String service_id;
    private String service_name;
    private String servicesubcategory_id;
    private String servicesubcategory_name;
    private String caller_id_friendlyname;

    public String getOperational_status() {
        return operational_status;
    }

    public void setOperational_status(String operational_status) {
        this.operational_status = operational_status;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getCaller_id() {
        return caller_id;
    }

    public void setCaller_id(String caller_id) {
        this.caller_id = caller_id;
    }

    public String getCaller_name() {
        return caller_name;
    }

    public void setCaller_name(String caller_name) {
        this.caller_name = caller_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(String approver_id) {
        this.approver_id = approver_id;
    }

    public String getApprover_email() {
        return approver_email;
    }

    public void setApprover_email(String approver_email) {
        this.approver_email = approver_email;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getServicesubcategory_id() {
        return servicesubcategory_id;
    }

    public void setServicesubcategory_id(String servicesubcategory_id) {
        this.servicesubcategory_id = servicesubcategory_id;
    }

    public String getServicesubcategory_name() {
        return servicesubcategory_name;
    }

    public void setServicesubcategory_name(String servicesubcategory_name) {
        this.servicesubcategory_name = servicesubcategory_name;
    }

    public String getCaller_id_friendlyname() {
        return caller_id_friendlyname;
    }

    public void setCaller_id_friendlyname(String caller_id_friendlyname) {
        this.caller_id_friendlyname = caller_id_friendlyname;
    }


}
