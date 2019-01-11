package org.kpagan.clash.clashserver.api.player;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends MongoRepository<PlayerDetailsInfo, String> {

	PlayerDetailsInfo findByTagAndTimestampAfter(@Param("tag") String tag, @Param("timestamp") LocalDateTime dateTime);
}
