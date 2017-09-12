package br.com.casproject.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import br.com.casproject.WebService;

public class TaskGenerica extends AsyncTask<String, Void, String> {

    private ProgressDialog dialog;
    private Context context;
    private TaskGenericaResultado taskResultado;
    private String classResultado;

    public TaskGenerica() {
        dialog = null;
    }

    public TaskGenerica(Activity activity) {

        //Verifica se a Activity passada implementa a interface
        if (activity instanceof TaskGenericaResultado) {
            this.taskResultado = (TaskGenericaResultado) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement TaskGenericaResultado");
        }

        this.context = activity;
        dialog = new ProgressDialog(this.context);
    }

    // Interface a ser implementada pela classe consumidora
    public interface TaskGenericaResultado {
        void onResultado(String resultado, String classResultado);
    }

    @Override
    protected void onPreExecute() {
        if (dialog != null) {
            dialog.setMessage("Enviando dados... aguarde");
            dialog.setIndeterminate(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... expr) {
        classResultado = expr[1];
        String resp = "";

        try {
            resp = WebService.postJsonToItopServer(expr[0], expr[1], expr[2], expr[3], expr[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String resp) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }


            taskResultado.onResultado(resp, classResultado);

        }

    }
}
