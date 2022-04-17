package com.hendisantika.service;

import com.hendisantika.model.Watertank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.33
 */
@Service
public class WatertankManager {
    private static final Logger log = LoggerFactory.getLogger(WatertankManager.class);

    /**
     * Spring Dependency Injection
     */
    @Autowired
    private WatertankService watertankServiceStub;

    /**
     * Get All the Water-tanks
     *
     * @return
     */
    public Map<Integer, Watertank> getAllWatertanks() {
        return watertankServiceStub.getAllWatertanks();
    }

    /**
     * Get the maximum capacity amount of a specific water tank container
     *
     * @param watertankId
     * @return {@link Double}
     */
    public double getMaxCapacity(Integer watertankId) {
        return watertankServiceStub.getWatertankById(watertankId).getMaxCapacity();
    }

    /**
     * Get the current capacity amount of a specific water tank container
     *
     * @param watertankId
     * @return {@link Double}
     */
    public double getCurrentCapacity(Integer watertankId) {
        return watertankServiceStub.getWatertankById(watertankId).getCurrentCapacity();
    }

    /**
     * Check if more water can be added to a specific water-tank
     *
     * @param liter
     * @param watertankId
     * @return {@link Boolean}
     */
    public boolean canAddVolumeToWatertank(double liter, Integer watertankId) {
        return getMaxCapacity(watertankId) >= (getCurrentCapacity(watertankId) + liter);
    }

    /**
     * Add more water to a specific water-tank
     *
     * @param liter
     * @param watertankId
     * @return {@link Boolean}
     */
    public boolean addWater(double liter, Integer watertankId) {
        if (canAddVolumeToWatertank(liter, watertankId)) {
            Watertank currentWatertank = watertankServiceStub.getWatertankById(watertankId);
            double originalCapacity = currentWatertank.getCurrentCapacity();
            currentWatertank.setCurrentCapacity(originalCapacity + liter);
            return true;
        }
        return false;

    }

    /**
     * Method will execute new {@link Runnable} in order to create a water leak on a
     * specific water-tank
     *
     * @param literOfLeak - the amount of liters to subtract from the water-tank
     *                    (The leak)
     * @param everySec    - the amount of time to wait between the {@link Runnable}
     *                    execution
     * @param watertankId - the water-tank id
     */
    public void createALeak(double literOfLeak, long everySec, Integer watertankId) {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("New leak of {} liters was created for watertank# {}", literOfLeak, watertankId);
                Watertank watertank = watertankServiceStub.getWatertankById(watertankId);
                // In case the amount of lean to subtract is not bigger the current amount of
                // water left in the water - tank
                if (watertank.getCurrentCapacity() > literOfLeak) {
                    watertank.setCurrentCapacity(watertank.getCurrentCapacity() - literOfLeak);
                } else {
                    // In case the amount of lean to subtract is bigger - just empty the whole water
                    // - tank
                    watertank.setCurrentCapacity(0);
                }

            }
        }, everySec, everySec, TimeUnit.SECONDS);

    }

}
