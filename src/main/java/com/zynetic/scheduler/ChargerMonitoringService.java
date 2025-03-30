package com.zynetic.scheduler;

import com.zynetic.repository.ChargerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class ChargerMonitoringService {
    @Autowired
    private ChargerRepository chargerRepository;

    @Scheduled(fixedRate = 300000) // Runs every 5 minutes
    public void markUnavailableChargers() {
        Instant fiveMinutesAgo = Instant.now().minusSeconds(300);
        chargerRepository.findAll()
                .filter(charger -> charger.getLastHeartbeat().isBefore(fiveMinutesAgo))
                .flatMap(charger -> {
                    charger.setStatus("Unavailable");
                    return chargerRepository.save(charger);
                })
                .subscribe();
    }
}
