package io.java.learning.rmi;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;

import java.util.List;

public interface UserClient {
    @Get("http://localhost:8081/user/list")
    List<String> getUsers(@Query("user") String user);
}
