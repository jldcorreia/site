/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usacucar.regra.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author jeffersonbarison
 */
public class Fcm {
    
    final static String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    final static String server_Key = "AIzaSyAQwMMsIHF6Fi0MlC8rQ8aYuEIvynsmMd8";
    
    public static void  sendFCMNotification(String tokenId, String title,String message) {
        try {
// Create URL instance.
            URL url = new URL(FCM_URL);
// create connection.
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
//set method as POST or GET
            conn.setRequestMethod("POST");
//pass FCM server key
            conn.setRequestProperty("Authorization", "key=" + "AIzaSyAQwMMsIHF6Fi0MlC8rQ8aYuEIvynsmMd8");
//Specify Message Format
            conn.setRequestProperty("Content-Type", "application/json");
//Create JSON Object & pass value
            JSONObject infoJson = new JSONObject();
            infoJson.put("title", title);
            infoJson.put("body", message);
            JSONObject json = new JSONObject();
            json.put("to", tokenId.trim());
            json.put("notification", infoJson);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            int status = 0;
            if (null != conn) {
                status = conn.getResponseCode();
            }
            if (status != 0) {
                switch (status) {
                    case 200:
                        //SUCCESS message
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        System.out.println("Android Notification Response : " + reader.readLine());
                        break;
                    case 401:
                        //client side error
                        System.out.println("Notification Response : TokenId : " + tokenId + " Error occurred :");
                        break;
                    case 501:
                        //server side error
                        System.out.println("Notification Response : [ errorCode=ServerError ] TokenId : " + tokenId);
                        break;
                    case 503:
                        //server side error
                        System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + tokenId);
                        break;
                    default:
                        break;
                }

            }

        } catch (MalformedURLException mlfexception) {
// Prototcal Error
            System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());

        } catch (IOException mlfexception) {
//URL problem
            System.out.println("Reading URL, Error occurred while sending push Notification!.." + mlfexception.getMessage());
        } catch (JSONException jsonexception) {
//Message format error
            System.out.println("Message Format, Error occurred while sending push Notification!.." + jsonexception.getMessage());
        } catch (Exception exception) {
//General Error or exception.
            System.out.println("Error occurred while sending push Notification!.." + exception.getMessage());
        }
    }
}
