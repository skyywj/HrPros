package com.sky.hrpro.service;

import com.sky.hrpro.dao.TestDao;
import com.sky.hrpro.entity.TestEntity;
import com.sky.hrpro.tools.LockHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: CarryJey
 * @Date: 2018/9/27 上午10:31
 */
@Service
public class TestService {
    @Autowired
    private TestDao testDao;

    @Autowired
    private LockHelper lockHelper;

//    使用异步线程池处理该方法
    @Async("taskScheduler")
    public void testService(int id){
        //此处写逻辑，注意不要在接口层去写过多逻辑，尽量放到service层
        testDao.addTest(id);
    }

//    redis缓存测试
    public void testCache(int id){
        TestEntity testEntity = testDao.getTest(id);
        System.out.println(testEntity);
    }

//    LockHelper工具测试
    public void testLockHelper(){
        lockHelper.runWithLock(
        ()-> System.out.println("lock success...")
        );
    }

}
