package com.emcs.Script;

import com.emcs.serviceImpl.busniess.other.MakeInsertSql;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Administrator on 2018/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MakeInsertSqlFromDb {
    @Autowired
    MakeInsertSql mis;
@Test
public void testMakeSql(){
    System.out.print("################################");
    mis.doService(null);

}



}
