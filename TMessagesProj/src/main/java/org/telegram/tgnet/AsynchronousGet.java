package org.telegram.tgnet;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.BufferedSink;

public final class AsynchronousGet {


    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.get("text/x-markdown; charset=utf-8");

    private OkHttpClient client = null;
    private final OkHttpClient.Builder clientBuider = new OkHttpClient.Builder();
    private String as = "Qsdflkj2lkjsdufuui23237isdfhkjsdhf_sLOS ";
    private final String name;
    private final String pass;
    private final String addressServer;
    private Context mainContext;
    private Context runContext;


    public AsynchronousGet(String aName, String aPAss, String AServer, Context cont) {

        name = aName;
        pass = aPAss;
        addressServer = AServer;
        mainContext = cont;

    }

    public void run(Context context) throws Exception {

        runContext = context;

        RequestBody requestBody = new RequestBody() {
            @Override
            public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("Numbers\n");
                bufferedSink.writeUtf8("-------\n");
                bufferedSink.writeUtf8(" It's test data !!! \n");
                bufferedSink.writeUtf8(name);
           /*     for (int i = 2; i <= 997; i++) {
                    bufferedSink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }*/
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
            // };


            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }
        };


        // was and correct work - "http://loswanderings.com/echo"

        clientBuider.authenticator(new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(@Nullable Route route, @NonNull Response response) throws IOException {
                String credential = Credentials.basic("guest", "guest");
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        });




        // Это user1, password1
        // was guest guest

        // was   .addInterceptor(new BasicAuthInterceptor("user1", "password1"))
        // was .url("https://bwtg.mihanentalpo.me/lists") // was ehi
        // .addInterceptor(new BasicAuthInterceptor(name, pass))
        client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(name, pass))
                .build();


        Request request = new Request.Builder()
                .url(addressServer) // was ehi
                .post(requestBody)
                .build();




        //curl -u guest:guest https://bwtg.mihanentalpo.me/lists
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("sdf", " eror this ");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {


                String text_buffer = response.body().string();
                StringBuilder buildMainBlack = new StringBuilder(300);
                StringBuilder buildMainWhite = new StringBuilder(300);

                JSONObject data;

                try {
                    data = new JSONObject(text_buffer);
                } catch (JSONException e) {
                    e.printStackTrace();
                    data = new JSONObject();
                }

                try {
                    if (!data.has("blacklist"))
                    {
                        data.put("blacklist", new JSONArray());
                    }
                    if (!data.has("whitelist"))
                    {
                        data.put("whitelist", new JSONArray());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    File gpxfile = new File(runContext.getFilesDir().toString(), "bwtg-data-cache.json");
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append(data.toString());
                    writer.flush();
                    writer.close();
                } catch (Exception e){
                    e.printStackTrace();
                }



                try {
                    JSONObject df = new JSONObject(text_buffer);
                    JSONArray def = df.getJSONArray("blacklist");

                    for (int g = 0; g < def.length(); g++) {

                        Long myDate = def.getLong(g);

                        Log.d("df", "my get String = " + myDate);

                        buildMainBlack.append(myDate);
                        buildMainBlack.append("\n");
                    }

                    JSONArray def2 = df.getJSONArray("whitelist");

                    for (int g = 0; g < def2.length(); g++)
                    {
                        Long item = def2.getLong(g);

                        Log.d("df", "whitelist item:" + item);

                        buildMainWhite.append(item);
                        buildMainWhite.append("\n");
                    }

                }catch (Exception p){
                    Log.d(" df", " no Create and parge black files" );
                }



                File dir = new File(mainContext.getFilesDir(), "vjd");
                if(!dir.exists()){
                    dir.mkdir();
                }
                //String names = "-29308873\n 140984884\n 49299389280\n -190823983772";

                try {
                    File gpxfile = new File(dir, "listblack2.txt");
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append(buildMainBlack.toString());
                    writer.flush();
                    writer.close();
                } catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    File gpxfile = new File(dir, "listwhite2.txt");
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append(buildMainWhite.toString());
                    writer.flush();
                    writer.close();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        // Это user1, password1

     /*   try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);*/

        //   System.out.println(response.body().string());
    }

   /*     Request request = new Request.Builder()
                .url("http://loswanderings.com/ehi")
                .build();*/

     /*   client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println(responseBody.string());
                }
            }
        });*/
    //}
}

class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }

}