package com.example.myapplication.retrofit;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RMainActivity extends AppCompatActivity {

    TextView responseText;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);

        responseText = findViewById(R.id.responseText);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        //GET List Resources

        Call<MultipleResource> call = apiInterface.doGetListResources();

        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {

                Log.e("TAG!",""+response.code()+"");
                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }

                responseText.setText(displayResponse);
            }


            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                    call.cancel();
            }
        });


         //Create new user

        User2 user = new User2("morpheus", "leader");
        Call<User2> call1 = apiInterface.createUser(user);

        call1.enqueue(new Callback<User2>(){
            @Override
            public void onResponse(Call<User2> call, Response<User2> response) {
                User2 user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();
                Log.e("TAG!",user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt);

            }

            @Override
            public void onFailure(Call<User2> call, Throwable t) {
                call.cancel();
            }
        });


        //GET List Users

        Call<UserList> call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
                Log.e("TAG!",text + " page\n" + total + " total\n" + totalPages + " totalPages\n");

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                    Log.e("TAG!","id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar);
                }


            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });


        // POST name and job Url encoded.

        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus","leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
                Log.e("TAG!",text + " page\n" + total + " total\n" + totalPages + " totalPages\n");
                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                    Log.e("TAG!", "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar);
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
