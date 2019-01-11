package org.kpagan.clash.clashserver.api.player.battlelog;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface BattleLogRepository extends MongoRepository<BattleLogInfo, String> {

	BattleLogInfo findByPlayerTagAndTimestampAfter(@Param("playerTag") String tag, @Param("timestamp") LocalDateTime dateTime);
	
}
