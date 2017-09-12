// Copyright (C) 2011-2013 ITOMIG GmbH
//
//   This file is part of iTopEnterprise.
//
//   iTopEnterprise is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation, either version 3 of the License, or
//   (at your option) any later version.
//
//   iTopEnterprise is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//
//   You should have received a copy of the GNU General Public License
//   along with iTopEnterprise. If not, see <http://www.gnu.org/licenses/>

//   Google-GSON: Copyright 2008-2011 Google Inc.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0

package br.com.casproject;

import android.net.http.AndroidHttpClient;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import br.com.casproject.repository.SessionRepository;

import static android.content.ContentValues.TAG;


public class WebService {

    public static String postJsonToItopServer(String operation,
                                              String itopClass, String key, String fields, String output_fields) {
        AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        String result = "";

        try {
            HttpPost request = new HttpPost();
            String req = "http://itop.digitall.inf.br/webservices/rest.php?version=1.3";

            request.setURI(new URI(req));

            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("auth_user", SessionRepository.getNomeusuario()));
            postParameters.add(new BasicNameValuePair("auth_pwd", SessionRepository.getSenhausuario()));

            JSONObject jsd = new JSONObject();
            jsd.put("operation", operation);
            if (fields != "")
                jsd.put("comment", "Synchronization from blah...");
            jsd.put("class", itopClass);
            if (key != "")
                jsd.put("key", key);
            if (fields != "") {

                JSONObject jsdFields = new JSONObject(fields);

                jsd.put("fields", jsdFields);
                Log.e("FIELDS ", jsdFields.toString());
            }
            if (output_fields != "")
                jsd.put("output_fields", output_fields);


            Log.e("FIELDS ", jsd.toString());

            postParameters.add(new BasicNameValuePair("json_data", jsd.toString()));

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                    postParameters);
            request.setEntity(formEntity);

            // request.addHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse response = client.execute(request);
            String status = response.getStatusLine().toString();

            if (status.contains("200") && status.contains("OK")) {

                // request worked fine, retrieved some data
                InputStream instream = response.getEntity().getContent();
                result = convertStreamToString(instream);
                //Log.d(TAG, "result is: " + result);
            } else // some error in http response
            {
                Log.e(TAG, "Get data - http-ERROR: " + status);
                result = "SERVER_ERROR: http status " + status;
            }

        } catch (Exception e) {
            // Toast does not work in background task
            Log.e(TAG, "Get data -  " + e.toString());
            result = "SERVER_ERROR: " + e.toString();
        } finally {
            client.close(); // needs to be done for androidhttpclient
        }
        return result;

    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
