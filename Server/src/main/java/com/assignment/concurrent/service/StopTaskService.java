package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.PointPairingTask;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import org.springframework.stereotype.Service;

public class StopTaskService extends TimerTask {
    
    private ExecutorService executorService;
    private Timer timer;
    private long endTime;
    
    public StopTaskService(ExecutorService executorService, Timer timer){
        this.executorService = executorService;
        this.timer = timer;
    }

    @Override
    public void run() {
        endTime = System.currentTimeMillis();
        PointPairingTask.killThread();//because the InterruptedException is catched,
        //need to set the variable to stop the Runnable
        executorService.shutdownNow();
        timer.cancel();//need to cancel the timer or the program wont end
    }
    
    public long getEndTime(){
        return endTime;
    }
}
