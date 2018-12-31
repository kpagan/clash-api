package org.kpagan.clash.clashserver;

import java.util.List;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberInfo;
import org.kpagan.clash.clashserver.api.clan.members.ClanMemberListService;
import org.kpagan.clash.clashserver.api.clan.members.IdleClanMemberService;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.api.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ClashServerApplication {

	@Autowired
    private PlayerService playerService;
	
	@Autowired
	private ClanMemberListService clanMembers;
	
	@Autowired
	private IdleClanMemberService idlePlayerFinder;
	
	public static void main(String[] args) {
		SpringApplication.run(ClashServerApplication.class, args);
	}

//	@Bean
	public ApplicationRunner init() {
		return args -> {
			PlayerDetailsInfo player = playerService.getPlayer("9CV8JVUQR");
			List<ClanMemberInfo> clanMembers2 = idlePlayerFinder.getIdlePlayers("P9R9282L");
			
			ObjectMapper mapper = new ObjectMapper();
//			mapper.writeValue(System.out, player);
			mapper.writeValue(System.out, clanMembers2);
		};
	}
}
